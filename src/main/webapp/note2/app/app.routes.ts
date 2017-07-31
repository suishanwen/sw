import {Routes} from '@angular/router';
import {NoteComponent} from './note';
import {NavComponent} from "./nav/nav.component";
import {ListComponent} from "./list/list.component";
import {EditComponent} from "./edit/edit.component";


export const ROUTES: Routes = [
    {path: '', redirectTo: 'nav', pathMatch: 'full'},
    {path: 'nav', component: NavComponent},
    {path: 'list', component: ListComponent},
    {path: 'note', component: NoteComponent},
    {path: 'edit', component: EditComponent},
];