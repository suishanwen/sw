"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var note_1 = require("./note");
var nav_component_1 = require("./nav/nav.component");
var list_component_1 = require("./list/list.component");
var edit_component_1 = require("./edit/edit.component");
exports.ROUTES = [
    { path: '', redirectTo: 'nav', pathMatch: 'full' },
    { path: 'nav', component: nav_component_1.NavComponent },
    { path: 'list', component: list_component_1.ListComponent },
    { path: 'note', component: note_1.NoteComponent },
    { path: 'edit', component: edit_component_1.EditComponent },
];
//# sourceMappingURL=app.routes.js.map