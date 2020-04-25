import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BomService } from 'app/entities/bom/bom.service';
import { IBom, Bom } from 'app/shared/model/bom.model';

describe('Service Tests', () => {
  describe('Bom Service', () => {
    let injector: TestBed;
    let service: BomService;
    let httpMock: HttpTestingController;
    let elemDefault: IBom;
    let expectedResult: IBom | IBom[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BomService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bom(0, 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 0, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            effDate: currentDate.format(DATE_FORMAT),
            disDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bom', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            effDate: currentDate.format(DATE_FORMAT),
            disDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effDate: currentDate,
            disDate: currentDate
          },
          returnedFromService
        );

        service.create(new Bom()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bom', () => {
        const returnedFromService = Object.assign(
          {
            itemNumber: 'BBBBBB',
            bomOperationNumber: 1,
            bomSequenceNumber: 1,
            componentNumber: 'BBBBBB',
            quantity: 1,
            bomYield: 1,
            effDate: currentDate.format(DATE_FORMAT),
            disDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effDate: currentDate,
            disDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bom', () => {
        const returnedFromService = Object.assign(
          {
            itemNumber: 'BBBBBB',
            bomOperationNumber: 1,
            bomSequenceNumber: 1,
            componentNumber: 'BBBBBB',
            quantity: 1,
            bomYield: 1,
            effDate: currentDate.format(DATE_FORMAT),
            disDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effDate: currentDate,
            disDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Bom', () => {
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
