import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISysMenu, SysMenu } from 'app/shared/model/sys-menu.model';
import { SysMenuService } from './sys-menu.service';

@Component({
  selector: 'jhi-sys-menu-update',
  templateUrl: './sys-menu-update.component.html',
})
export class SysMenuUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    createdBy: [null, [Validators.maxLength(64)]],
    createdDate: [],
    lastModifiedBy: [null, [Validators.maxLength(64)]],
    lastModifiedDate: [],
    deleted: [],
    code: [null, [Validators.maxLength(64)]],
    parentIds: [null, [Validators.maxLength(3072)]],
    siblingSort: [],
    globalSort: [],
    leaf: [],
    level: [],
    shortName: [null, [Validators.maxLength(128)]],
    fullName: [null, [Validators.maxLength(128)]],
    href: [null, [Validators.maxLength(2000)]],
    icon: [null, [Validators.maxLength(2000)]],
    visible: [],
    status: [],
  });

  constructor(protected sysMenuService: SysMenuService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysMenu }) => {
      if (!sysMenu.id) {
        const today = moment().startOf('day');
        sysMenu.createdDate = today;
        sysMenu.lastModifiedDate = today;
      }

      this.updateForm(sysMenu);
    });
  }

  updateForm(sysMenu: ISysMenu): void {
    this.editForm.patchValue({
      id: sysMenu.id,
      createdBy: sysMenu.createdBy,
      createdDate: sysMenu.createdDate ? sysMenu.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: sysMenu.lastModifiedBy,
      lastModifiedDate: sysMenu.lastModifiedDate ? sysMenu.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      deleted: sysMenu.deleted,
      code: sysMenu.code,
      parentIds: sysMenu.parentIds,
      siblingSort: sysMenu.siblingSort,
      globalSort: sysMenu.globalSort,
      leaf: sysMenu.leaf,
      level: sysMenu.level,
      shortName: sysMenu.shortName,
      fullName: sysMenu.fullName,
      href: sysMenu.href,
      icon: sysMenu.icon,
      visible: sysMenu.visible,
      status: sysMenu.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysMenu = this.createFromForm();
    if (sysMenu.id !== undefined) {
      this.subscribeToSaveResponse(this.sysMenuService.update(sysMenu));
    } else {
      this.subscribeToSaveResponse(this.sysMenuService.create(sysMenu));
    }
  }

  private createFromForm(): ISysMenu {
    return {
      ...new SysMenu(),
      id: this.editForm.get(['id'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      deleted: this.editForm.get(['deleted'])!.value,
      code: this.editForm.get(['code'])!.value,
      parentIds: this.editForm.get(['parentIds'])!.value,
      siblingSort: this.editForm.get(['siblingSort'])!.value,
      globalSort: this.editForm.get(['globalSort'])!.value,
      leaf: this.editForm.get(['leaf'])!.value,
      level: this.editForm.get(['level'])!.value,
      shortName: this.editForm.get(['shortName'])!.value,
      fullName: this.editForm.get(['fullName'])!.value,
      href: this.editForm.get(['href'])!.value,
      icon: this.editForm.get(['icon'])!.value,
      visible: this.editForm.get(['visible'])!.value,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysMenu>>): void {
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
