import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InoscTestModule } from '../../../test.module';
import { MvtStkDetailComponent } from 'app/entities/mvt-stk/mvt-stk-detail.component';
import { MvtStk } from 'app/shared/model/mvt-stk.model';

describe('Component Tests', () => {
  describe('MvtStk Management Detail Component', () => {
    let comp: MvtStkDetailComponent;
    let fixture: ComponentFixture<MvtStkDetailComponent>;
    const route = ({ data: of({ mvtStk: new MvtStk(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InoscTestModule],
        declarations: [MvtStkDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MvtStkDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MvtStkDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mvtStk on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mvtStk).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
