"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
require("common");
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var http_1 = require("@angular/http");
var app_service_1 = require("../app.service");
var ListComponent = (function () {
    function ListComponent(http, router, noteService) {
        var _this = this;
        this.http = http;
        this.router = router;
        this.noteService = noteService;
        this.notes = [];
        this.search = { title: "" };
        http.get(Path.getUri("api/note/")).subscribe(function (res) { return _this.notes = res.json(); });
    }
    ListComponent.prototype.showNote = function (id) {
        this.noteService.setId(id);
        this.router.navigate(["note"]);
    };
    ListComponent.prototype.ngOnInit = function () {
    };
    return ListComponent;
}());
ListComponent = __decorate([
    core_1.Component({
        selector: 'note',
        template: "\n<div class=\"note-list-index\">\n    <div align=\"center\">\n        <input id=\"title\" [(ngModel)]=\"search.title\" placeholder=\"Search\" autofocus/>\n    </div>\n    <div class=\"note-list\" self-height=\"$(window).height()-32\">\n        <ul>\n            <li *ngFor=\"let note of notes|SearchPipe:search.title\" (click)=\"showNote(note.id)\">\n                <span class=\"title\">\n                    <span>{{note.title}}</span>\n                </span>\n                <span class=\"update\">{{(note.editTime?note.editTime:note.postTime)|date:'yyyy-MM-dd HH:mm:ss'}}</span>\n            </li>\n        </ul>\n    </div>\n</div>\n    ",
        styleUrls: ['app/list/list.component.css'],
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof http_1.Http !== "undefined" && http_1.Http) === "function" && _a || Object, typeof (_b = typeof router_1.Router !== "undefined" && router_1.Router) === "function" && _b || Object, app_service_1.NoteService])
], ListComponent);
exports.ListComponent = ListComponent;
var _a, _b;
//# sourceMappingURL=list.component.js.map