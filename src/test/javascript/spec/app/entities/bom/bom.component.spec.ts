import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InoscTestModule } from '../../../test.module';
import { BomComponent } from 'app/entities/bom/bom.component';
import { BomService } from 'app/entities/bom/bom.service';
import { Bom } from 'app/shared/model/bom.model';

describe('Component Tests', () => {
  describe('Bom Management Component', () => {
    let comp: BomComponent;
    let fixture: ComponentFixture<BomComponent>;
    let service: BomService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InoscTestModule],
        declarations: [BomComponent]
      })
        .overrideTemplate(BomComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BomComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BomService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Bom(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.boms && comp.boms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
