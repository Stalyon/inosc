import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ItemService } from 'app/entities/item/item.service';
import { IItem, Item } from 'app/shared/model/item.model';

describe('Service Tests', () => {
  describe('Item Service', () => {
    let injector: TestBed;
    let service: ItemService;
    let httpMock: HttpTestingController;
    let elemDefault: IItem;
    let expectedResult: IItem | IItem[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ItemService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Item(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            slEffectiveFromDate: currentDate.format(DATE_FORMAT),
            itemCreationDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Item', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            slEffectiveFromDate: currentDate.format(DATE_FORMAT),
            itemCreationDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            slEffectiveFromDate: currentDate,
            itemCreationDate: currentDate
          },
          returnedFromService
        );

        service.create(new Item()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Item', () => {
        const returnedFromService = Object.assign(
          {
            organizationCode: 'BBBBBB',
            businessUnit: 'BBBBBB',
            businessUnitDescription: 'BBBBBB',
            businessField: 'BBBBBB',
            businessFieldDescription: 'BBBBBB',
            businessLine: 'BBBBBB',
            businessLineDescription: 'BBBBBB',
            workcenter: 'BBBBBB',
            productGroup: 'BBBBBB',
            productGroupDescription: 'BBBBBB',
            productCategory: 'BBBBBB',
            productCategoryDescription: 'BBBBBB',
            productLine: 'BBBBBB',
            productLineDescription: 'BBBBBB',
            productLineManager: 'BBBBBB',
            productDivision: 'BBBBBB',
            productCategoryManager: 'BBBBBB',
            plantCategory: 'BBBBBB',
            itemType: 'BBBBBB',
            primaryUnitOfMeasure: 'BBBBBB',
            itemNumber: 'BBBBBB',
            itemDescription: 'BBBBBB',
            inventoryItemStatusCode: 'BBBBBB',
            planningMakeOrBuyCode: 'BBBBBB',
            itemPlannerCode: 'BBBBBB',
            plannerName: 'BBBBBB',
            mrpPlanningMethod: 'BBBBBB',
            inventoryPlanningMethod: 'BBBBBB',
            leadTimeLotSize: 1,
            fixedLeadTime: 1,
            fixedOrderQuanitiy: 1,
            fixedLotMultiplier: 'BBBBBB',
            currentSafetyStockQuantity: 1,
            demandTimeFence: 'BBBBBB',
            demandTimeFenceDays: 1,
            fixedDaysSupply: 1,
            frozenItemCost: 1,
            frozenMaterialCost: 1,
            maxMinmaxQuantity: 1,
            minMinmaxQuantity: 1,
            orderQuantityMax: 1,
            orderQuantityMin: 1,
            planningTimeFenceDays: 1,
            postprocessingLeadTime: 1,
            preprocessingLeadTime: 1,
            processingLeadTime: 1,
            mdmGoHCategory: 'BBBBBB',
            resourceName: 'BBBBBB',
            usageRate: 1,
            aslVmiEnabled: 'BBBBBB',
            aslConsignedFromSupplier: 'BBBBBB',
            aslSupplier: 'BBBBBB',
            aslSupplierNumber: 'BBBBBB',
            aslsupplierSite: 'BBBBBB',
            aslSupplierItem: 'BBBBBB',
            aslpurchasingUom: 'BBBBBB',
            slProcessCodeDetail: 'BBBBBB',
            slEffectiveFromDate: currentDate.format(DATE_FORMAT),
            receiptRoutingDetail: 'BBBBBB',
            vmiPlanningParty: 'BBBBBB',
            listPrice: 1,
            mdmGlobalId: 'BBBBBB',
            mdmArticleNumberYgart: 'BBBBBB',
            lotControl: 'BBBBBB',
            shelfLifeControl: 'BBBBBB',
            shelfLifeDays: 1,
            serialNumberControl: 'BBBBBB',
            unitVolume: 1,
            volumeUnitOfMeasure: 'BBBBBB',
            unitWeight: 1,
            weightUnitOfMeasure: 'BBBBBB',
            unitsInABoxPackage: 1,
            revisionControl: 'BBBBBB',
            dimensionUnitHeight: 1,
            dimensionUnitLength: 1,
            dimensionUnitWidth: 1,
            dimensionUnitOfMeasure: 'BBBBBB',
            hazardousWeight: 'BBBBBB',
            franceAbcPickingCategory: 'BBBBBB',
            mdmTempConditionsCategory: 'BBBBBB',
            perishCodeCategory: 'BBBBBB',
            unNumber: 'BBBBBB',
            hazardClass: 'BBBBBB',
            unitOfIssue: 'BBBBBB',
            roundingFactor: 'BBBBBB',
            itemBuyerName: 'BBBBBB',
            hazardousWeightUom: 'BBBBBB',
            gphCode: 'BBBBBB',
            mos: 'BBBBBB',
            lpnPackage: 'BBBBBB',
            qteUnitairePalette: 1,
            typeUm: 'BBBBBB',
            hauteurUm: 1,
            longueurUm: 1,
            largeurUm: 1,
            qteUnitaireCarton: 1,
            dimensionsUc: 'BBBBBB',
            specifications: 'BBBBBB',
            cadencePoste: 'BBBBBB',
            pfse: 'BBBBBB',
            rhenus: 'BBBBBB',
            testCapa: 'BBBBBB',
            abc: 'BBBBBB',
            itemCreationDate: currentDate.format(DATE_FORMAT),
            categorieNm: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            slEffectiveFromDate: currentDate,
            itemCreationDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Item', () => {
        const returnedFromService = Object.assign(
          {
            organizationCode: 'BBBBBB',
            businessUnit: 'BBBBBB',
            businessUnitDescription: 'BBBBBB',
            businessField: 'BBBBBB',
            businessFieldDescription: 'BBBBBB',
            businessLine: 'BBBBBB',
            businessLineDescription: 'BBBBBB',
            workcenter: 'BBBBBB',
            productGroup: 'BBBBBB',
            productGroupDescription: 'BBBBBB',
            productCategory: 'BBBBBB',
            productCategoryDescription: 'BBBBBB',
            productLine: 'BBBBBB',
            productLineDescription: 'BBBBBB',
            productLineManager: 'BBBBBB',
            productDivision: 'BBBBBB',
            productCategoryManager: 'BBBBBB',
            plantCategory: 'BBBBBB',
            itemType: 'BBBBBB',
            primaryUnitOfMeasure: 'BBBBBB',
            itemNumber: 'BBBBBB',
            itemDescription: 'BBBBBB',
            inventoryItemStatusCode: 'BBBBBB',
            planningMakeOrBuyCode: 'BBBBBB',
            itemPlannerCode: 'BBBBBB',
            plannerName: 'BBBBBB',
            mrpPlanningMethod: 'BBBBBB',
            inventoryPlanningMethod: 'BBBBBB',
            leadTimeLotSize: 1,
            fixedLeadTime: 1,
            fixedOrderQuanitiy: 1,
            fixedLotMultiplier: 'BBBBBB',
            currentSafetyStockQuantity: 1,
            demandTimeFence: 'BBBBBB',
            demandTimeFenceDays: 1,
            fixedDaysSupply: 1,
            frozenItemCost: 1,
            frozenMaterialCost: 1,
            maxMinmaxQuantity: 1,
            minMinmaxQuantity: 1,
            orderQuantityMax: 1,
            orderQuantityMin: 1,
            planningTimeFenceDays: 1,
            postprocessingLeadTime: 1,
            preprocessingLeadTime: 1,
            processingLeadTime: 1,
            mdmGoHCategory: 'BBBBBB',
            resourceName: 'BBBBBB',
            usageRate: 1,
            aslVmiEnabled: 'BBBBBB',
            aslConsignedFromSupplier: 'BBBBBB',
            aslSupplier: 'BBBBBB',
            aslSupplierNumber: 'BBBBBB',
            aslsupplierSite: 'BBBBBB',
            aslSupplierItem: 'BBBBBB',
            aslpurchasingUom: 'BBBBBB',
            slProcessCodeDetail: 'BBBBBB',
            slEffectiveFromDate: currentDate.format(DATE_FORMAT),
            receiptRoutingDetail: 'BBBBBB',
            vmiPlanningParty: 'BBBBBB',
            listPrice: 1,
            mdmGlobalId: 'BBBBBB',
            mdmArticleNumberYgart: 'BBBBBB',
            lotControl: 'BBBBBB',
            shelfLifeControl: 'BBBBBB',
            shelfLifeDays: 1,
            serialNumberControl: 'BBBBBB',
            unitVolume: 1,
            volumeUnitOfMeasure: 'BBBBBB',
            unitWeight: 1,
            weightUnitOfMeasure: 'BBBBBB',
            unitsInABoxPackage: 1,
            revisionControl: 'BBBBBB',
            dimensionUnitHeight: 1,
            dimensionUnitLength: 1,
            dimensionUnitWidth: 1,
            dimensionUnitOfMeasure: 'BBBBBB',
            hazardousWeight: 'BBBBBB',
            franceAbcPickingCategory: 'BBBBBB',
            mdmTempConditionsCategory: 'BBBBBB',
            perishCodeCategory: 'BBBBBB',
            unNumber: 'BBBBBB',
            hazardClass: 'BBBBBB',
            unitOfIssue: 'BBBBBB',
            roundingFactor: 'BBBBBB',
            itemBuyerName: 'BBBBBB',
            hazardousWeightUom: 'BBBBBB',
            gphCode: 'BBBBBB',
            mos: 'BBBBBB',
            lpnPackage: 'BBBBBB',
            qteUnitairePalette: 1,
            typeUm: 'BBBBBB',
            hauteurUm: 1,
            longueurUm: 1,
            largeurUm: 1,
            qteUnitaireCarton: 1,
            dimensionsUc: 'BBBBBB',
            specifications: 'BBBBBB',
            cadencePoste: 'BBBBBB',
            pfse: 'BBBBBB',
            rhenus: 'BBBBBB',
            testCapa: 'BBBBBB',
            abc: 'BBBBBB',
            itemCreationDate: currentDate.format(DATE_FORMAT),
            categorieNm: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            slEffectiveFromDate: currentDate,
            itemCreationDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Item', () => {
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
