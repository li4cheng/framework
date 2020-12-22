import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOperationLog, OperationLog } from 'app/shared/model/operation-log.model';
import { OperationLogService } from './operation-log.service';
import { OperationLogComponent } from './operation-log.component';
import { OperationLogDetailComponent } from './operation-log-detail.component';
import { OperationLogUpdateComponent } from './operation-log-update.component';

@Injectable({ providedIn: 'root' })
export class OperationLogResolve implements Resolve<IOperationLog> {
  constructor(private service: OperationLogService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOperationLog> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((operationLog: HttpResponse<OperationLog>) => {
          if (operationLog.body) {
            return of(operationLog.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OperationLog());
  }
}

export const operationLogRoute: Routes = [
  {
    path: '',
    component: OperationLogComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'frameworkApp.operationLog.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OperationLogDetailComponent,
    resolve: {
      operationLog: OperationLogResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'frameworkApp.operationLog.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OperationLogUpdateComponent,
    resolve: {
      operationLog: OperationLogResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'frameworkApp.operationLog.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OperationLogUpdateComponent,
    resolve: {
      operationLog: OperationLogResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'frameworkApp.operationLog.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
