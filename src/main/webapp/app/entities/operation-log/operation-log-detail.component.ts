import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperationLog } from 'app/shared/model/operation-log.model';

@Component({
  selector: 'jhi-operation-log-detail',
  templateUrl: './operation-log-detail.component.html',
})
export class OperationLogDetailComponent implements OnInit {
  operationLog: IOperationLog | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operationLog }) => (this.operationLog = operationLog));
  }

  previousState(): void {
    window.history.back();
  }
}
