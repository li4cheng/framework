import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrameworkSharedModule } from 'app/shared/shared.module';
import { OperationLogComponent } from './operation-log.component';
import { OperationLogDetailComponent } from './operation-log-detail.component';
import { OperationLogUpdateComponent } from './operation-log-update.component';
import { OperationLogDeleteDialogComponent } from './operation-log-delete-dialog.component';
import { operationLogRoute } from './operation-log.route';

@NgModule({
  imports: [FrameworkSharedModule, RouterModule.forChild(operationLogRoute)],
  declarations: [OperationLogComponent, OperationLogDetailComponent, OperationLogUpdateComponent, OperationLogDeleteDialogComponent],
  entryComponents: [OperationLogDeleteDialogComponent],
})
export class FrameworkOperationLogModule {}
