import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParseHistory } from 'app/shared/model/parse-history.model';
import { ParseHistoryService } from './parse-history.service';

@Component({
  templateUrl: './parse-history-delete-dialog.component.html'
})
export class ParseHistoryDeleteDialogComponent {
  parseHistory?: IParseHistory;

  constructor(
    protected parseHistoryService: ParseHistoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.parseHistoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('parseHistoryListModification');
      this.activeModal.close();
    });
  }
}
