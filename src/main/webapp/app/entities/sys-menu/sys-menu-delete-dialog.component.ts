import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysMenu } from 'app/shared/model/sys-menu.model';
import { SysMenuService } from './sys-menu.service';

@Component({
  templateUrl: './sys-menu-delete-dialog.component.html',
})
export class SysMenuDeleteDialogComponent {
  sysMenu?: ISysMenu;

  constructor(protected sysMenuService: SysMenuService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sysMenuService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sysMenuListModification');
      this.activeModal.close();
    });
  }
}
