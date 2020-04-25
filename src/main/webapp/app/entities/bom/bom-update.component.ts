import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBom, Bom } from 'app/shared/model/bom.model';
import { BomService } from './bom.service';

@Component({
  selector: 'jhi-bom-update',
  templateUrl: './bom-update.component.html'
})
export class BomUpdateComponent implements OnInit {
  isSaving = false;
  effDateDp: any;
  disDateDp: any;

  editForm = this.fb.group({
    id: [],
    itemNumber: [null, [Validators.required, Validators.maxLength(18)]],
    bomOperationNumber: [],
    bomSequenceNumber: [],
    componentNumber: [null, [Validators.required, Validators.maxLength(18)]],
    quantity: [null, [Validators.required]],
    bomYield: [null, [Validators.required]],
    effDate: [],
    disDate: []
  });

  constructor(protected bomService: BomService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bom }) => {
      this.updateForm(bom);
    });
  }

  updateForm(bom: IBom): void {
    this.editForm.patchValue({
      id: bom.id,
      itemNumber: bom.itemNumber,
      bomOperationNumber: bom.bomOperationNumber,
      bomSequenceNumber: bom.bomSequenceNumber,
      componentNumber: bom.componentNumber,
      quantity: bom.quantity,
      bomYield: bom.bomYield,
      effDate: bom.effDate,
      disDate: bom.disDate
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bom = this.createFromForm();
    if (bom.id !== undefined) {
      this.subscribeToSaveResponse(this.bomService.update(bom));
    } else {
      this.subscribeToSaveResponse(this.bomService.create(bom));
    }
  }

  private createFromForm(): IBom {
    return {
      ...new Bom(),
      id: this.editForm.get(['id'])!.value,
      itemNumber: this.editForm.get(['itemNumber'])!.value,
      bomOperationNumber: this.editForm.get(['bomOperationNumber'])!.value,
      bomSequenceNumber: this.editForm.get(['bomSequenceNumber'])!.value,
      componentNumber: this.editForm.get(['componentNumber'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      bomYield: this.editForm.get(['bomYield'])!.value,
      effDate: this.editForm.get(['effDate'])!.value,
      disDate: this.editForm.get(['disDate'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBom>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
