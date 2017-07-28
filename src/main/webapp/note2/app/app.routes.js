"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var index_1 = require("./note/index");
var nav_component_1 = require("./nav/nav.component");
var list_component_1 = require("./list/list.component");
var edit_component_1 = require("./edit/edit.component");
var index_2 = require("./no-content/index");
exports.ROUTES = [
    { path: '', redirectTo: 'nav', pathMatch: 'full' },
    { path: 'nav', component: nav_component_1.NavComponent },
    { path: 'list', component: list_component_1.ListComponent },
    { path: 'note', component: index_1.NoteComponent },
    { path: 'edit', component: edit_component_1.EditComponent },
    { path: 'no-content', component: index_2.NoContentComponent }
];
//# sourceMappingURL=app.routes.js.map