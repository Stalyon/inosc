import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { InoscTestModule } from '../../../test.module';
import { BomUpdateComponent } from 'app/entities/bom/bom-update.component';
import { BomService } from 'app/entities/bom/bom.service';
import { Bom } from 'app/shared/model/bom.model';

describe('Component Tests', () => {
  describe('Bom Management Update Component', () => {
    let comp: BomUpdateComponent;
    let fixture: ComponentFixture<BomUpdateComponent>;
    let service: BomService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InoscTestModule],
        declarations: [BomUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BomUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BomUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BomService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bom(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bom();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
