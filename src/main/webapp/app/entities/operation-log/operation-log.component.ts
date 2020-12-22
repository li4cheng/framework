import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOperationLog } from 'app/shared/model/operation-log.model';
import { OperationLogService } from './operation-log.service';
import { OperationLogDeleteDialogComponent } from './operation-log-delete-dialog.component';

@Component({
  selector: 'jhi-operation-log',
  templateUrl: './operation-log.component.html',
})
export class OperationLogComponent implements OnInit, OnDestroy {
  operationLogs?: IOperationLog[];
  eventSubscriber?: Subscription;

  constructor(
    protected operationLogService: OperationLogService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.operationLogService.query().subscribe((res: HttpResponse<IOperationLog[]>) => (this.operationLogs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOperationLogs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOperationLog): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOperationLogs(): void {
    this.eventSubscriber = this.eventManager.subscribe('operationLogListModification', () => this.loadAll());
  }

  delete(operationLog: IOperationLog): void {
    const modalRef = this.modalService.open(OperationLogDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.operationLog = operationLog;
  }
}
