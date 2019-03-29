import 'common';
import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Http} from '@angular/http';
import {Note} from "./note.model";
import {NoteService} from "../app.service";

declare let $: any,
    Path: any;

@Component({
    selector: 'note',
    template: `
<div class="note-index" self-height="$(window).height()">
    <div class="book-mark" [hidden]="buttonShow || source==='sw'" (mouseenter)="buttonShow = true" (mouseleave)="buttonDelayHide()"
         ng-mouseleave="buttonDelayHide()"><span><img src="https://bitcoinrobot.cn/file/img/note/bookmarks.jpg"></span>
    </div>
    <div class="operate" [hidden]="!buttonShow">
        <ul>
            <li>
                <button class="btn btn-primary" (click)="editNote(0)">新增</button>
            </li>
            <li>
                <button class="btn btn-danger" (click)="editNote(1)">编辑</button>
            </li>
            <li>
                <button class="btn btn-default" (click)="backList()">返回</button>
            </li>
        </ul>
    </div>
    <div class="edit">
        <span [hidden]="!note.editTime">本主题最后于{{note.editTime|date:'yyyy-MM-dd HH:mm:ss'}}编辑</span>
    </div>
    <div class="title">
        主题：<span>{{note.title}}</span>
        作者：<span>{{note.poster}}</span>
        ip：<span>{{note.ip}}</span>
        时间：<span>{{note.postTime|date:'yyyy-MM-dd HH:mm:ss'}}</span>
    </div>
    <div class="box">
        <div class="content" id="content"></div>
    </div>
</div>
    `,
    styleUrls: ['app/note/note.component.css'],
})

export class NoteComponent implements OnInit {
    private buttonShow: boolean = false;
    private source: string = "";
    private note: Note = new Note();

    constructor(private http: Http,
                private router: Router,
                private activatedRoute: ActivatedRoute,
                private noteSerive: NoteService) {
        console.log("construct");
        if (noteSerive.isNewNote()) {
            http.get(Path.getUri("api/note/get?id=" + noteSerive.getId())).subscribe(
                res => {
                    this.note = res.json();
                    noteSerive.setNote(this.note);
                    $("#content")[0].innerHTML = this.note.content;
                }
            );
        } else {
            this.note = noteSerive.getNote();
        }

    }

    editNote(edit) {
        sessionStorage.setItem("edit", edit);
        this.router.navigate(["edit"]);
    };

    backList() {
        this.router.navigate(["list"]);
    };

    buttonDelayHide() {
        setTimeout(() => {
            this.buttonShow = false;
        }, 3000);
    }


    public ngOnInit() {
        console.log("note onInit...");
        if (this.note.content) {
            $("#content")[0].innerHTML = this.note.content;
        }
    }
}
