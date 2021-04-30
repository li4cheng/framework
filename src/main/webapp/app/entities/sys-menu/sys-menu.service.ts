import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISysMenu } from 'app/shared/model/sys-menu.model';

type EntityResponseType = HttpResponse<ISysMenu>;
type EntityArrayResponseType = HttpResponse<ISysMenu[]>;

@Injectable({ providedIn: 'root' })
export class SysMenuService {
  public resourceUrl = SERVER_API_URL + 'api/sys-menus';

  constructor(protected http: HttpClient) {}

  create(sysMenu: ISysMenu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysMenu);
    return this.http
      .post<ISysMenu>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sysMenu: ISysMenu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysMenu);
    return this.http
      .put<ISysMenu>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISysMenu>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISysMenu[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sysMenu: ISysMenu): ISysMenu {
    const copy: ISysMenu = Object.assign({}, sysMenu, {
      createdDate: sysMenu.createdDate && sysMenu.createdDate.isValid() ? sysMenu.createdDate.toJSON() : undefined,
      lastModifiedDate: sysMenu.lastModifiedDate && sysMenu.lastModifiedDate.isValid() ? sysMenu.lastModifiedDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sysMenu: ISysMenu) => {
        sysMenu.createdDate = sysMenu.createdDate ? moment(sysMenu.createdDate) : undefined;
        sysMenu.lastModifiedDate = sysMenu.lastModifiedDate ? moment(sysMenu.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
