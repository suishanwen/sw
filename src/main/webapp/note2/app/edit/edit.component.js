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
var note_model_1 = require("../note/note.model");
var app_service_1 = require("../app.service");
var EditComponent = (function () {
    function EditComponent(http, router, activatedRoute, noteService) {
        this.http = http;
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.noteService = noteService;
        this.note = new note_model_1.Note();
        var edit = sessionStorage.getItem("edit");
        if (edit === "1") {
            this.note = noteService.getNote();
        }
    }
    EditComponent.prototype.recommend = function (event) {
        console.log(event);
    };
    EditComponent.prototype.newEditor = function () {
        new TQEditor('content', {
            toolbar: 'full',
            imageUploadUrl: 'http://localhost:8051/sw/api/file/upload',
            directInsertUploadImage: true,
            flashUploadUrl: 'http://localhost:8051/sw/api/file/upload',
            videoUploadUrl: 'http://localhost:8051/sw/api/file/upload',
            musicUploadUrl: 'http://localhost:8051/sw/api/file/upload',
            linkUploadUrl: 'http://localhost:8051/sw/api/file/upload'
        });
        $("#TQEditorContainer_content")[0].style.width = "808px";
        setTimeout(function () {
            $("#content")[0].focus();
        }, 100);
    };
    EditComponent.prototype.backNote = function () {
        console.log("back");
        if (this.noteService.getId()) {
            this.router.navigate(["note"]);
        }
        else {
            this.router.navigate(["nav"]);
        }
    };
    EditComponent.prototype.saveNote = function () {
        var _this = this;
        console.log(this.note);
        var url = "";
        if (this.note.id) {
            url = Path.getUri("api/note/edit");
        }
        else {
            url = Path.getUri("api/note/add");
        }
        this.note.content = $("#content").val();
        this.note.recommend = this.note.recommend ? 1 : 0;
        this.http.post(url, this.note).subscribe(function (res) {
            console.log(res);
            if (res.ok) {
                if (res.text() === "") {
                    alert("当前IP不允许编辑此贴");
                    return;
                }
                else {
                    _this.noteService.setNote(res.json());
                    _this.router.navigate(["note"]);
                }
            }
        });
    };
    EditComponent.prototype.ngOnInit = function () {
    };
    return EditComponent;
}());
EditComponent = __decorate([
    core_1.Component({
        selector: 'note',
        template: "\n<div class=\"note-edit-index\">\n    <div>\n        <span class=\"note-subject badge\">{{note.id?\"\u7F16\u8F91\u4E3B\u9898\":\"\u65B0\u4E3B\u9898\"}}</span>\n        <span>\n            <label for=\"recommend\">\u63A8\u8350<input name=\"recommend\" class=\"recommend\" type=\"checkbox\"\n                                            [(ngModel)]=\"note.recommend\" (Change)=\"recommend($event)\"/></label>\n        </span>\n    </div>\n    <form #noteForm=\"ngForm\" class=\"form-horizontal\">\n        <div class=\"form-group note-info\">\n            <div>\n                <label for=\"title\">\u6807\u9898<input name=\"title\" class=\"title\" [(ngModel)]=\"note.title\"\n                                            required/></label>\n            </div>\n            <div>\n                <label for=\"poster\">\u4F5C\u8005<input name=\"poster\" class=\"poster\" [(ngModel)]=\"note.poster\"\n                                             required/></label>\n            </div>\n        </div>\n        <div class=\"form-group note-info\">\n            <div>\n                <label for=\"summary\">\u6458\u8981<input name=\"summary\" class=\"summary\"\n                                              [(ngModel)]=\"note.summary\"\n                                              required/></label>\n            </div>\n            <div>\n                <label for=\"tag\">\u6807\u7B7E<input name=\"tag\" class=\"tag\" [(ngModel)]=\"note.tag\"\n                                          required/></label>\n            </div>\n        </div>\n\n        <div class=\"form-group\">\n            <textarea name=\"content\" id=\"content\" cols=\"80\" rows=\"16\" class=\"content\" [(ngModel)]=\"note.content\"\n                      (click)=\"newEditor()\"\n                      required></textarea>\n        </div>\n        <div class=\"form-group note-info\">\n            <span [hidden]=\"!note.postTime\">\u53D1\u5E03\u65F6\u95F4:{{note.postTime|date:'yyyy-MM-dd HH:mm:ss'}}</span>\n            <span [hidden]=\"!note.editTime\">\u6700\u540E\u7F16\u8F91\u65F6\u95F4:{{note.editTime|date:'yyyy-MM-dd HH:mm:ss'}}</span>\n        </div>\n        <div class=\"form-group operate\">\n            <button class=\"btn btn-default\" (click)=\"backNote()\">\u8FD4\u56DE</button>\n            <button class=\"btn btn-primary\" [disabled]=\"noteForm.form.$invalid\" (click)=\"saveNote()\">\u63D0\u4EA4</button>\n        </div>\n    </form>\n</div>\n\n    ",
        styleUrls: ['app/edit/edit.component.css'],
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof http_1.Http !== "undefined" && http_1.Http) === "function" && _a || Object, typeof (_b = typeof router_1.Router !== "undefined" && router_1.Router) === "function" && _b || Object, typeof (_c = typeof router_1.ActivatedRoute !== "undefined" && router_1.ActivatedRoute) === "function" && _c || Object, app_service_1.NoteService])
], EditComponent);
exports.EditComponent = EditComponent;
var _a, _b, _c;
//# sourceMappingURL=edit.component.js.map