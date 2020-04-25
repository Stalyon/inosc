import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBom } from 'app/shared/model/bom.model';

type EntityResponseType = HttpResponse<IBom>;
type EntityArrayResponseType = HttpResponse<IBom[]>;

@Injectable({ providedIn: 'root' })
export class BomService {
  public resourceUrl = SERVER_API_URL + 'api/boms';

  constructor(protected http: HttpClient) {}

  create(bom: IBom): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bom);
    return this.http
      .post<IBom>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bom: IBom): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bom);
    return this.http
      .put<IBom>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBom>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBom[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bom: IBom): IBom {
    const copy: IBom = Object.assign({}, bom, {
      effDate: bom.effDate && bom.effDate.isValid() ? bom.effDate.format(DATE_FORMAT) : undefined,
      disDate: bom.disDate && bom.disDate.isValid() ? bom.disDate.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.effDate = res.body.effDate ? moment(res.body.effDate) : undefined;
      res.body.disDate = res.body.disDate ? moment(res.body.disDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bom: IBom) => {
        bom.effDate = bom.effDate ? moment(bom.effDate) : undefined;
        bom.disDate = bom.disDate ? moment(bom.disDate) : undefined;
      });
    }
    return res;
  }
}
