import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOperationLog } from 'app/shared/model/operation-log.model';

type EntityResponseType = HttpResponse<IOperationLog>;
type EntityArrayResponseType = HttpResponse<IOperationLog[]>;

@Injectable({ providedIn: 'root' })
export class OperationLogService {
  public resourceUrl = SERVER_API_URL + 'api/operation-logs';

  constructor(protected http: HttpClient) {}

  create(operationLog: IOperationLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operationLog);
    return this.http
      .post<IOperationLog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(operationLog: IOperationLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operationLog);
    return this.http
      .put<IOperationLog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOperationLog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOperationLog[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(operationLog: IOperationLog): IOperationLog {
    const copy: IOperationLog = Object.assign({}, operationLog, {
      operationTime: operationLog.operationTime && operationLog.operationTime.isValid() ? operationLog.operationTime.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.operationTime = res.body.operationTime ? moment(res.body.operationTime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((operationLog: IOperationLog) => {
        operationLog.operationTime = operationLog.operationTime ? moment(operationLog.operationTime) : undefined;
      });
    }
    return res;
  }
}
