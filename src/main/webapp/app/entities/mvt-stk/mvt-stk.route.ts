import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMvtStk, MvtStk } from 'app/shared/model/mvt-stk.model';
import { MvtStkService } from './mvt-stk.service';
import { MvtStkComponent } from './mvt-stk.component';
import { MvtStkDetailComponent } from './mvt-stk-detail.component';
import { MvtStkUpdateComponent } from './mvt-stk-update.component';

@Injectable({ providedIn: 'root' })
export class MvtStkResolve implements Resolve<IMvtStk> {
  constructor(private service: MvtStkService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMvtStk> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mvtStk: HttpResponse<MvtStk>) => {
          if (mvtStk.body) {
            return of(mvtStk.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MvtStk());
  }
}

export const mvtStkRoute: Routes = [
  {
    path: '',
    component: MvtStkComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MvtStks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MvtStkDetailComponent,
    resolve: {
      mvtStk: MvtStkResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MvtStks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MvtStkUpdateComponent,
    resolve: {
      mvtStk: MvtStkResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MvtStks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MvtStkUpdateComponent,
    resolve: {
      mvtStk: MvtStkResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MvtStks'
    },
    canActivate: [UserRouteAccessService]
  }
];
