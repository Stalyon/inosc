import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMvtStk } from 'app/shared/model/mvt-stk.model';
import { MvtStkService } from './mvt-stk.service';

@Component({
  templateUrl: './mvt-stk-delete-dialog.component.html'
})
export class MvtStkDeleteDialogComponent {
  mvtStk?: IMvtStk;

  constructor(protected mvtStkService: MvtStkService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mvtStkService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mvtStkListModification');
      this.activeModal.close();
    });
  }
}
