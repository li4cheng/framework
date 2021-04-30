import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysMenu } from 'app/shared/model/sys-menu.model';
import { SysMenuService } from './sys-menu.service';
import { SysMenuDeleteDialogComponent } from './sys-menu-delete-dialog.component';

@Component({
  selector: 'jhi-sys-menu',
  templateUrl: './sys-menu.component.html',
})
export class SysMenuComponent implements OnInit, OnDestroy {
  sysMenus?: ISysMenu[];
  eventSubscriber?: Subscription;

  constructor(protected sysMenuService: SysMenuService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.sysMenuService.query().subscribe((res: HttpResponse<ISysMenu[]>) => (this.sysMenus = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSysMenus();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISysMenu): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSysMenus(): void {
    this.eventSubscriber = this.eventManager.subscribe('sysMenuListModification', () => this.loadAll());
  }

  delete(sysMenu: ISysMenu): void {
    const modalRef = this.modalService.open(SysMenuDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sysMenu = sysMenu;
  }
}
