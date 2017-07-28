import 'renderer';
import {Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
declare let $: any,
    RENDERER: any;

@Component({
    selector: 'note',
    template: `
<div id="jsi-cards-container" class="nav-main">
    <div class="nav-index">
        <div class="add" (click)="navigate('edit')"><a>新增</a></div>
        <div class="find" (click)="navigate('list')"><a>查看</a></div>
    </div>
</div>
`,
    styleUrls: ['app/nav/nav.component.css'],
})

export class NavComponent implements OnInit {
    constructor(private router: Router) {
    }

    navigate(path: String) {
        this.router.navigate([path]);
    }

    public ngOnInit() {
        let height = ($(window).height() - 300) / 2;
        $(".nav-index .add").css("margin", height + "px 150px");
        $(".nav-index .find").css("margin", height + "px 150px");
        RENDERER.init();
    }
}
