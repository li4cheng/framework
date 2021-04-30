import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISysMenu, SysMenu } from 'app/shared/model/sys-menu.model';
import { SysMenuService } from './sys-menu.service';
import { SysMenuComponent } from './sys-menu.component';
import { SysMenuDetailComponent } from './sys-menu-detail.component';
import { SysMenuUpdateComponent } from './sys-menu-update.component';

@Injectable({ providedIn: 'root' })
export class SysMenuResolve implements Resolve<ISysMenu> {
  constructor(private service: SysMenuService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysMenu> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sysMenu: HttpResponse<SysMenu>) => {
          if (sysMenu.body) {
            return of(sysMenu.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SysMenu());
  }
}

export const sysMenuRoute: Routes = [
  {
    path: '',
    component: SysMenuComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'frameworkApp.sysMenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysMenuDetailComponent,
    resolve: {
      sysMenu: SysMenuResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'frameworkApp.sysMenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysMenuUpdateComponent,
    resolve: {
      sysMenu: SysMenuResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'frameworkApp.sysMenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysMenuUpdateComponent,
    resolve: {
      sysMenu: SysMenuResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'frameworkApp.sysMenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
