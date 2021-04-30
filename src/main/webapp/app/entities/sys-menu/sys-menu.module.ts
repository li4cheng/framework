import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrameworkSharedModule } from 'app/shared/shared.module';
import { SysMenuComponent } from './sys-menu.component';
import { SysMenuDetailComponent } from './sys-menu-detail.component';
import { SysMenuUpdateComponent } from './sys-menu-update.component';
import { SysMenuDeleteDialogComponent } from './sys-menu-delete-dialog.component';
import { sysMenuRoute } from './sys-menu.route';

@NgModule({
  imports: [FrameworkSharedModule, RouterModule.forChild(sysMenuRoute)],
  declarations: [SysMenuComponent, SysMenuDetailComponent, SysMenuUpdateComponent, SysMenuDeleteDialogComponent],
  entryComponents: [SysMenuDeleteDialogComponent],
})
export class FrameworkSysMenuModule {}
