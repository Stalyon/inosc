import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMvtStk, MvtStk } from 'app/shared/model/mvt-stk.model';
import { MvtStkService } from './mvt-stk.service';

@Component({
  selector: 'jhi-mvt-stk-update',
  templateUrl: './mvt-stk-update.component.html'
})
export class MvtStkUpdateComponent implements OnInit {
  isSaving = false;
  dateMvtDp: any;

  editForm = this.fb.group({
    id: [],
    dateMvt: [null, [Validators.required]],
    codeMvt: [null, [Validators.maxLength(3)]],
    itemNumber: [null, [Validators.required, Validators.maxLength(18)]],
    magasin: [null, [Validators.maxLength(20)]],
    emplacement: [null, [Validators.maxLength(20)]],
    qte: [null, [Validators.required]],
    numOrdre: [null, [Validators.maxLength(12)]],
    numLigneOrdre: [null, [Validators.maxLength(4)]],
    lotNumber: [null, [Validators.maxLength(12)]]
  });

  constructor(protected mvtStkService: MvtStkService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mvtStk }) => {
      this.updateForm(mvtStk);
    });
  }

  updateForm(mvtStk: IMvtStk): void {
    this.editForm.patchValue({
      id: mvtStk.id,
      dateMvt: mvtStk.dateMvt,
      codeMvt: mvtStk.codeMvt,
      itemNumber: mvtStk.itemNumber,
      magasin: mvtStk.magasin,
      emplacement: mvtStk.emplacement,
      qte: mvtStk.qte,
      numOrdre: mvtStk.numOrdre,
      numLigneOrdre: mvtStk.numLigneOrdre,
      lotNumber: mvtStk.lotNumber
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mvtStk = this.createFromForm();
    if (mvtStk.id !== undefined) {
      this.subscribeToSaveResponse(this.mvtStkService.update(mvtStk));
    } else {
      this.subscribeToSaveResponse(this.mvtStkService.create(mvtStk));
    }
  }

  private createFromForm(): IMvtStk {
    return {
      ...new MvtStk(),
      id: this.editForm.get(['id'])!.value,
      dateMvt: this.editForm.get(['dateMvt'])!.value,
      codeMvt: this.editForm.get(['codeMvt'])!.value,
      itemNumber: this.editForm.get(['itemNumber'])!.value,
      magasin: this.editForm.get(['magasin'])!.value,
      emplacement: this.editForm.get(['emplacement'])!.value,
      qte: this.editForm.get(['qte'])!.value,
      numOrdre: this.editForm.get(['numOrdre'])!.value,
      numLigneOrdre: this.editForm.get(['numLigneOrdre'])!.value,
      lotNumber: this.editForm.get(['lotNumber'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMvtStk>>): void {
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
