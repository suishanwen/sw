import {Routes} from '@angular/router';
import {NoteComponent} from './note/index';
import {NavComponent} from "./nav/nav.component";
import {ListComponent} from "./list/list.component";
import {EditComponent} from "./edit/edit.component";
import {NoContentComponent} from './no-content/index';


export const ROUTES: Routes = [
    {path: '', redirectTo: 'nav', pathMatch: 'full'},
    {path: 'nav', component: NavComponent},
    {path: 'list', component: ListComponent},
    {path: 'note', component: NoteComponent},
    {path: 'edit', component: EditComponent},
    {path: 'no-content', component: NoContentComponent}
];