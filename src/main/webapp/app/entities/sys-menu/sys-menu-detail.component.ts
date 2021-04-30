import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysMenu } from 'app/shared/model/sys-menu.model';

@Component({
  selector: 'jhi-sys-menu-detail',
  templateUrl: './sys-menu-detail.component.html',
})
export class SysMenuDetailComponent implements OnInit {
  sysMenu: ISysMenu | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysMenu }) => (this.sysMenu = sysMenu));
  }

  previousState(): void {
    window.history.back();
  }
}
