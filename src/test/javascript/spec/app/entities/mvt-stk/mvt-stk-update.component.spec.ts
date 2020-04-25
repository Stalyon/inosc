import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { InoscTestModule } from '../../../test.module';
import { MvtStkUpdateComponent } from 'app/entities/mvt-stk/mvt-stk-update.component';
import { MvtStkService } from 'app/entities/mvt-stk/mvt-stk.service';
import { MvtStk } from 'app/shared/model/mvt-stk.model';

describe('Component Tests', () => {
  describe('MvtStk Management Update Component', () => {
    let comp: MvtStkUpdateComponent;
    let fixture: ComponentFixture<MvtStkUpdateComponent>;
    let service: MvtStkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InoscTestModule],
        declarations: [MvtStkUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MvtStkUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MvtStkUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MvtStkService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MvtStk(123);
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
        const entity = new MvtStk();
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
