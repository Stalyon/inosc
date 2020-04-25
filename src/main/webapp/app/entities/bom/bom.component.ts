import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBom } from 'app/shared/model/bom.model';
import { BomService } from './bom.service';
import { BomDeleteDialogComponent } from './bom-delete-dialog.component';

@Component({
  selector: 'jhi-bom',
  templateUrl: './bom.component.html'
})
export class BomComponent implements OnInit, OnDestroy {
  boms?: IBom[];
  eventSubscriber?: Subscription;

  constructor(protected bomService: BomService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.bomService.query().subscribe((res: HttpResponse<IBom[]>) => (this.boms = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBoms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBom): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBoms(): void {
    this.eventSubscriber = this.eventManager.subscribe('bomListModification', () => this.loadAll());
  }

  delete(bom: IBom): void {
    const modalRef = this.modalService.open(BomDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bom = bom;
  }
}
