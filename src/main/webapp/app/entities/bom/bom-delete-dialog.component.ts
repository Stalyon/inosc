import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBom } from 'app/shared/model/bom.model';
import { BomService } from './bom.service';

@Component({
  templateUrl: './bom-delete-dialog.component.html'
})
export class BomDeleteDialogComponent {
  bom?: IBom;

  constructor(protected bomService: BomService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bomService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bomListModification');
      this.activeModal.close();
    });
  }
}
