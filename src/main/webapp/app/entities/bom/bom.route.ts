import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBom, Bom } from 'app/shared/model/bom.model';
import { BomService } from './bom.service';
import { BomComponent } from './bom.component';
import { BomDetailComponent } from './bom-detail.component';
import { BomUpdateComponent } from './bom-update.component';

@Injectable({ providedIn: 'root' })
export class BomResolve implements Resolve<IBom> {
  constructor(private service: BomService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBom> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bom: HttpResponse<Bom>) => {
          if (bom.body) {
            return of(bom.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bom());
  }
}

export const bomRoute: Routes = [
  {
    path: '',
    component: BomComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Boms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BomDetailComponent,
    resolve: {
      bom: BomResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Boms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BomUpdateComponent,
    resolve: {
      bom: BomResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Boms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BomUpdateComponent,
    resolve: {
      bom: BomResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Boms'
    },
    canActivate: [UserRouteAccessService]
  }
];
