import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InoscTestModule } from '../../../test.module';
import { BomDetailComponent } from 'app/entities/bom/bom-detail.component';
import { Bom } from 'app/shared/model/bom.model';

describe('Component Tests', () => {
  describe('Bom Management Detail Component', () => {
    let comp: BomDetailComponent;
    let fixture: ComponentFixture<BomDetailComponent>;
    const route = ({ data: of({ bom: new Bom(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InoscTestModule],
        declarations: [BomDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BomDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BomDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bom on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bom).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
