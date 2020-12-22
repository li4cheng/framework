import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperationLog } from 'app/shared/model/operation-log.model';
import { OperationLogService } from './operation-log.service';

@Component({
  templateUrl: './operation-log-delete-dialog.component.html',
})
export class OperationLogDeleteDialogComponent {
  operationLog?: IOperationLog;

  constructor(
    protected operationLogService: OperationLogService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.operationLogService.delete(id).subscribe(() => {
      this.eventManager.broadcast('operationLogListModification');
      this.activeModal.close();
    });
  }
}
