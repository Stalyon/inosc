import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMvtStk } from 'app/shared/model/mvt-stk.model';
import { MvtStkService } from './mvt-stk.service';
import { MvtStkDeleteDialogComponent } from './mvt-stk-delete-dialog.component';

@Component({
  selector: 'jhi-mvt-stk',
  templateUrl: './mvt-stk.component.html'
})
export class MvtStkComponent implements OnInit, OnDestroy {
  mvtStks?: IMvtStk[];
  eventSubscriber?: Subscription;

  constructor(protected mvtStkService: MvtStkService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.mvtStkService.query().subscribe((res: HttpResponse<IMvtStk[]>) => (this.mvtStks = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMvtStks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMvtStk): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMvtStks(): void {
    this.eventSubscriber = this.eventManager.subscribe('mvtStkListModification', () => this.loadAll());
  }

  delete(mvtStk: IMvtStk): void {
    const modalRef = this.modalService.open(MvtStkDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mvtStk = mvtStk;
  }
}
