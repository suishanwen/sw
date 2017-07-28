import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {NavComponent} from './nav/index';
import {ListComponent} from './list/index';
import {NoteComponent} from './note/index';
import {EditComponent} from './edit/index';
import {NoContentComponent} from './no-content/index';

import {ROUTES} from "./app.routes";

import {SearchPipe} from "./list/search.pipe"
import {NoteService} from "./app.service";

@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule, RouterModule.forRoot(ROUTES)],
    declarations: [AppComponent, NavComponent, ListComponent, NoteComponent, EditComponent, NoContentComponent, SearchPipe],
    providers: [NoteService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
