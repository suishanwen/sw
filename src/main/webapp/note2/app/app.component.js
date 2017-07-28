"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var AppComponent = (function () {
    function AppComponent() {
    }
    AppComponent.prototype.ngOnInit = function () {
    };
    AppComponent = __decorate([
        core_1.Component({
            selector: 'note',
            template: '<router-outlet></router-outlet>'
            // template: `
            //             <div>
            //                 <!--使用了bootstrap样式的导航，routerLinkActive，表示路由激活的时候，谈价active类样式-->
            //                 <ul class="nav navbar-nav">
            //                     <li routerLinkActive="active"><a routerLink="">首页</a></li>
            //                     <li routerLinkActive="active"><a routerLink="about">about</a></li>
            //                     <li routerLinkActive="active"><a routerLink="no-content">no-content</a></li>
            //                 </ul>
            //                 <!--路由内容显示区域-->
            //                 <router-outlet></router-outlet>
            //             </div>
            //             `
        })
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map