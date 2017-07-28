import {Component, OnInit} from '@angular/core';

@Component({
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

export class AppComponent implements OnInit {
    public ngOnInit() {

    }
}
