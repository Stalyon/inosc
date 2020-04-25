import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParseHistory, ParseHistory } from 'app/shared/model/parse-history.model';
import { ParseHistoryService } from './parse-history.service';
import { ParseHistoryComponent } from './parse-history.component';
import { ParseHistoryDetailComponent } from './parse-history-detail.component';
import { ParseHistoryUpdateComponent } from './parse-history-update.component';

@Injectable({ providedIn: 'root' })
export class ParseHistoryResolve implements Resolve<IParseHistory> {
  constructor(private service: ParseHistoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParseHistory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((parseHistory: HttpResponse<ParseHistory>) => {
          if (parseHistory.body) {
            return of(parseHistory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParseHistory());
  }
}

export const parseHistoryRoute: Routes = [
  {
    path: '',
    component: ParseHistoryComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ParseHistories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ParseHistoryDetailComponent,
    resolve: {
      parseHistory: ParseHistoryResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ParseHistories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ParseHistoryUpdateComponent,
    resolve: {
      parseHistory: ParseHistoryResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ParseHistories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ParseHistoryUpdateComponent,
    resolve: {
      parseHistory: ParseHistoryResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ParseHistories'
    },
    canActivate: [UserRouteAccessService]
  }
];
