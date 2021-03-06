import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParseHistory, ParseHistory } from 'app/shared/model/parse-history.model';
import { ParseHistoryService } from './parse-history.service';

@Component({
  selector: 'jhi-parse-history-update',
  templateUrl: './parse-history-update.component.html'
})
export class ParseHistoryUpdateComponent implements OnInit {
  isSaving = false;
  parsedDateDp: any;

  editForm = this.fb.group({
    id: [],
    fileName: [],
    fileSize: [],
    parsedDate: []
  });

  constructor(protected parseHistoryService: ParseHistoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parseHistory }) => {
      this.updateForm(parseHistory);
    });
  }

  updateForm(parseHistory: IParseHistory): void {
    this.editForm.patchValue({
      id: parseHistory.id,
      fileName: parseHistory.fileName,
      fileSize: parseHistory.fileSize,
      parsedDate: parseHistory.parsedDate
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const parseHistory = this.createFromForm();
    if (parseHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.parseHistoryService.update(parseHistory));
    } else {
      this.subscribeToSaveResponse(this.parseHistoryService.create(parseHistory));
    }
  }

  private createFromForm(): IParseHistory {
    return {
      ...new ParseHistory(),
      id: this.editForm.get(['id'])!.value,
      fileName: this.editForm.get(['fileName'])!.value,
      fileSize: this.editForm.get(['fileSize'])!.value,
      parsedDate: this.editForm.get(['parsedDate'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParseHistory>>): void {
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
