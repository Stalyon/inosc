import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParseHistory } from 'app/shared/model/parse-history.model';

@Component({
  selector: 'jhi-parse-history-detail',
  templateUrl: './parse-history-detail.component.html'
})
export class ParseHistoryDetailComponent implements OnInit {
  parseHistory: IParseHistory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parseHistory }) => (this.parseHistory = parseHistory));
  }

  previousState(): void {
    window.history.back();
  }
}
