"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
// Tell Angular2 we're creating a Pipe with TypeScript decorators
var SearchPipe = (function () {
    function SearchPipe() {
    }
    // Transform is the new "return function(value, args)" in Angular 1.x
    SearchPipe.prototype.transform = function (value, args) {
        var title = args;
        return value.filter(function (note) {
            return note.title.indexOf(title) !== -1;
        });
    };
    return SearchPipe;
}());
SearchPipe = __decorate([
    core_1.Pipe({
        name: 'SearchPipe'
    })
], SearchPipe);
exports.SearchPipe = SearchPipe;
//# sourceMappingURL=search.pipe.js.map