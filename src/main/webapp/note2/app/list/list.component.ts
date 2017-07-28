import 'common';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {NoteSummary, Search} from './list.model';
import {Http} from '@angular/http';
import {NoteService} from "../app.service";

declare let $: any,
    Path: any;

@Component({
    selector: 'note',
    template: `
<div class="note-list-index">
    <div align="center">
        <input id="title" [(ngModel)]="search.title" placeholder="Search" autofocus/>
    </div>
    <div class="note-list" self-height="$(window).height()-32">
        <ul>
            <li *ngFor="let note of notes|SearchPipe:search.title" (click)="showNote(note.id)">
                <span class="title">
                    <span>{{note.title}}</span>
                </span>
                <span class="update">{{(note.editTime?note.editTime:note.postTime)|date:'yyyy-MM-dd HH:mm:ss'}}</span>
            </li>
        </ul>
    </div>
</div>
    `,
    styleUrls: ['app/list/list.component.css'],
})

export class ListComponent implements OnInit {
    private notes: NoteSummary[] = [];
    private search: Search = {title: ""};

    constructor(private http: Http,
                private router: Router,
                private noteService: NoteService) {
        http.get(Path.getUri("api/note/")).subscribe(res => this.notes = res.json());

    }


    showNote(id: number) {
        this.noteService.setId(id);
        this.router.navigate(["note"]);
    }

    public ngOnInit() {

    }
}
