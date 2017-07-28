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
var note_model_1 = require("./note.model");
var app_service_1 = require("../app.service");
var NoteComponent = (function () {
    function NoteComponent(http, router, activatedRoute, noteSerive) {
        var _this = this;
        this.http = http;
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.noteSerive = noteSerive;
        this.buttonShow = false;
        this.source = "";
        this.note = new note_model_1.Note();
        console.log("construct");
        if (noteSerive.isNewNote()) {
            http.get(Path.getUri("api/note/get?id=" + noteSerive.getId())).subscribe(function (res) {
                _this.note = res.json();
                noteSerive.setNote(_this.note);
                $("#content")[0].innerHTML = _this.note.content;
            });
        }
        else {
            this.note = noteSerive.getNote();
        }
    }
    NoteComponent.prototype.editNote = function (edit) {
        sessionStorage.setItem("edit", edit);
        this.router.navigate(["edit"]);
    };
    ;
    NoteComponent.prototype.backList = function () {
        this.router.navigate(["list"]);
    };
    ;
    NoteComponent.prototype.buttonDelayHide = function () {
        var _this = this;
        setTimeout(function () {
            _this.buttonShow = false;
        }, 3000);
    };
    NoteComponent.prototype.ngOnInit = function () {
        console.log("note onInit...");
        if (this.note.content) {
            $("#content")[0].innerHTML = this.note.content;
        }
    };
    NoteComponent = __decorate([
        core_1.Component({
            selector: 'note',
            template: "\n<div class=\"note-index\" self-height=\"$(window).height()\">\n    <div class=\"book-mark\" [hidden]=\"buttonShow || source==='sw'\" (mouseenter)=\"buttonShow = true\" (mouseleave)=\"buttonDelayHide()\"\n         ng-mouseleave=\"buttonDelayHide()\"><span><img src=\"http://121.42.239.141/note/assets/img/bookmarks.jpg\"></span>\n    </div>\n    <div class=\"operate\" [hidden]=\"!buttonShow\">\n        <button class=\"btn btn-primary\" (click)=\"editNote(0)\">\u65B0\u589E</button>\n        <button class=\"btn btn-danger\" (click)=\"editNote(1)\">\u7F16\u8F91</button>\n        <button class=\"btn btn-default\" (click)=\"backList()\">\u8FD4\u56DE</button>\n    </div>\n    <div class=\"edit\">\n        <span [hidden]=\"!note.editTime\">\u672C\u4E3B\u9898\u6700\u540E\u4E8E{{note.editTime|date:'yyyy-MM-dd HH:mm:ss'}}\u7F16\u8F91</span>\n    </div>\n    <div class=\"title\">\n        \u4E3B\u9898\uFF1A<span>{{note.title}}</span>\n        \u4F5C\u8005\uFF1A<span>{{note.poster}}</span>\n        ip\uFF1A<span>{{note.ip}}</span>\n        \u65F6\u95F4\uFF1A<span>{{note.postTime|date:'yyyy-MM-dd HH:mm:ss'}}</span>\n    </div>\n    <div class=\"box\">\n        <div class=\"content\" id=\"content\"></div>\n    </div>\n</div>\n    ",
            styleUrls: ['app/note/note.component.css'],
        }),
        __metadata("design:paramtypes", [http_1.Http,
            router_1.Router,
            router_1.ActivatedRoute,
            app_service_1.NoteService])
    ], NoteComponent);
    return NoteComponent;
}());
exports.NoteComponent = NoteComponent;
//# sourceMappingURL=note.component.js.map