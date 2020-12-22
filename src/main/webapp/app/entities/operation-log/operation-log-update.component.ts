import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOperationLog, OperationLog } from 'app/shared/model/operation-log.model';
import { OperationLogService } from './operation-log.service';

@Component({
  selector: 'jhi-operation-log-update',
  templateUrl: './operation-log-update.component.html',
})
export class OperationLogUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    operationLogCode: [],
    operatorId: [],
    operatorName: [],
    operationTime: [],
    interfaceName: [],
  });

  constructor(protected operationLogService: OperationLogService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operationLog }) => {
      if (!operationLog.id) {
        const today = moment().startOf('day');
        operationLog.operationTime = today;
      }

      this.updateForm(operationLog);
    });
  }

  updateForm(operationLog: IOperationLog): void {
    this.editForm.patchValue({
      id: operationLog.id,
      operationLogCode: operationLog.operationLogCode,
      operatorId: operationLog.operatorId,
      operatorName: operationLog.operatorName,
      operationTime: operationLog.operationTime ? operationLog.operationTime.format(DATE_TIME_FORMAT) : null,
      interfaceName: operationLog.interfaceName,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operationLog = this.createFromForm();
    if (operationLog.id !== undefined) {
      this.subscribeToSaveResponse(this.operationLogService.update(operationLog));
    } else {
      this.subscribeToSaveResponse(this.operationLogService.create(operationLog));
    }
  }

  private createFromForm(): IOperationLog {
    return {
      ...new OperationLog(),
      id: this.editForm.get(['id'])!.value,
      operationLogCode: this.editForm.get(['operationLogCode'])!.value,
      operatorId: this.editForm.get(['operatorId'])!.value,
      operatorName: this.editForm.get(['operatorName'])!.value,
      operationTime: this.editForm.get(['operationTime'])!.value
        ? moment(this.editForm.get(['operationTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      interfaceName: this.editForm.get(['interfaceName'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperationLog>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
