import { Moment } from 'moment';

export interface IItem {
  id?: number;
  organizationCode?: string;
  businessUnit?: string;
  businessUnitDescription?: string;
  businessField?: string;
  businessFieldDescription?: string;
  businessLine?: string;
  businessLineDescription?: string;
  workcenter?: string;
  productGroup?: string;
  productGroupDescription?: string;
  productCategory?: string;
  productCategoryDescription?: string;
  productLine?: string;
  productLineDescription?: string;
  productLineManager?: string;
  productDivision?: string;
  productCategoryManager?: string;
  plantCategory?: string;
  itemType?: string;
  primaryUnitOfMeasure?: string;
  itemNumber?: string;
  itemDescription?: string;
  inventoryItemStatusCode?: string;
  planningMakeOrBuyCode?: string;
  itemPlannerCode?: string;
  plannerName?: string;
  mrpPlanningMethod?: string;
  inventoryPlanningMethod?: string;
  leadTimeLotSize?: number;
  fixedLeadTime?: number;
  fixedOrderQuanitiy?: number;
  fixedLotMultiplier?: string;
  currentSafetyStockQuantity?: number;
  demandTimeFence?: string;
  demandTimeFenceDays?: number;
  fixedDaysSupply?: number;
  frozenItemCost?: number;
  frozenMaterialCost?: number;
  maxMinmaxQuantity?: number;
  minMinmaxQuantity?: number;
  orderQuantityMax?: number;
  orderQuantityMin?: number;
  planningTimeFenceDays?: number;
  postprocessingLeadTime?: number;
  preprocessingLeadTime?: number;
  processingLeadTime?: number;
  mdmGoHCategory?: string;
  resourceName?: string;
  usageRate?: number;
  aslVmiEnabled?: string;
  aslConsignedFromSupplier?: string;
  aslSupplier?: string;
  aslSupplierNumber?: string;
  aslsupplierSite?: string;
  aslSupplierItem?: string;
  aslpurchasingUom?: string;
  slProcessCodeDetail?: string;
  slEffectiveFromDate?: Moment;
  receiptRoutingDetail?: string;
  vmiPlanningParty?: string;
  listPrice?: number;
  mdmGlobalId?: string;
  mdmArticleNumberYgart?: string;
  lotControl?: string;
  shelfLifeControl?: string;
  shelfLifeDays?: number;
  serialNumberControl?: string;
  unitVolume?: number;
  volumeUnitOfMeasure?: string;
  unitWeight?: number;
  weightUnitOfMeasure?: string;
  unitsInABoxPackage?: number;
  revisionControl?: string;
  dimensionUnitHeight?: number;
  dimensionUnitLength?: number;
  dimensionUnitWidth?: number;
  dimensionUnitOfMeasure?: string;
  hazardousWeight?: string;
  franceAbcPickingCategory?: string;
  mdmTempConditionsCategory?: string;
  perishCodeCategory?: string;
  unNumber?: string;
  hazardClass?: string;
  unitOfIssue?: string;
  roundingFactor?: string;
  itemBuyerName?: string;
  hazardousWeightUom?: string;
  gphCode?: string;
  mos?: string;
  lpnPackage?: string;
  qteUnitairePalette?: number;
  typeUm?: string;
  hauteurUm?: number;
  longueurUm?: number;
  largeurUm?: number;
  qteUnitaireCarton?: number;
  dimensionsUc?: string;
  specifications?: string;
  cadencePoste?: string;
  pfse?: string;
  rhenus?: string;
  testCapa?: string;
  abc?: string;
  itemCreationDate?: Moment;
  categorieNm?: string;
}

export class Item implements IItem {
  constructor(
    public id?: number,
    public organizationCode?: string,
    public businessUnit?: string,
    public businessUnitDescription?: string,
    public businessField?: string,
    public businessFieldDescription?: string,
    public businessLine?: string,
    public businessLineDescription?: string,
    public workcenter?: string,
    public productGroup?: string,
    public productGroupDescription?: string,
    public productCategory?: string,
    public productCategoryDescription?: string,
    public productLine?: string,
    public productLineDescription?: string,
    public productLineManager?: string,
    public productDivision?: string,
    public productCategoryManager?: string,
    public plantCategory?: string,
    public itemType?: string,
    public primaryUnitOfMeasure?: string,
    public itemNumber?: string,
    public itemDescription?: string,
    public inventoryItemStatusCode?: string,
    public planningMakeOrBuyCode?: string,
    public itemPlannerCode?: string,
    public plannerName?: string,
    public mrpPlanningMethod?: string,
    public inventoryPlanningMethod?: string,
    public leadTimeLotSize?: number,
    public fixedLeadTime?: number,
    public fixedOrderQuanitiy?: number,
    public fixedLotMultiplier?: string,
    public currentSafetyStockQuantity?: number,
    public demandTimeFence?: string,
    public demandTimeFenceDays?: number,
    public fixedDaysSupply?: number,
    public frozenItemCost?: number,
    public frozenMaterialCost?: number,
    public maxMinmaxQuantity?: number,
    public minMinmaxQuantity?: number,
    public orderQuantityMax?: number,
    public orderQuantityMin?: number,
    public planningTimeFenceDays?: number,
    public postprocessingLeadTime?: number,
    public preprocessingLeadTime?: number,
    public processingLeadTime?: number,
    public mdmGoHCategory?: string,
    public resourceName?: string,
    public usageRate?: number,
    public aslVmiEnabled?: string,
    public aslConsignedFromSupplier?: string,
    public aslSupplier?: string,
    public aslSupplierNumber?: string,
    public aslsupplierSite?: string,
    public aslSupplierItem?: string,
    public aslpurchasingUom?: string,
    public slProcessCodeDetail?: string,
    public slEffectiveFromDate?: Moment,
    public receiptRoutingDetail?: string,
    public vmiPlanningParty?: string,
    public listPrice?: number,
    public mdmGlobalId?: string,
    public mdmArticleNumberYgart?: string,
    public lotControl?: string,
    public shelfLifeControl?: string,
    public shelfLifeDays?: number,
    public serialNumberControl?: string,
    public unitVolume?: number,
    public volumeUnitOfMeasure?: string,
    public unitWeight?: number,
    public weightUnitOfMeasure?: string,
    public unitsInABoxPackage?: number,
    public revisionControl?: string,
    public dimensionUnitHeight?: number,
    public dimensionUnitLength?: number,
    public dimensionUnitWidth?: number,
    public dimensionUnitOfMeasure?: string,
    public hazardousWeight?: string,
    public franceAbcPickingCategory?: string,
    public mdmTempConditionsCategory?: string,
    public perishCodeCategory?: string,
    public unNumber?: string,
    public hazardClass?: string,
    public unitOfIssue?: string,
    public roundingFactor?: string,
    public itemBuyerName?: string,
    public hazardousWeightUom?: string,
    public gphCode?: string,
    public mos?: string,
    public lpnPackage?: string,
    public qteUnitairePalette?: number,
    public typeUm?: string,
    public hauteurUm?: number,
    public longueurUm?: number,
    public largeurUm?: number,
    public qteUnitaireCarton?: number,
    public dimensionsUc?: string,
    public specifications?: string,
    public cadencePoste?: string,
    public pfse?: string,
    public rhenus?: string,
    public testCapa?: string,
    public abc?: string,
    public itemCreationDate?: Moment,
    public categorieNm?: string
  ) {}
}
