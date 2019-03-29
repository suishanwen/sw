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
    return NoteComponent;
}());
NoteComponent = __decorate([
    core_1.Component({
        selector: 'note',
        template: "\n<div class=\"note-index\" self-height=\"$(window).height()\">\n    <div class=\"book-mark\" [hidden]=\"buttonShow || source===\'sw\'\" (mouseenter)=\"buttonShow = true\" (mouseleave)=\"buttonDelayHide()\"\n         ng-mouseleave=\"buttonDelayHide()\"><span><img src=\"https://bitcoinrobot.cn/file/img/note/bookmarks.jpg\"></span>\n    </div>\n    <div class=\"operate\" [hidden]=\"!buttonShow\">\n        <ul>\n            <li>\n                <button class=\"btn btn-primary\" (click)=\"editNote(0)\">新增</button>\n            </li>\n            <li>\n                <button class=\"btn btn-danger\" (click)=\"editNote(1)\">编辑</button>\n            </li>\n            <li>\n                <button class=\"btn btn-default\" (click)=\"backList()\">返回</button>\n            </li>\n        </ul>\n    </div>\n    <div class=\"edit\">\n        <span [hidden]=\"!note.editTime\">本主题最后于{{note.editTime|date:\'yyyy-MM-dd HH:mm:ss\'}}编辑</span>\n    </div>\n    <div class=\"title\">\n        主题：<span>{{note.title}}</span>\n        作者：<span>{{note.poster}}</span>\n        ip：<span>{{note.ip}}</span>\n        时间：<span>{{note.postTime|date:\'yyyy-MM-dd HH:mm:ss\'}}</span>\n    </div>\n    <div class=\"box\">\n        <div class=\"content\" id=\"content\"></div>\n    </div>\n</div>\n    ",
        styleUrls: ['app/note/note.component.css'],
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof http_1.Http !== "undefined" && http_1.Http) === "function" && _a || Object, typeof (_b = typeof router_1.Router !== "undefined" && router_1.Router) === "function" && _b || Object, typeof (_c = typeof router_1.ActivatedRoute !== "undefined" && router_1.ActivatedRoute) === "function" && _c || Object, app_service_1.NoteService])
], NoteComponent);
exports.NoteComponent = NoteComponent;
var _a, _b, _c;
//# sourceMappingURL=note.component.js.map