import 'common';
import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Http} from '@angular/http';
import {Note} from "../note/note.model";
import {NoteService} from "../app.service";

declare let $: any,
    Path: any,
    TQEditor: any,
    content: any;

@Component({
    selector: 'note',
    template: `
<div class="note-edit-index">
    <div>
        <span class="note-subject badge">{{note.id?"编辑主题":"新主题"}}</span>
        <span>
            <label for="recommend">推荐<input name="recommend" class="recommend" type="checkbox"
                                            [(ngModel)]="note.recommend" (Change)="recommend($event)"/></label>
        </span>
    </div>
    <form #noteForm="ngForm" class="form-horizontal">
        <div class="form-group note-info">
            <div>
                <label for="title">标题<input name="title" class="title" [(ngModel)]="note.title"
                                            required/></label>
            </div>
            <div>
                <label for="poster">作者<input name="poster" class="poster" [(ngModel)]="note.poster"
                                             required/></label>
            </div>
        </div>
        <div class="form-group note-info">
            <div>
                <label for="summary">摘要<input name="summary" class="summary"
                                              [(ngModel)]="note.summary"
                                              required/></label>
            </div>
            <div>
                <label for="tag">标签<input name="tag" class="tag" [(ngModel)]="note.tag"
                                          required/></label>
            </div>
        </div>

        <div class="form-group">
            <textarea name="content" id="content" cols="80" rows="16" class="content" [(ngModel)]="note.content"
                      (click)="newEditor()"
                      required></textarea>
        </div>
        <div class="form-group note-info">
            <span [hidden]="!note.postTime">发布时间:{{note.postTime|date:'yyyy-MM-dd HH:mm:ss'}}</span>
            <span [hidden]="!note.editTime">最后编辑时间:{{note.editTime|date:'yyyy-MM-dd HH:mm:ss'}}</span>
        </div>
        <div class="form-group operate">
            <button class="btn btn-default" (click)="backNote()">返回</button>
            <button class="btn btn-primary" [disabled]="noteForm.form.$invalid" (click)="saveNote()">提交</button>
        </div>
    </form>
</div>

    `,
    styleUrls: ['app/edit/edit.component.css'],
})

export class EditComponent implements OnInit {

    private note: Note = new Note();

    constructor(private http: Http,
                private router: Router,
                private activatedRoute: ActivatedRoute,
                private noteService: NoteService) {

        let edit = sessionStorage.getItem("edit");
        if (edit === "1") {
            this.note = noteService.getNote();
        }
    }


    recommend(event) {
        console.log(event);
    }

    newEditor() {
        new TQEditor('content');
        $("#TQEditorContainer_content")[0].style.width = "808px";
        setTimeout(() => {
            $("#content")[0].focus();
        }, 100);
    };

    backNote() {
        console.log("back");
        if (this.noteService.getId()) {
            this.router.navigate(["note"]);
        } else {
            this.router.navigate(["nav"]);
        }
    }

    saveNote() {
        console.log(this.note);
        let url = "";
        if (this.note.id) {
            url = Path.getUri("api/note/edit");
        } else {
            url = Path.getUri("api/note/add");
        }
        this.note.content = $("#content").val();
        this.note.recommend = this.note.recommend ? 1 : 0;
        this.http.post(url, this.note).subscribe(res => {
            console.log(res);
            if (res.ok) {
                if (res.text() === "") {
                    alert("当前IP不允许编辑此贴"!);
                    return;
                } else {
                    this.noteService.setNote(res.json());
                    this.router.navigate(["note"]);
                }
            }
        });
    }


    public ngOnInit() {

    }
}
