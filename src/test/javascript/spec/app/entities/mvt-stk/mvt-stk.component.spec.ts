import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InoscTestModule } from '../../../test.module';
import { MvtStkComponent } from 'app/entities/mvt-stk/mvt-stk.component';
import { MvtStkService } from 'app/entities/mvt-stk/mvt-stk.service';
import { MvtStk } from 'app/shared/model/mvt-stk.model';

describe('Component Tests', () => {
  describe('MvtStk Management Component', () => {
    let comp: MvtStkComponent;
    let fixture: ComponentFixture<MvtStkComponent>;
    let service: MvtStkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InoscTestModule],
        declarations: [MvtStkComponent]
      })
        .overrideTemplate(MvtStkComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MvtStkComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MvtStkService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MvtStk(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mvtStks && comp.mvtStks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
