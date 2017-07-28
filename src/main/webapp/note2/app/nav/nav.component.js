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
require("renderer");
var router_1 = require("@angular/router");
var core_1 = require("@angular/core");
var NavComponent = (function () {
    function NavComponent(router) {
        this.router = router;
    }
    NavComponent.prototype.navigate = function (path) {
        this.router.navigate([path]);
    };
    NavComponent.prototype.ngOnInit = function () {
        var height = ($(window).height() - 300) / 2;
        $(".nav-index .add").css("margin", height + "px 150px");
        $(".nav-index .find").css("margin", height + "px 150px");
        RENDERER.init();
    };
    NavComponent = __decorate([
        core_1.Component({
            selector: 'note',
            template: "\n<div id=\"jsi-cards-container\" class=\"nav-main\">\n    <div class=\"nav-index\">\n        <div class=\"add\" (click)=\"navigate('edit')\"><a>\u65B0\u589E</a></div>\n        <div class=\"find\" (click)=\"navigate('list')\"><a>\u67E5\u770B</a></div>\n    </div>\n</div>\n",
            styleUrls: ['app/nav/nav.component.css'],
        }),
        __metadata("design:paramtypes", [router_1.Router])
    ], NavComponent);
    return NavComponent;
}());
exports.NavComponent = NavComponent;
//# sourceMappingURL=nav.component.js.map