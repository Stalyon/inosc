import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MvtStkService } from 'app/entities/mvt-stk/mvt-stk.service';
import { IMvtStk, MvtStk } from 'app/shared/model/mvt-stk.model';

describe('Service Tests', () => {
  describe('MvtStk Service', () => {
    let injector: TestBed;
    let service: MvtStkService;
    let httpMock: HttpTestingController;
    let elemDefault: IMvtStk;
    let expectedResult: IMvtStk | IMvtStk[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MvtStkService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MvtStk(0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateMvt: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MvtStk', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateMvt: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateMvt: currentDate
          },
          returnedFromService
        );

        service.create(new MvtStk()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MvtStk', () => {
        const returnedFromService = Object.assign(
          {
            dateMvt: currentDate.format(DATE_FORMAT),
            codeMvt: 'BBBBBB',
            itemNumber: 'BBBBBB',
            magasin: 'BBBBBB',
            emplacement: 'BBBBBB',
            qte: 1,
            numOrdre: 'BBBBBB',
            numLigneOrdre: 'BBBBBB',
            lotNumber: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateMvt: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MvtStk', () => {
        const returnedFromService = Object.assign(
          {
            dateMvt: currentDate.format(DATE_FORMAT),
            codeMvt: 'BBBBBB',
            itemNumber: 'BBBBBB',
            magasin: 'BBBBBB',
            emplacement: 'BBBBBB',
            qte: 1,
            numOrdre: 'BBBBBB',
            numLigneOrdre: 'BBBBBB',
            lotNumber: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateMvt: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MvtStk', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
