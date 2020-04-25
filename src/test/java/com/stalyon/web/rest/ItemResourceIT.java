package com.stalyon.web.rest;

import com.stalyon.InoscApp;
import com.stalyon.domain.Item;
import com.stalyon.repository.ItemRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ItemResource} REST controller.
 */
@SpringBootTest(classes = InoscApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ItemResourceIT {

    private static final String DEFAULT_ORGANIZATION_CODE = "AAA";
    private static final String UPDATED_ORGANIZATION_CODE = "BBB";

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBB";

    private static final String DEFAULT_BUSINESS_UNIT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_FIELD = "AAAAAA";
    private static final String UPDATED_BUSINESS_FIELD = "BBBBBB";

    private static final String DEFAULT_BUSINESS_FIELD_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_FIELD_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_LINE = "AAAAA";
    private static final String UPDATED_BUSINESS_LINE = "BBBBB";

    private static final String DEFAULT_BUSINESS_LINE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_LINE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_WORKCENTER = "AAAAA";
    private static final String UPDATED_WORKCENTER = "BBBBB";

    private static final String DEFAULT_PRODUCT_GROUP = "AAAAAAA";
    private static final String UPDATED_PRODUCT_GROUP = "BBBBBBB";

    private static final String DEFAULT_PRODUCT_GROUP_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_GROUP_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CATEGORY = "AAA";
    private static final String UPDATED_PRODUCT_CATEGORY = "BBB";

    private static final String DEFAULT_PRODUCT_CATEGORY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CATEGORY_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_LINE = "AA";
    private static final String UPDATED_PRODUCT_LINE = "BB";

    private static final String DEFAULT_PRODUCT_LINE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_LINE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_LINE_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_LINE_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CATEGORY_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CATEGORY_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_PLANT_CATEGORY = "AA";
    private static final String UPDATED_PLANT_CATEGORY = "BB";

    private static final String DEFAULT_ITEM_TYPE = "AAAAA";
    private static final String UPDATED_ITEM_TYPE = "BBBBB";

    private static final String DEFAULT_PRIMARY_UNIT_OF_MEASURE = "AA";
    private static final String UPDATED_PRIMARY_UNIT_OF_MEASURE = "BB";

    private static final String DEFAULT_ITEM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INVENTORY_ITEM_STATUS_CODE = "AAAAAAAA";
    private static final String UPDATED_INVENTORY_ITEM_STATUS_CODE = "BBBBBBBB";

    private static final String DEFAULT_PLANNING_MAKE_OR_BUY_CODE = "AAAA";
    private static final String UPDATED_PLANNING_MAKE_OR_BUY_CODE = "BBBB";

    private static final String DEFAULT_ITEM_PLANNER_CODE = "AAAA";
    private static final String UPDATED_ITEM_PLANNER_CODE = "BBBB";

    private static final String DEFAULT_PLANNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PLANNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MRP_PLANNING_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_MRP_PLANNING_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_INVENTORY_PLANNING_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_INVENTORY_PLANNING_METHOD = "BBBBBBBBBB";

    private static final Float DEFAULT_LEAD_TIME_LOT_SIZE = 1F;
    private static final Float UPDATED_LEAD_TIME_LOT_SIZE = 2F;

    private static final Float DEFAULT_FIXED_LEAD_TIME = 1F;
    private static final Float UPDATED_FIXED_LEAD_TIME = 2F;

    private static final Float DEFAULT_FIXED_ORDER_QUANITIY = 1F;
    private static final Float UPDATED_FIXED_ORDER_QUANITIY = 2F;

    private static final String DEFAULT_FIXED_LOT_MULTIPLIER = "AAAAAAAA";
    private static final String UPDATED_FIXED_LOT_MULTIPLIER = "BBBBBBBB";

    private static final Float DEFAULT_CURRENT_SAFETY_STOCK_QUANTITY = 1F;
    private static final Float UPDATED_CURRENT_SAFETY_STOCK_QUANTITY = 2F;

    private static final String DEFAULT_DEMAND_TIME_FENCE = "AAAAAAAAAA";
    private static final String UPDATED_DEMAND_TIME_FENCE = "BBBBBBBBBB";

    private static final Float DEFAULT_DEMAND_TIME_FENCE_DAYS = 1F;
    private static final Float UPDATED_DEMAND_TIME_FENCE_DAYS = 2F;

    private static final Integer DEFAULT_FIXED_DAYS_SUPPLY = 1;
    private static final Integer UPDATED_FIXED_DAYS_SUPPLY = 2;

    private static final Float DEFAULT_FROZEN_ITEM_COST = 1F;
    private static final Float UPDATED_FROZEN_ITEM_COST = 2F;

    private static final Float DEFAULT_FROZEN_MATERIAL_COST = 1F;
    private static final Float UPDATED_FROZEN_MATERIAL_COST = 2F;

    private static final Integer DEFAULT_MAX_MINMAX_QUANTITY = 1;
    private static final Integer UPDATED_MAX_MINMAX_QUANTITY = 2;

    private static final Integer DEFAULT_MIN_MINMAX_QUANTITY = 1;
    private static final Integer UPDATED_MIN_MINMAX_QUANTITY = 2;

    private static final Integer DEFAULT_ORDER_QUANTITY_MAX = 1;
    private static final Integer UPDATED_ORDER_QUANTITY_MAX = 2;

    private static final Integer DEFAULT_ORDER_QUANTITY_MIN = 1;
    private static final Integer UPDATED_ORDER_QUANTITY_MIN = 2;

    private static final Float DEFAULT_PLANNING_TIME_FENCE_DAYS = 1F;
    private static final Float UPDATED_PLANNING_TIME_FENCE_DAYS = 2F;

    private static final Float DEFAULT_POSTPROCESSING_LEAD_TIME = 1F;
    private static final Float UPDATED_POSTPROCESSING_LEAD_TIME = 2F;

    private static final Integer DEFAULT_PREPROCESSING_LEAD_TIME = 1;
    private static final Integer UPDATED_PREPROCESSING_LEAD_TIME = 2;

    private static final Float DEFAULT_PROCESSING_LEAD_TIME = 1F;
    private static final Float UPDATED_PROCESSING_LEAD_TIME = 2F;

    private static final String DEFAULT_MDM_GO_H_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_MDM_GO_H_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_USAGE_RATE = 1F;
    private static final Float UPDATED_USAGE_RATE = 2F;

    private static final String DEFAULT_ASL_VMI_ENABLED = "A";
    private static final String UPDATED_ASL_VMI_ENABLED = "B";

    private static final String DEFAULT_ASL_CONSIGNED_FROM_SUPPLIER = "A";
    private static final String UPDATED_ASL_CONSIGNED_FROM_SUPPLIER = "B";

    private static final String DEFAULT_ASL_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_ASL_SUPPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_ASL_SUPPLIER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ASL_SUPPLIER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ASLSUPPLIER_SITE = "AAAAAAAA";
    private static final String UPDATED_ASLSUPPLIER_SITE = "BBBBBBBB";

    private static final String DEFAULT_ASL_SUPPLIER_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ASL_SUPPLIER_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_ASLPURCHASING_UOM = "AAA";
    private static final String UPDATED_ASLPURCHASING_UOM = "BBB";

    private static final String DEFAULT_SL_PROCESS_CODE_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_SL_PROCESS_CODE_DETAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SL_EFFECTIVE_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SL_EFFECTIVE_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RECEIPT_ROUTING_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_ROUTING_DETAIL = "BBBBBBBBBB";

    private static final String DEFAULT_VMI_PLANNING_PARTY = "AAAAAAAAAA";
    private static final String UPDATED_VMI_PLANNING_PARTY = "BBBBBBBBBB";

    private static final Float DEFAULT_LIST_PRICE = 1F;
    private static final Float UPDATED_LIST_PRICE = 2F;

    private static final String DEFAULT_MDM_GLOBAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_MDM_GLOBAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MDM_ARTICLE_NUMBER_YGART = "AAAAAAAAAA";
    private static final String UPDATED_MDM_ARTICLE_NUMBER_YGART = "BBBBBBBBBB";

    private static final String DEFAULT_LOT_CONTROL = "AAAAAAAAAA";
    private static final String UPDATED_LOT_CONTROL = "BBBBBBBBBB";

    private static final String DEFAULT_SHELF_LIFE_CONTROL = "AAAAAAAAAA";
    private static final String UPDATED_SHELF_LIFE_CONTROL = "BBBBBBBBBB";

    private static final Float DEFAULT_SHELF_LIFE_DAYS = 1F;
    private static final Float UPDATED_SHELF_LIFE_DAYS = 2F;

    private static final String DEFAULT_SERIAL_NUMBER_CONTROL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER_CONTROL = "BBBBBBBBBB";

    private static final Float DEFAULT_UNIT_VOLUME = 1F;
    private static final Float UPDATED_UNIT_VOLUME = 2F;

    private static final String DEFAULT_VOLUME_UNIT_OF_MEASURE = "AAA";
    private static final String UPDATED_VOLUME_UNIT_OF_MEASURE = "BBB";

    private static final Float DEFAULT_UNIT_WEIGHT = 1F;
    private static final Float UPDATED_UNIT_WEIGHT = 2F;

    private static final String DEFAULT_WEIGHT_UNIT_OF_MEASURE = "AA";
    private static final String UPDATED_WEIGHT_UNIT_OF_MEASURE = "BB";

    private static final Float DEFAULT_UNITS_IN_A_BOX_PACKAGE = 1F;
    private static final Float UPDATED_UNITS_IN_A_BOX_PACKAGE = 2F;

    private static final String DEFAULT_REVISION_CONTROL = "AAAAAA";
    private static final String UPDATED_REVISION_CONTROL = "BBBBBB";

    private static final Float DEFAULT_DIMENSION_UNIT_HEIGHT = 1F;
    private static final Float UPDATED_DIMENSION_UNIT_HEIGHT = 2F;

    private static final Float DEFAULT_DIMENSION_UNIT_LENGTH = 1F;
    private static final Float UPDATED_DIMENSION_UNIT_LENGTH = 2F;

    private static final Float DEFAULT_DIMENSION_UNIT_WIDTH = 1F;
    private static final Float UPDATED_DIMENSION_UNIT_WIDTH = 2F;

    private static final String DEFAULT_DIMENSION_UNIT_OF_MEASURE = "AA";
    private static final String UPDATED_DIMENSION_UNIT_OF_MEASURE = "BB";

    private static final String DEFAULT_HAZARDOUS_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_HAZARDOUS_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_FRANCE_ABC_PICKING_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_FRANCE_ABC_PICKING_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_MDM_TEMP_CONDITIONS_CATEGORY = "AAA";
    private static final String UPDATED_MDM_TEMP_CONDITIONS_CATEGORY = "BBB";

    private static final String DEFAULT_PERISH_CODE_CATEGORY = "AAAAA";
    private static final String UPDATED_PERISH_CODE_CATEGORY = "BBBBB";

    private static final String DEFAULT_UN_NUMBER = "AAAAAA";
    private static final String UPDATED_UN_NUMBER = "BBBBBB";

    private static final String DEFAULT_HAZARD_CLASS = "AAAA";
    private static final String UPDATED_HAZARD_CLASS = "BBBB";

    private static final String DEFAULT_UNIT_OF_ISSUE = "AA";
    private static final String UPDATED_UNIT_OF_ISSUE = "BB";

    private static final String DEFAULT_ROUNDING_FACTOR = "AAAAAAAAAA";
    private static final String UPDATED_ROUNDING_FACTOR = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_BUYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_BUYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HAZARDOUS_WEIGHT_UOM = "AAAAA";
    private static final String UPDATED_HAZARDOUS_WEIGHT_UOM = "BBBBB";

    private static final String DEFAULT_GPH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_GPH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MOS = "AAAAAAAAA";
    private static final String UPDATED_MOS = "BBBBBBBBB";

    private static final String DEFAULT_LPN_PACKAGE = "AAA";
    private static final String UPDATED_LPN_PACKAGE = "BBB";

    private static final Float DEFAULT_QTE_UNITAIRE_PALETTE = 1F;
    private static final Float UPDATED_QTE_UNITAIRE_PALETTE = 2F;

    private static final String DEFAULT_TYPE_UM = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_UM = "BBBBBBBBBB";

    private static final Integer DEFAULT_HAUTEUR_UM = 1;
    private static final Integer UPDATED_HAUTEUR_UM = 2;

    private static final Integer DEFAULT_LONGUEUR_UM = 1;
    private static final Integer UPDATED_LONGUEUR_UM = 2;

    private static final Integer DEFAULT_LARGEUR_UM = 1;
    private static final Integer UPDATED_LARGEUR_UM = 2;

    private static final Float DEFAULT_QTE_UNITAIRE_CARTON = 1F;
    private static final Float UPDATED_QTE_UNITAIRE_CARTON = 2F;

    private static final String DEFAULT_DIMENSIONS_UC = "AAAAAAAAAA";
    private static final String UPDATED_DIMENSIONS_UC = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIFICATIONS = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATIONS = "BBBBBBBBBB";

    private static final String DEFAULT_CADENCE_POSTE = "AAAAA";
    private static final String UPDATED_CADENCE_POSTE = "BBBBB";

    private static final String DEFAULT_PFSE = "AAAAAAAAAA";
    private static final String UPDATED_PFSE = "BBBBBBBBBB";

    private static final String DEFAULT_RHENUS = "AAAAAAAAAA";
    private static final String UPDATED_RHENUS = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_CAPA = "AAAAAAAA";
    private static final String UPDATED_TEST_CAPA = "BBBBBBBB";

    private static final String DEFAULT_ABC = "A";
    private static final String UPDATED_ABC = "B";

    private static final LocalDate DEFAULT_ITEM_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ITEM_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CATEGORIE_NM = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIE_NM = "BBBBBBBBBB";

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemMockMvc;

    private Item item;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Item createEntity(EntityManager em) {
        Item item = new Item()
            .organizationCode(DEFAULT_ORGANIZATION_CODE)
            .businessUnit(DEFAULT_BUSINESS_UNIT)
            .businessUnitDescription(DEFAULT_BUSINESS_UNIT_DESCRIPTION)
            .businessField(DEFAULT_BUSINESS_FIELD)
            .businessFieldDescription(DEFAULT_BUSINESS_FIELD_DESCRIPTION)
            .businessLine(DEFAULT_BUSINESS_LINE)
            .businessLineDescription(DEFAULT_BUSINESS_LINE_DESCRIPTION)
            .workcenter(DEFAULT_WORKCENTER)
            .productGroup(DEFAULT_PRODUCT_GROUP)
            .productGroupDescription(DEFAULT_PRODUCT_GROUP_DESCRIPTION)
            .productCategory(DEFAULT_PRODUCT_CATEGORY)
            .productCategoryDescription(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION)
            .productLine(DEFAULT_PRODUCT_LINE)
            .productLineDescription(DEFAULT_PRODUCT_LINE_DESCRIPTION)
            .productLineManager(DEFAULT_PRODUCT_LINE_MANAGER)
            .productDivision(DEFAULT_PRODUCT_DIVISION)
            .productCategoryManager(DEFAULT_PRODUCT_CATEGORY_MANAGER)
            .plantCategory(DEFAULT_PLANT_CATEGORY)
            .itemType(DEFAULT_ITEM_TYPE)
            .primaryUnitOfMeasure(DEFAULT_PRIMARY_UNIT_OF_MEASURE)
            .itemNumber(DEFAULT_ITEM_NUMBER)
            .itemDescription(DEFAULT_ITEM_DESCRIPTION)
            .inventoryItemStatusCode(DEFAULT_INVENTORY_ITEM_STATUS_CODE)
            .planningMakeOrBuyCode(DEFAULT_PLANNING_MAKE_OR_BUY_CODE)
            .itemPlannerCode(DEFAULT_ITEM_PLANNER_CODE)
            .plannerName(DEFAULT_PLANNER_NAME)
            .mrpPlanningMethod(DEFAULT_MRP_PLANNING_METHOD)
            .inventoryPlanningMethod(DEFAULT_INVENTORY_PLANNING_METHOD)
            .leadTimeLotSize(DEFAULT_LEAD_TIME_LOT_SIZE)
            .fixedLeadTime(DEFAULT_FIXED_LEAD_TIME)
            .fixedOrderQuanitiy(DEFAULT_FIXED_ORDER_QUANITIY)
            .fixedLotMultiplier(DEFAULT_FIXED_LOT_MULTIPLIER)
            .currentSafetyStockQuantity(DEFAULT_CURRENT_SAFETY_STOCK_QUANTITY)
            .demandTimeFence(DEFAULT_DEMAND_TIME_FENCE)
            .demandTimeFenceDays(DEFAULT_DEMAND_TIME_FENCE_DAYS)
            .fixedDaysSupply(DEFAULT_FIXED_DAYS_SUPPLY)
            .frozenItemCost(DEFAULT_FROZEN_ITEM_COST)
            .frozenMaterialCost(DEFAULT_FROZEN_MATERIAL_COST)
            .maxMinmaxQuantity(DEFAULT_MAX_MINMAX_QUANTITY)
            .minMinmaxQuantity(DEFAULT_MIN_MINMAX_QUANTITY)
            .orderQuantityMax(DEFAULT_ORDER_QUANTITY_MAX)
            .orderQuantityMin(DEFAULT_ORDER_QUANTITY_MIN)
            .planningTimeFenceDays(DEFAULT_PLANNING_TIME_FENCE_DAYS)
            .postprocessingLeadTime(DEFAULT_POSTPROCESSING_LEAD_TIME)
            .preprocessingLeadTime(DEFAULT_PREPROCESSING_LEAD_TIME)
            .processingLeadTime(DEFAULT_PROCESSING_LEAD_TIME)
            .mdmGoHCategory(DEFAULT_MDM_GO_H_CATEGORY)
            .resourceName(DEFAULT_RESOURCE_NAME)
            .usageRate(DEFAULT_USAGE_RATE)
            .aslVmiEnabled(DEFAULT_ASL_VMI_ENABLED)
            .aslConsignedFromSupplier(DEFAULT_ASL_CONSIGNED_FROM_SUPPLIER)
            .aslSupplier(DEFAULT_ASL_SUPPLIER)
            .aslSupplierNumber(DEFAULT_ASL_SUPPLIER_NUMBER)
            .aslsupplierSite(DEFAULT_ASLSUPPLIER_SITE)
            .aslSupplierItem(DEFAULT_ASL_SUPPLIER_ITEM)
            .aslpurchasingUom(DEFAULT_ASLPURCHASING_UOM)
            .slProcessCodeDetail(DEFAULT_SL_PROCESS_CODE_DETAIL)
            .slEffectiveFromDate(DEFAULT_SL_EFFECTIVE_FROM_DATE)
            .receiptRoutingDetail(DEFAULT_RECEIPT_ROUTING_DETAIL)
            .vmiPlanningParty(DEFAULT_VMI_PLANNING_PARTY)
            .listPrice(DEFAULT_LIST_PRICE)
            .mdmGlobalId(DEFAULT_MDM_GLOBAL_ID)
            .mdmArticleNumberYgart(DEFAULT_MDM_ARTICLE_NUMBER_YGART)
            .lotControl(DEFAULT_LOT_CONTROL)
            .shelfLifeControl(DEFAULT_SHELF_LIFE_CONTROL)
            .shelfLifeDays(DEFAULT_SHELF_LIFE_DAYS)
            .serialNumberControl(DEFAULT_SERIAL_NUMBER_CONTROL)
            .unitVolume(DEFAULT_UNIT_VOLUME)
            .volumeUnitOfMeasure(DEFAULT_VOLUME_UNIT_OF_MEASURE)
            .unitWeight(DEFAULT_UNIT_WEIGHT)
            .weightUnitOfMeasure(DEFAULT_WEIGHT_UNIT_OF_MEASURE)
            .unitsInABoxPackage(DEFAULT_UNITS_IN_A_BOX_PACKAGE)
            .revisionControl(DEFAULT_REVISION_CONTROL)
            .dimensionUnitHeight(DEFAULT_DIMENSION_UNIT_HEIGHT)
            .dimensionUnitLength(DEFAULT_DIMENSION_UNIT_LENGTH)
            .dimensionUnitWidth(DEFAULT_DIMENSION_UNIT_WIDTH)
            .dimensionUnitOfMeasure(DEFAULT_DIMENSION_UNIT_OF_MEASURE)
            .hazardousWeight(DEFAULT_HAZARDOUS_WEIGHT)
            .franceAbcPickingCategory(DEFAULT_FRANCE_ABC_PICKING_CATEGORY)
            .mdmTempConditionsCategory(DEFAULT_MDM_TEMP_CONDITIONS_CATEGORY)
            .perishCodeCategory(DEFAULT_PERISH_CODE_CATEGORY)
            .unNumber(DEFAULT_UN_NUMBER)
            .hazardClass(DEFAULT_HAZARD_CLASS)
            .unitOfIssue(DEFAULT_UNIT_OF_ISSUE)
            .roundingFactor(DEFAULT_ROUNDING_FACTOR)
            .itemBuyerName(DEFAULT_ITEM_BUYER_NAME)
            .hazardousWeightUom(DEFAULT_HAZARDOUS_WEIGHT_UOM)
            .gphCode(DEFAULT_GPH_CODE)
            .mos(DEFAULT_MOS)
            .lpnPackage(DEFAULT_LPN_PACKAGE)
            .qteUnitairePalette(DEFAULT_QTE_UNITAIRE_PALETTE)
            .typeUm(DEFAULT_TYPE_UM)
            .hauteurUm(DEFAULT_HAUTEUR_UM)
            .longueurUm(DEFAULT_LONGUEUR_UM)
            .largeurUm(DEFAULT_LARGEUR_UM)
            .qteUnitaireCarton(DEFAULT_QTE_UNITAIRE_CARTON)
            .dimensionsUc(DEFAULT_DIMENSIONS_UC)
            .specifications(DEFAULT_SPECIFICATIONS)
            .cadencePoste(DEFAULT_CADENCE_POSTE)
            .pfse(DEFAULT_PFSE)
            .rhenus(DEFAULT_RHENUS)
            .testCapa(DEFAULT_TEST_CAPA)
            .abc(DEFAULT_ABC)
            .itemCreationDate(DEFAULT_ITEM_CREATION_DATE)
            .categorieNm(DEFAULT_CATEGORIE_NM);
        return item;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Item createUpdatedEntity(EntityManager em) {
        Item item = new Item()
            .organizationCode(UPDATED_ORGANIZATION_CODE)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .businessUnitDescription(UPDATED_BUSINESS_UNIT_DESCRIPTION)
            .businessField(UPDATED_BUSINESS_FIELD)
            .businessFieldDescription(UPDATED_BUSINESS_FIELD_DESCRIPTION)
            .businessLine(UPDATED_BUSINESS_LINE)
            .businessLineDescription(UPDATED_BUSINESS_LINE_DESCRIPTION)
            .workcenter(UPDATED_WORKCENTER)
            .productGroup(UPDATED_PRODUCT_GROUP)
            .productGroupDescription(UPDATED_PRODUCT_GROUP_DESCRIPTION)
            .productCategory(UPDATED_PRODUCT_CATEGORY)
            .productCategoryDescription(UPDATED_PRODUCT_CATEGORY_DESCRIPTION)
            .productLine(UPDATED_PRODUCT_LINE)
            .productLineDescription(UPDATED_PRODUCT_LINE_DESCRIPTION)
            .productLineManager(UPDATED_PRODUCT_LINE_MANAGER)
            .productDivision(UPDATED_PRODUCT_DIVISION)
            .productCategoryManager(UPDATED_PRODUCT_CATEGORY_MANAGER)
            .plantCategory(UPDATED_PLANT_CATEGORY)
            .itemType(UPDATED_ITEM_TYPE)
            .primaryUnitOfMeasure(UPDATED_PRIMARY_UNIT_OF_MEASURE)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .inventoryItemStatusCode(UPDATED_INVENTORY_ITEM_STATUS_CODE)
            .planningMakeOrBuyCode(UPDATED_PLANNING_MAKE_OR_BUY_CODE)
            .itemPlannerCode(UPDATED_ITEM_PLANNER_CODE)
            .plannerName(UPDATED_PLANNER_NAME)
            .mrpPlanningMethod(UPDATED_MRP_PLANNING_METHOD)
            .inventoryPlanningMethod(UPDATED_INVENTORY_PLANNING_METHOD)
            .leadTimeLotSize(UPDATED_LEAD_TIME_LOT_SIZE)
            .fixedLeadTime(UPDATED_FIXED_LEAD_TIME)
            .fixedOrderQuanitiy(UPDATED_FIXED_ORDER_QUANITIY)
            .fixedLotMultiplier(UPDATED_FIXED_LOT_MULTIPLIER)
            .currentSafetyStockQuantity(UPDATED_CURRENT_SAFETY_STOCK_QUANTITY)
            .demandTimeFence(UPDATED_DEMAND_TIME_FENCE)
            .demandTimeFenceDays(UPDATED_DEMAND_TIME_FENCE_DAYS)
            .fixedDaysSupply(UPDATED_FIXED_DAYS_SUPPLY)
            .frozenItemCost(UPDATED_FROZEN_ITEM_COST)
            .frozenMaterialCost(UPDATED_FROZEN_MATERIAL_COST)
            .maxMinmaxQuantity(UPDATED_MAX_MINMAX_QUANTITY)
            .minMinmaxQuantity(UPDATED_MIN_MINMAX_QUANTITY)
            .orderQuantityMax(UPDATED_ORDER_QUANTITY_MAX)
            .orderQuantityMin(UPDATED_ORDER_QUANTITY_MIN)
            .planningTimeFenceDays(UPDATED_PLANNING_TIME_FENCE_DAYS)
            .postprocessingLeadTime(UPDATED_POSTPROCESSING_LEAD_TIME)
            .preprocessingLeadTime(UPDATED_PREPROCESSING_LEAD_TIME)
            .processingLeadTime(UPDATED_PROCESSING_LEAD_TIME)
            .mdmGoHCategory(UPDATED_MDM_GO_H_CATEGORY)
            .resourceName(UPDATED_RESOURCE_NAME)
            .usageRate(UPDATED_USAGE_RATE)
            .aslVmiEnabled(UPDATED_ASL_VMI_ENABLED)
            .aslConsignedFromSupplier(UPDATED_ASL_CONSIGNED_FROM_SUPPLIER)
            .aslSupplier(UPDATED_ASL_SUPPLIER)
            .aslSupplierNumber(UPDATED_ASL_SUPPLIER_NUMBER)
            .aslsupplierSite(UPDATED_ASLSUPPLIER_SITE)
            .aslSupplierItem(UPDATED_ASL_SUPPLIER_ITEM)
            .aslpurchasingUom(UPDATED_ASLPURCHASING_UOM)
            .slProcessCodeDetail(UPDATED_SL_PROCESS_CODE_DETAIL)
            .slEffectiveFromDate(UPDATED_SL_EFFECTIVE_FROM_DATE)
            .receiptRoutingDetail(UPDATED_RECEIPT_ROUTING_DETAIL)
            .vmiPlanningParty(UPDATED_VMI_PLANNING_PARTY)
            .listPrice(UPDATED_LIST_PRICE)
            .mdmGlobalId(UPDATED_MDM_GLOBAL_ID)
            .mdmArticleNumberYgart(UPDATED_MDM_ARTICLE_NUMBER_YGART)
            .lotControl(UPDATED_LOT_CONTROL)
            .shelfLifeControl(UPDATED_SHELF_LIFE_CONTROL)
            .shelfLifeDays(UPDATED_SHELF_LIFE_DAYS)
            .serialNumberControl(UPDATED_SERIAL_NUMBER_CONTROL)
            .unitVolume(UPDATED_UNIT_VOLUME)
            .volumeUnitOfMeasure(UPDATED_VOLUME_UNIT_OF_MEASURE)
            .unitWeight(UPDATED_UNIT_WEIGHT)
            .weightUnitOfMeasure(UPDATED_WEIGHT_UNIT_OF_MEASURE)
            .unitsInABoxPackage(UPDATED_UNITS_IN_A_BOX_PACKAGE)
            .revisionControl(UPDATED_REVISION_CONTROL)
            .dimensionUnitHeight(UPDATED_DIMENSION_UNIT_HEIGHT)
            .dimensionUnitLength(UPDATED_DIMENSION_UNIT_LENGTH)
            .dimensionUnitWidth(UPDATED_DIMENSION_UNIT_WIDTH)
            .dimensionUnitOfMeasure(UPDATED_DIMENSION_UNIT_OF_MEASURE)
            .hazardousWeight(UPDATED_HAZARDOUS_WEIGHT)
            .franceAbcPickingCategory(UPDATED_FRANCE_ABC_PICKING_CATEGORY)
            .mdmTempConditionsCategory(UPDATED_MDM_TEMP_CONDITIONS_CATEGORY)
            .perishCodeCategory(UPDATED_PERISH_CODE_CATEGORY)
            .unNumber(UPDATED_UN_NUMBER)
            .hazardClass(UPDATED_HAZARD_CLASS)
            .unitOfIssue(UPDATED_UNIT_OF_ISSUE)
            .roundingFactor(UPDATED_ROUNDING_FACTOR)
            .itemBuyerName(UPDATED_ITEM_BUYER_NAME)
            .hazardousWeightUom(UPDATED_HAZARDOUS_WEIGHT_UOM)
            .gphCode(UPDATED_GPH_CODE)
            .mos(UPDATED_MOS)
            .lpnPackage(UPDATED_LPN_PACKAGE)
            .qteUnitairePalette(UPDATED_QTE_UNITAIRE_PALETTE)
            .typeUm(UPDATED_TYPE_UM)
            .hauteurUm(UPDATED_HAUTEUR_UM)
            .longueurUm(UPDATED_LONGUEUR_UM)
            .largeurUm(UPDATED_LARGEUR_UM)
            .qteUnitaireCarton(UPDATED_QTE_UNITAIRE_CARTON)
            .dimensionsUc(UPDATED_DIMENSIONS_UC)
            .specifications(UPDATED_SPECIFICATIONS)
            .cadencePoste(UPDATED_CADENCE_POSTE)
            .pfse(UPDATED_PFSE)
            .rhenus(UPDATED_RHENUS)
            .testCapa(UPDATED_TEST_CAPA)
            .abc(UPDATED_ABC)
            .itemCreationDate(UPDATED_ITEM_CREATION_DATE)
            .categorieNm(UPDATED_CATEGORIE_NM);
        return item;
    }

    @BeforeEach
    public void initTest() {
        item = createEntity(em);
    }

    @Test
    @Transactional
    public void createItem() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item
        restItemMockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isCreated());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate + 1);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getOrganizationCode()).isEqualTo(DEFAULT_ORGANIZATION_CODE);
        assertThat(testItem.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
        assertThat(testItem.getBusinessUnitDescription()).isEqualTo(DEFAULT_BUSINESS_UNIT_DESCRIPTION);
        assertThat(testItem.getBusinessField()).isEqualTo(DEFAULT_BUSINESS_FIELD);
        assertThat(testItem.getBusinessFieldDescription()).isEqualTo(DEFAULT_BUSINESS_FIELD_DESCRIPTION);
        assertThat(testItem.getBusinessLine()).isEqualTo(DEFAULT_BUSINESS_LINE);
        assertThat(testItem.getBusinessLineDescription()).isEqualTo(DEFAULT_BUSINESS_LINE_DESCRIPTION);
        assertThat(testItem.getWorkcenter()).isEqualTo(DEFAULT_WORKCENTER);
        assertThat(testItem.getProductGroup()).isEqualTo(DEFAULT_PRODUCT_GROUP);
        assertThat(testItem.getProductGroupDescription()).isEqualTo(DEFAULT_PRODUCT_GROUP_DESCRIPTION);
        assertThat(testItem.getProductCategory()).isEqualTo(DEFAULT_PRODUCT_CATEGORY);
        assertThat(testItem.getProductCategoryDescription()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION);
        assertThat(testItem.getProductLine()).isEqualTo(DEFAULT_PRODUCT_LINE);
        assertThat(testItem.getProductLineDescription()).isEqualTo(DEFAULT_PRODUCT_LINE_DESCRIPTION);
        assertThat(testItem.getProductLineManager()).isEqualTo(DEFAULT_PRODUCT_LINE_MANAGER);
        assertThat(testItem.getProductDivision()).isEqualTo(DEFAULT_PRODUCT_DIVISION);
        assertThat(testItem.getProductCategoryManager()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_MANAGER);
        assertThat(testItem.getPlantCategory()).isEqualTo(DEFAULT_PLANT_CATEGORY);
        assertThat(testItem.getItemType()).isEqualTo(DEFAULT_ITEM_TYPE);
        assertThat(testItem.getPrimaryUnitOfMeasure()).isEqualTo(DEFAULT_PRIMARY_UNIT_OF_MEASURE);
        assertThat(testItem.getItemNumber()).isEqualTo(DEFAULT_ITEM_NUMBER);
        assertThat(testItem.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testItem.getInventoryItemStatusCode()).isEqualTo(DEFAULT_INVENTORY_ITEM_STATUS_CODE);
        assertThat(testItem.getPlanningMakeOrBuyCode()).isEqualTo(DEFAULT_PLANNING_MAKE_OR_BUY_CODE);
        assertThat(testItem.getItemPlannerCode()).isEqualTo(DEFAULT_ITEM_PLANNER_CODE);
        assertThat(testItem.getPlannerName()).isEqualTo(DEFAULT_PLANNER_NAME);
        assertThat(testItem.getMrpPlanningMethod()).isEqualTo(DEFAULT_MRP_PLANNING_METHOD);
        assertThat(testItem.getInventoryPlanningMethod()).isEqualTo(DEFAULT_INVENTORY_PLANNING_METHOD);
        assertThat(testItem.getLeadTimeLotSize()).isEqualTo(DEFAULT_LEAD_TIME_LOT_SIZE);
        assertThat(testItem.getFixedLeadTime()).isEqualTo(DEFAULT_FIXED_LEAD_TIME);
        assertThat(testItem.getFixedOrderQuanitiy()).isEqualTo(DEFAULT_FIXED_ORDER_QUANITIY);
        assertThat(testItem.getFixedLotMultiplier()).isEqualTo(DEFAULT_FIXED_LOT_MULTIPLIER);
        assertThat(testItem.getCurrentSafetyStockQuantity()).isEqualTo(DEFAULT_CURRENT_SAFETY_STOCK_QUANTITY);
        assertThat(testItem.getDemandTimeFence()).isEqualTo(DEFAULT_DEMAND_TIME_FENCE);
        assertThat(testItem.getDemandTimeFenceDays()).isEqualTo(DEFAULT_DEMAND_TIME_FENCE_DAYS);
        assertThat(testItem.getFixedDaysSupply()).isEqualTo(DEFAULT_FIXED_DAYS_SUPPLY);
        assertThat(testItem.getFrozenItemCost()).isEqualTo(DEFAULT_FROZEN_ITEM_COST);
        assertThat(testItem.getFrozenMaterialCost()).isEqualTo(DEFAULT_FROZEN_MATERIAL_COST);
        assertThat(testItem.getMaxMinmaxQuantity()).isEqualTo(DEFAULT_MAX_MINMAX_QUANTITY);
        assertThat(testItem.getMinMinmaxQuantity()).isEqualTo(DEFAULT_MIN_MINMAX_QUANTITY);
        assertThat(testItem.getOrderQuantityMax()).isEqualTo(DEFAULT_ORDER_QUANTITY_MAX);
        assertThat(testItem.getOrderQuantityMin()).isEqualTo(DEFAULT_ORDER_QUANTITY_MIN);
        assertThat(testItem.getPlanningTimeFenceDays()).isEqualTo(DEFAULT_PLANNING_TIME_FENCE_DAYS);
        assertThat(testItem.getPostprocessingLeadTime()).isEqualTo(DEFAULT_POSTPROCESSING_LEAD_TIME);
        assertThat(testItem.getPreprocessingLeadTime()).isEqualTo(DEFAULT_PREPROCESSING_LEAD_TIME);
        assertThat(testItem.getProcessingLeadTime()).isEqualTo(DEFAULT_PROCESSING_LEAD_TIME);
        assertThat(testItem.getMdmGoHCategory()).isEqualTo(DEFAULT_MDM_GO_H_CATEGORY);
        assertThat(testItem.getResourceName()).isEqualTo(DEFAULT_RESOURCE_NAME);
        assertThat(testItem.getUsageRate()).isEqualTo(DEFAULT_USAGE_RATE);
        assertThat(testItem.getAslVmiEnabled()).isEqualTo(DEFAULT_ASL_VMI_ENABLED);
        assertThat(testItem.getAslConsignedFromSupplier()).isEqualTo(DEFAULT_ASL_CONSIGNED_FROM_SUPPLIER);
        assertThat(testItem.getAslSupplier()).isEqualTo(DEFAULT_ASL_SUPPLIER);
        assertThat(testItem.getAslSupplierNumber()).isEqualTo(DEFAULT_ASL_SUPPLIER_NUMBER);
        assertThat(testItem.getAslsupplierSite()).isEqualTo(DEFAULT_ASLSUPPLIER_SITE);
        assertThat(testItem.getAslSupplierItem()).isEqualTo(DEFAULT_ASL_SUPPLIER_ITEM);
        assertThat(testItem.getAslpurchasingUom()).isEqualTo(DEFAULT_ASLPURCHASING_UOM);
        assertThat(testItem.getSlProcessCodeDetail()).isEqualTo(DEFAULT_SL_PROCESS_CODE_DETAIL);
        assertThat(testItem.getSlEffectiveFromDate()).isEqualTo(DEFAULT_SL_EFFECTIVE_FROM_DATE);
        assertThat(testItem.getReceiptRoutingDetail()).isEqualTo(DEFAULT_RECEIPT_ROUTING_DETAIL);
        assertThat(testItem.getVmiPlanningParty()).isEqualTo(DEFAULT_VMI_PLANNING_PARTY);
        assertThat(testItem.getListPrice()).isEqualTo(DEFAULT_LIST_PRICE);
        assertThat(testItem.getMdmGlobalId()).isEqualTo(DEFAULT_MDM_GLOBAL_ID);
        assertThat(testItem.getMdmArticleNumberYgart()).isEqualTo(DEFAULT_MDM_ARTICLE_NUMBER_YGART);
        assertThat(testItem.getLotControl()).isEqualTo(DEFAULT_LOT_CONTROL);
        assertThat(testItem.getShelfLifeControl()).isEqualTo(DEFAULT_SHELF_LIFE_CONTROL);
        assertThat(testItem.getShelfLifeDays()).isEqualTo(DEFAULT_SHELF_LIFE_DAYS);
        assertThat(testItem.getSerialNumberControl()).isEqualTo(DEFAULT_SERIAL_NUMBER_CONTROL);
        assertThat(testItem.getUnitVolume()).isEqualTo(DEFAULT_UNIT_VOLUME);
        assertThat(testItem.getVolumeUnitOfMeasure()).isEqualTo(DEFAULT_VOLUME_UNIT_OF_MEASURE);
        assertThat(testItem.getUnitWeight()).isEqualTo(DEFAULT_UNIT_WEIGHT);
        assertThat(testItem.getWeightUnitOfMeasure()).isEqualTo(DEFAULT_WEIGHT_UNIT_OF_MEASURE);
        assertThat(testItem.getUnitsInABoxPackage()).isEqualTo(DEFAULT_UNITS_IN_A_BOX_PACKAGE);
        assertThat(testItem.getRevisionControl()).isEqualTo(DEFAULT_REVISION_CONTROL);
        assertThat(testItem.getDimensionUnitHeight()).isEqualTo(DEFAULT_DIMENSION_UNIT_HEIGHT);
        assertThat(testItem.getDimensionUnitLength()).isEqualTo(DEFAULT_DIMENSION_UNIT_LENGTH);
        assertThat(testItem.getDimensionUnitWidth()).isEqualTo(DEFAULT_DIMENSION_UNIT_WIDTH);
        assertThat(testItem.getDimensionUnitOfMeasure()).isEqualTo(DEFAULT_DIMENSION_UNIT_OF_MEASURE);
        assertThat(testItem.getHazardousWeight()).isEqualTo(DEFAULT_HAZARDOUS_WEIGHT);
        assertThat(testItem.getFranceAbcPickingCategory()).isEqualTo(DEFAULT_FRANCE_ABC_PICKING_CATEGORY);
        assertThat(testItem.getMdmTempConditionsCategory()).isEqualTo(DEFAULT_MDM_TEMP_CONDITIONS_CATEGORY);
        assertThat(testItem.getPerishCodeCategory()).isEqualTo(DEFAULT_PERISH_CODE_CATEGORY);
        assertThat(testItem.getUnNumber()).isEqualTo(DEFAULT_UN_NUMBER);
        assertThat(testItem.getHazardClass()).isEqualTo(DEFAULT_HAZARD_CLASS);
        assertThat(testItem.getUnitOfIssue()).isEqualTo(DEFAULT_UNIT_OF_ISSUE);
        assertThat(testItem.getRoundingFactor()).isEqualTo(DEFAULT_ROUNDING_FACTOR);
        assertThat(testItem.getItemBuyerName()).isEqualTo(DEFAULT_ITEM_BUYER_NAME);
        assertThat(testItem.getHazardousWeightUom()).isEqualTo(DEFAULT_HAZARDOUS_WEIGHT_UOM);
        assertThat(testItem.getGphCode()).isEqualTo(DEFAULT_GPH_CODE);
        assertThat(testItem.getMos()).isEqualTo(DEFAULT_MOS);
        assertThat(testItem.getLpnPackage()).isEqualTo(DEFAULT_LPN_PACKAGE);
        assertThat(testItem.getQteUnitairePalette()).isEqualTo(DEFAULT_QTE_UNITAIRE_PALETTE);
        assertThat(testItem.getTypeUm()).isEqualTo(DEFAULT_TYPE_UM);
        assertThat(testItem.getHauteurUm()).isEqualTo(DEFAULT_HAUTEUR_UM);
        assertThat(testItem.getLongueurUm()).isEqualTo(DEFAULT_LONGUEUR_UM);
        assertThat(testItem.getLargeurUm()).isEqualTo(DEFAULT_LARGEUR_UM);
        assertThat(testItem.getQteUnitaireCarton()).isEqualTo(DEFAULT_QTE_UNITAIRE_CARTON);
        assertThat(testItem.getDimensionsUc()).isEqualTo(DEFAULT_DIMENSIONS_UC);
        assertThat(testItem.getSpecifications()).isEqualTo(DEFAULT_SPECIFICATIONS);
        assertThat(testItem.getCadencePoste()).isEqualTo(DEFAULT_CADENCE_POSTE);
        assertThat(testItem.getPfse()).isEqualTo(DEFAULT_PFSE);
        assertThat(testItem.getRhenus()).isEqualTo(DEFAULT_RHENUS);
        assertThat(testItem.getTestCapa()).isEqualTo(DEFAULT_TEST_CAPA);
        assertThat(testItem.getAbc()).isEqualTo(DEFAULT_ABC);
        assertThat(testItem.getItemCreationDate()).isEqualTo(DEFAULT_ITEM_CREATION_DATE);
        assertThat(testItem.getCategorieNm()).isEqualTo(DEFAULT_CATEGORIE_NM);
    }

    @Test
    @Transactional
    public void createItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item with an existing ID
        item.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemMockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkItemNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setItemNumber(null);

        // Create the Item, which fails.

        restItemMockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItems() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get all the itemList
        restItemMockMvc.perform(get("/api/items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(item.getId().intValue())))
            .andExpect(jsonPath("$.[*].organizationCode").value(hasItem(DEFAULT_ORGANIZATION_CODE)))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT)))
            .andExpect(jsonPath("$.[*].businessUnitDescription").value(hasItem(DEFAULT_BUSINESS_UNIT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].businessField").value(hasItem(DEFAULT_BUSINESS_FIELD)))
            .andExpect(jsonPath("$.[*].businessFieldDescription").value(hasItem(DEFAULT_BUSINESS_FIELD_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].businessLine").value(hasItem(DEFAULT_BUSINESS_LINE)))
            .andExpect(jsonPath("$.[*].businessLineDescription").value(hasItem(DEFAULT_BUSINESS_LINE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].workcenter").value(hasItem(DEFAULT_WORKCENTER)))
            .andExpect(jsonPath("$.[*].productGroup").value(hasItem(DEFAULT_PRODUCT_GROUP)))
            .andExpect(jsonPath("$.[*].productGroupDescription").value(hasItem(DEFAULT_PRODUCT_GROUP_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].productCategory").value(hasItem(DEFAULT_PRODUCT_CATEGORY)))
            .andExpect(jsonPath("$.[*].productCategoryDescription").value(hasItem(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].productLine").value(hasItem(DEFAULT_PRODUCT_LINE)))
            .andExpect(jsonPath("$.[*].productLineDescription").value(hasItem(DEFAULT_PRODUCT_LINE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].productLineManager").value(hasItem(DEFAULT_PRODUCT_LINE_MANAGER)))
            .andExpect(jsonPath("$.[*].productDivision").value(hasItem(DEFAULT_PRODUCT_DIVISION)))
            .andExpect(jsonPath("$.[*].productCategoryManager").value(hasItem(DEFAULT_PRODUCT_CATEGORY_MANAGER)))
            .andExpect(jsonPath("$.[*].plantCategory").value(hasItem(DEFAULT_PLANT_CATEGORY)))
            .andExpect(jsonPath("$.[*].itemType").value(hasItem(DEFAULT_ITEM_TYPE)))
            .andExpect(jsonPath("$.[*].primaryUnitOfMeasure").value(hasItem(DEFAULT_PRIMARY_UNIT_OF_MEASURE)))
            .andExpect(jsonPath("$.[*].itemNumber").value(hasItem(DEFAULT_ITEM_NUMBER)))
            .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].inventoryItemStatusCode").value(hasItem(DEFAULT_INVENTORY_ITEM_STATUS_CODE)))
            .andExpect(jsonPath("$.[*].planningMakeOrBuyCode").value(hasItem(DEFAULT_PLANNING_MAKE_OR_BUY_CODE)))
            .andExpect(jsonPath("$.[*].itemPlannerCode").value(hasItem(DEFAULT_ITEM_PLANNER_CODE)))
            .andExpect(jsonPath("$.[*].plannerName").value(hasItem(DEFAULT_PLANNER_NAME)))
            .andExpect(jsonPath("$.[*].mrpPlanningMethod").value(hasItem(DEFAULT_MRP_PLANNING_METHOD)))
            .andExpect(jsonPath("$.[*].inventoryPlanningMethod").value(hasItem(DEFAULT_INVENTORY_PLANNING_METHOD)))
            .andExpect(jsonPath("$.[*].leadTimeLotSize").value(hasItem(DEFAULT_LEAD_TIME_LOT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].fixedLeadTime").value(hasItem(DEFAULT_FIXED_LEAD_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].fixedOrderQuanitiy").value(hasItem(DEFAULT_FIXED_ORDER_QUANITIY.doubleValue())))
            .andExpect(jsonPath("$.[*].fixedLotMultiplier").value(hasItem(DEFAULT_FIXED_LOT_MULTIPLIER)))
            .andExpect(jsonPath("$.[*].currentSafetyStockQuantity").value(hasItem(DEFAULT_CURRENT_SAFETY_STOCK_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].demandTimeFence").value(hasItem(DEFAULT_DEMAND_TIME_FENCE)))
            .andExpect(jsonPath("$.[*].demandTimeFenceDays").value(hasItem(DEFAULT_DEMAND_TIME_FENCE_DAYS.doubleValue())))
            .andExpect(jsonPath("$.[*].fixedDaysSupply").value(hasItem(DEFAULT_FIXED_DAYS_SUPPLY)))
            .andExpect(jsonPath("$.[*].frozenItemCost").value(hasItem(DEFAULT_FROZEN_ITEM_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].frozenMaterialCost").value(hasItem(DEFAULT_FROZEN_MATERIAL_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].maxMinmaxQuantity").value(hasItem(DEFAULT_MAX_MINMAX_QUANTITY)))
            .andExpect(jsonPath("$.[*].minMinmaxQuantity").value(hasItem(DEFAULT_MIN_MINMAX_QUANTITY)))
            .andExpect(jsonPath("$.[*].orderQuantityMax").value(hasItem(DEFAULT_ORDER_QUANTITY_MAX)))
            .andExpect(jsonPath("$.[*].orderQuantityMin").value(hasItem(DEFAULT_ORDER_QUANTITY_MIN)))
            .andExpect(jsonPath("$.[*].planningTimeFenceDays").value(hasItem(DEFAULT_PLANNING_TIME_FENCE_DAYS.doubleValue())))
            .andExpect(jsonPath("$.[*].postprocessingLeadTime").value(hasItem(DEFAULT_POSTPROCESSING_LEAD_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].preprocessingLeadTime").value(hasItem(DEFAULT_PREPROCESSING_LEAD_TIME)))
            .andExpect(jsonPath("$.[*].processingLeadTime").value(hasItem(DEFAULT_PROCESSING_LEAD_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].mdmGoHCategory").value(hasItem(DEFAULT_MDM_GO_H_CATEGORY)))
            .andExpect(jsonPath("$.[*].resourceName").value(hasItem(DEFAULT_RESOURCE_NAME)))
            .andExpect(jsonPath("$.[*].usageRate").value(hasItem(DEFAULT_USAGE_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].aslVmiEnabled").value(hasItem(DEFAULT_ASL_VMI_ENABLED)))
            .andExpect(jsonPath("$.[*].aslConsignedFromSupplier").value(hasItem(DEFAULT_ASL_CONSIGNED_FROM_SUPPLIER)))
            .andExpect(jsonPath("$.[*].aslSupplier").value(hasItem(DEFAULT_ASL_SUPPLIER)))
            .andExpect(jsonPath("$.[*].aslSupplierNumber").value(hasItem(DEFAULT_ASL_SUPPLIER_NUMBER)))
            .andExpect(jsonPath("$.[*].aslsupplierSite").value(hasItem(DEFAULT_ASLSUPPLIER_SITE)))
            .andExpect(jsonPath("$.[*].aslSupplierItem").value(hasItem(DEFAULT_ASL_SUPPLIER_ITEM)))
            .andExpect(jsonPath("$.[*].aslpurchasingUom").value(hasItem(DEFAULT_ASLPURCHASING_UOM)))
            .andExpect(jsonPath("$.[*].slProcessCodeDetail").value(hasItem(DEFAULT_SL_PROCESS_CODE_DETAIL)))
            .andExpect(jsonPath("$.[*].slEffectiveFromDate").value(hasItem(DEFAULT_SL_EFFECTIVE_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].receiptRoutingDetail").value(hasItem(DEFAULT_RECEIPT_ROUTING_DETAIL)))
            .andExpect(jsonPath("$.[*].vmiPlanningParty").value(hasItem(DEFAULT_VMI_PLANNING_PARTY)))
            .andExpect(jsonPath("$.[*].listPrice").value(hasItem(DEFAULT_LIST_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].mdmGlobalId").value(hasItem(DEFAULT_MDM_GLOBAL_ID)))
            .andExpect(jsonPath("$.[*].mdmArticleNumberYgart").value(hasItem(DEFAULT_MDM_ARTICLE_NUMBER_YGART)))
            .andExpect(jsonPath("$.[*].lotControl").value(hasItem(DEFAULT_LOT_CONTROL)))
            .andExpect(jsonPath("$.[*].shelfLifeControl").value(hasItem(DEFAULT_SHELF_LIFE_CONTROL)))
            .andExpect(jsonPath("$.[*].shelfLifeDays").value(hasItem(DEFAULT_SHELF_LIFE_DAYS.doubleValue())))
            .andExpect(jsonPath("$.[*].serialNumberControl").value(hasItem(DEFAULT_SERIAL_NUMBER_CONTROL)))
            .andExpect(jsonPath("$.[*].unitVolume").value(hasItem(DEFAULT_UNIT_VOLUME.doubleValue())))
            .andExpect(jsonPath("$.[*].volumeUnitOfMeasure").value(hasItem(DEFAULT_VOLUME_UNIT_OF_MEASURE)))
            .andExpect(jsonPath("$.[*].unitWeight").value(hasItem(DEFAULT_UNIT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weightUnitOfMeasure").value(hasItem(DEFAULT_WEIGHT_UNIT_OF_MEASURE)))
            .andExpect(jsonPath("$.[*].unitsInABoxPackage").value(hasItem(DEFAULT_UNITS_IN_A_BOX_PACKAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].revisionControl").value(hasItem(DEFAULT_REVISION_CONTROL)))
            .andExpect(jsonPath("$.[*].dimensionUnitHeight").value(hasItem(DEFAULT_DIMENSION_UNIT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].dimensionUnitLength").value(hasItem(DEFAULT_DIMENSION_UNIT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].dimensionUnitWidth").value(hasItem(DEFAULT_DIMENSION_UNIT_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].dimensionUnitOfMeasure").value(hasItem(DEFAULT_DIMENSION_UNIT_OF_MEASURE)))
            .andExpect(jsonPath("$.[*].hazardousWeight").value(hasItem(DEFAULT_HAZARDOUS_WEIGHT)))
            .andExpect(jsonPath("$.[*].franceAbcPickingCategory").value(hasItem(DEFAULT_FRANCE_ABC_PICKING_CATEGORY)))
            .andExpect(jsonPath("$.[*].mdmTempConditionsCategory").value(hasItem(DEFAULT_MDM_TEMP_CONDITIONS_CATEGORY)))
            .andExpect(jsonPath("$.[*].perishCodeCategory").value(hasItem(DEFAULT_PERISH_CODE_CATEGORY)))
            .andExpect(jsonPath("$.[*].unNumber").value(hasItem(DEFAULT_UN_NUMBER)))
            .andExpect(jsonPath("$.[*].hazardClass").value(hasItem(DEFAULT_HAZARD_CLASS)))
            .andExpect(jsonPath("$.[*].unitOfIssue").value(hasItem(DEFAULT_UNIT_OF_ISSUE)))
            .andExpect(jsonPath("$.[*].roundingFactor").value(hasItem(DEFAULT_ROUNDING_FACTOR)))
            .andExpect(jsonPath("$.[*].itemBuyerName").value(hasItem(DEFAULT_ITEM_BUYER_NAME)))
            .andExpect(jsonPath("$.[*].hazardousWeightUom").value(hasItem(DEFAULT_HAZARDOUS_WEIGHT_UOM)))
            .andExpect(jsonPath("$.[*].gphCode").value(hasItem(DEFAULT_GPH_CODE)))
            .andExpect(jsonPath("$.[*].mos").value(hasItem(DEFAULT_MOS)))
            .andExpect(jsonPath("$.[*].lpnPackage").value(hasItem(DEFAULT_LPN_PACKAGE)))
            .andExpect(jsonPath("$.[*].qteUnitairePalette").value(hasItem(DEFAULT_QTE_UNITAIRE_PALETTE.doubleValue())))
            .andExpect(jsonPath("$.[*].typeUm").value(hasItem(DEFAULT_TYPE_UM)))
            .andExpect(jsonPath("$.[*].hauteurUm").value(hasItem(DEFAULT_HAUTEUR_UM)))
            .andExpect(jsonPath("$.[*].longueurUm").value(hasItem(DEFAULT_LONGUEUR_UM)))
            .andExpect(jsonPath("$.[*].largeurUm").value(hasItem(DEFAULT_LARGEUR_UM)))
            .andExpect(jsonPath("$.[*].qteUnitaireCarton").value(hasItem(DEFAULT_QTE_UNITAIRE_CARTON.doubleValue())))
            .andExpect(jsonPath("$.[*].dimensionsUc").value(hasItem(DEFAULT_DIMENSIONS_UC)))
            .andExpect(jsonPath("$.[*].specifications").value(hasItem(DEFAULT_SPECIFICATIONS)))
            .andExpect(jsonPath("$.[*].cadencePoste").value(hasItem(DEFAULT_CADENCE_POSTE)))
            .andExpect(jsonPath("$.[*].pfse").value(hasItem(DEFAULT_PFSE)))
            .andExpect(jsonPath("$.[*].rhenus").value(hasItem(DEFAULT_RHENUS)))
            .andExpect(jsonPath("$.[*].testCapa").value(hasItem(DEFAULT_TEST_CAPA)))
            .andExpect(jsonPath("$.[*].abc").value(hasItem(DEFAULT_ABC)))
            .andExpect(jsonPath("$.[*].itemCreationDate").value(hasItem(DEFAULT_ITEM_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].categorieNm").value(hasItem(DEFAULT_CATEGORIE_NM)));
    }
    
    @Test
    @Transactional
    public void getItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", item.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(item.getId().intValue()))
            .andExpect(jsonPath("$.organizationCode").value(DEFAULT_ORGANIZATION_CODE))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT))
            .andExpect(jsonPath("$.businessUnitDescription").value(DEFAULT_BUSINESS_UNIT_DESCRIPTION))
            .andExpect(jsonPath("$.businessField").value(DEFAULT_BUSINESS_FIELD))
            .andExpect(jsonPath("$.businessFieldDescription").value(DEFAULT_BUSINESS_FIELD_DESCRIPTION))
            .andExpect(jsonPath("$.businessLine").value(DEFAULT_BUSINESS_LINE))
            .andExpect(jsonPath("$.businessLineDescription").value(DEFAULT_BUSINESS_LINE_DESCRIPTION))
            .andExpect(jsonPath("$.workcenter").value(DEFAULT_WORKCENTER))
            .andExpect(jsonPath("$.productGroup").value(DEFAULT_PRODUCT_GROUP))
            .andExpect(jsonPath("$.productGroupDescription").value(DEFAULT_PRODUCT_GROUP_DESCRIPTION))
            .andExpect(jsonPath("$.productCategory").value(DEFAULT_PRODUCT_CATEGORY))
            .andExpect(jsonPath("$.productCategoryDescription").value(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION))
            .andExpect(jsonPath("$.productLine").value(DEFAULT_PRODUCT_LINE))
            .andExpect(jsonPath("$.productLineDescription").value(DEFAULT_PRODUCT_LINE_DESCRIPTION))
            .andExpect(jsonPath("$.productLineManager").value(DEFAULT_PRODUCT_LINE_MANAGER))
            .andExpect(jsonPath("$.productDivision").value(DEFAULT_PRODUCT_DIVISION))
            .andExpect(jsonPath("$.productCategoryManager").value(DEFAULT_PRODUCT_CATEGORY_MANAGER))
            .andExpect(jsonPath("$.plantCategory").value(DEFAULT_PLANT_CATEGORY))
            .andExpect(jsonPath("$.itemType").value(DEFAULT_ITEM_TYPE))
            .andExpect(jsonPath("$.primaryUnitOfMeasure").value(DEFAULT_PRIMARY_UNIT_OF_MEASURE))
            .andExpect(jsonPath("$.itemNumber").value(DEFAULT_ITEM_NUMBER))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION))
            .andExpect(jsonPath("$.inventoryItemStatusCode").value(DEFAULT_INVENTORY_ITEM_STATUS_CODE))
            .andExpect(jsonPath("$.planningMakeOrBuyCode").value(DEFAULT_PLANNING_MAKE_OR_BUY_CODE))
            .andExpect(jsonPath("$.itemPlannerCode").value(DEFAULT_ITEM_PLANNER_CODE))
            .andExpect(jsonPath("$.plannerName").value(DEFAULT_PLANNER_NAME))
            .andExpect(jsonPath("$.mrpPlanningMethod").value(DEFAULT_MRP_PLANNING_METHOD))
            .andExpect(jsonPath("$.inventoryPlanningMethod").value(DEFAULT_INVENTORY_PLANNING_METHOD))
            .andExpect(jsonPath("$.leadTimeLotSize").value(DEFAULT_LEAD_TIME_LOT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.fixedLeadTime").value(DEFAULT_FIXED_LEAD_TIME.doubleValue()))
            .andExpect(jsonPath("$.fixedOrderQuanitiy").value(DEFAULT_FIXED_ORDER_QUANITIY.doubleValue()))
            .andExpect(jsonPath("$.fixedLotMultiplier").value(DEFAULT_FIXED_LOT_MULTIPLIER))
            .andExpect(jsonPath("$.currentSafetyStockQuantity").value(DEFAULT_CURRENT_SAFETY_STOCK_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.demandTimeFence").value(DEFAULT_DEMAND_TIME_FENCE))
            .andExpect(jsonPath("$.demandTimeFenceDays").value(DEFAULT_DEMAND_TIME_FENCE_DAYS.doubleValue()))
            .andExpect(jsonPath("$.fixedDaysSupply").value(DEFAULT_FIXED_DAYS_SUPPLY))
            .andExpect(jsonPath("$.frozenItemCost").value(DEFAULT_FROZEN_ITEM_COST.doubleValue()))
            .andExpect(jsonPath("$.frozenMaterialCost").value(DEFAULT_FROZEN_MATERIAL_COST.doubleValue()))
            .andExpect(jsonPath("$.maxMinmaxQuantity").value(DEFAULT_MAX_MINMAX_QUANTITY))
            .andExpect(jsonPath("$.minMinmaxQuantity").value(DEFAULT_MIN_MINMAX_QUANTITY))
            .andExpect(jsonPath("$.orderQuantityMax").value(DEFAULT_ORDER_QUANTITY_MAX))
            .andExpect(jsonPath("$.orderQuantityMin").value(DEFAULT_ORDER_QUANTITY_MIN))
            .andExpect(jsonPath("$.planningTimeFenceDays").value(DEFAULT_PLANNING_TIME_FENCE_DAYS.doubleValue()))
            .andExpect(jsonPath("$.postprocessingLeadTime").value(DEFAULT_POSTPROCESSING_LEAD_TIME.doubleValue()))
            .andExpect(jsonPath("$.preprocessingLeadTime").value(DEFAULT_PREPROCESSING_LEAD_TIME))
            .andExpect(jsonPath("$.processingLeadTime").value(DEFAULT_PROCESSING_LEAD_TIME.doubleValue()))
            .andExpect(jsonPath("$.mdmGoHCategory").value(DEFAULT_MDM_GO_H_CATEGORY))
            .andExpect(jsonPath("$.resourceName").value(DEFAULT_RESOURCE_NAME))
            .andExpect(jsonPath("$.usageRate").value(DEFAULT_USAGE_RATE.doubleValue()))
            .andExpect(jsonPath("$.aslVmiEnabled").value(DEFAULT_ASL_VMI_ENABLED))
            .andExpect(jsonPath("$.aslConsignedFromSupplier").value(DEFAULT_ASL_CONSIGNED_FROM_SUPPLIER))
            .andExpect(jsonPath("$.aslSupplier").value(DEFAULT_ASL_SUPPLIER))
            .andExpect(jsonPath("$.aslSupplierNumber").value(DEFAULT_ASL_SUPPLIER_NUMBER))
            .andExpect(jsonPath("$.aslsupplierSite").value(DEFAULT_ASLSUPPLIER_SITE))
            .andExpect(jsonPath("$.aslSupplierItem").value(DEFAULT_ASL_SUPPLIER_ITEM))
            .andExpect(jsonPath("$.aslpurchasingUom").value(DEFAULT_ASLPURCHASING_UOM))
            .andExpect(jsonPath("$.slProcessCodeDetail").value(DEFAULT_SL_PROCESS_CODE_DETAIL))
            .andExpect(jsonPath("$.slEffectiveFromDate").value(DEFAULT_SL_EFFECTIVE_FROM_DATE.toString()))
            .andExpect(jsonPath("$.receiptRoutingDetail").value(DEFAULT_RECEIPT_ROUTING_DETAIL))
            .andExpect(jsonPath("$.vmiPlanningParty").value(DEFAULT_VMI_PLANNING_PARTY))
            .andExpect(jsonPath("$.listPrice").value(DEFAULT_LIST_PRICE.doubleValue()))
            .andExpect(jsonPath("$.mdmGlobalId").value(DEFAULT_MDM_GLOBAL_ID))
            .andExpect(jsonPath("$.mdmArticleNumberYgart").value(DEFAULT_MDM_ARTICLE_NUMBER_YGART))
            .andExpect(jsonPath("$.lotControl").value(DEFAULT_LOT_CONTROL))
            .andExpect(jsonPath("$.shelfLifeControl").value(DEFAULT_SHELF_LIFE_CONTROL))
            .andExpect(jsonPath("$.shelfLifeDays").value(DEFAULT_SHELF_LIFE_DAYS.doubleValue()))
            .andExpect(jsonPath("$.serialNumberControl").value(DEFAULT_SERIAL_NUMBER_CONTROL))
            .andExpect(jsonPath("$.unitVolume").value(DEFAULT_UNIT_VOLUME.doubleValue()))
            .andExpect(jsonPath("$.volumeUnitOfMeasure").value(DEFAULT_VOLUME_UNIT_OF_MEASURE))
            .andExpect(jsonPath("$.unitWeight").value(DEFAULT_UNIT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.weightUnitOfMeasure").value(DEFAULT_WEIGHT_UNIT_OF_MEASURE))
            .andExpect(jsonPath("$.unitsInABoxPackage").value(DEFAULT_UNITS_IN_A_BOX_PACKAGE.doubleValue()))
            .andExpect(jsonPath("$.revisionControl").value(DEFAULT_REVISION_CONTROL))
            .andExpect(jsonPath("$.dimensionUnitHeight").value(DEFAULT_DIMENSION_UNIT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.dimensionUnitLength").value(DEFAULT_DIMENSION_UNIT_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.dimensionUnitWidth").value(DEFAULT_DIMENSION_UNIT_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.dimensionUnitOfMeasure").value(DEFAULT_DIMENSION_UNIT_OF_MEASURE))
            .andExpect(jsonPath("$.hazardousWeight").value(DEFAULT_HAZARDOUS_WEIGHT))
            .andExpect(jsonPath("$.franceAbcPickingCategory").value(DEFAULT_FRANCE_ABC_PICKING_CATEGORY))
            .andExpect(jsonPath("$.mdmTempConditionsCategory").value(DEFAULT_MDM_TEMP_CONDITIONS_CATEGORY))
            .andExpect(jsonPath("$.perishCodeCategory").value(DEFAULT_PERISH_CODE_CATEGORY))
            .andExpect(jsonPath("$.unNumber").value(DEFAULT_UN_NUMBER))
            .andExpect(jsonPath("$.hazardClass").value(DEFAULT_HAZARD_CLASS))
            .andExpect(jsonPath("$.unitOfIssue").value(DEFAULT_UNIT_OF_ISSUE))
            .andExpect(jsonPath("$.roundingFactor").value(DEFAULT_ROUNDING_FACTOR))
            .andExpect(jsonPath("$.itemBuyerName").value(DEFAULT_ITEM_BUYER_NAME))
            .andExpect(jsonPath("$.hazardousWeightUom").value(DEFAULT_HAZARDOUS_WEIGHT_UOM))
            .andExpect(jsonPath("$.gphCode").value(DEFAULT_GPH_CODE))
            .andExpect(jsonPath("$.mos").value(DEFAULT_MOS))
            .andExpect(jsonPath("$.lpnPackage").value(DEFAULT_LPN_PACKAGE))
            .andExpect(jsonPath("$.qteUnitairePalette").value(DEFAULT_QTE_UNITAIRE_PALETTE.doubleValue()))
            .andExpect(jsonPath("$.typeUm").value(DEFAULT_TYPE_UM))
            .andExpect(jsonPath("$.hauteurUm").value(DEFAULT_HAUTEUR_UM))
            .andExpect(jsonPath("$.longueurUm").value(DEFAULT_LONGUEUR_UM))
            .andExpect(jsonPath("$.largeurUm").value(DEFAULT_LARGEUR_UM))
            .andExpect(jsonPath("$.qteUnitaireCarton").value(DEFAULT_QTE_UNITAIRE_CARTON.doubleValue()))
            .andExpect(jsonPath("$.dimensionsUc").value(DEFAULT_DIMENSIONS_UC))
            .andExpect(jsonPath("$.specifications").value(DEFAULT_SPECIFICATIONS))
            .andExpect(jsonPath("$.cadencePoste").value(DEFAULT_CADENCE_POSTE))
            .andExpect(jsonPath("$.pfse").value(DEFAULT_PFSE))
            .andExpect(jsonPath("$.rhenus").value(DEFAULT_RHENUS))
            .andExpect(jsonPath("$.testCapa").value(DEFAULT_TEST_CAPA))
            .andExpect(jsonPath("$.abc").value(DEFAULT_ABC))
            .andExpect(jsonPath("$.itemCreationDate").value(DEFAULT_ITEM_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.categorieNm").value(DEFAULT_CATEGORIE_NM));
    }

    @Test
    @Transactional
    public void getNonExistingItem() throws Exception {
        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the item
        Item updatedItem = itemRepository.findById(item.getId()).get();
        // Disconnect from session so that the updates on updatedItem are not directly saved in db
        em.detach(updatedItem);
        updatedItem
            .organizationCode(UPDATED_ORGANIZATION_CODE)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .businessUnitDescription(UPDATED_BUSINESS_UNIT_DESCRIPTION)
            .businessField(UPDATED_BUSINESS_FIELD)
            .businessFieldDescription(UPDATED_BUSINESS_FIELD_DESCRIPTION)
            .businessLine(UPDATED_BUSINESS_LINE)
            .businessLineDescription(UPDATED_BUSINESS_LINE_DESCRIPTION)
            .workcenter(UPDATED_WORKCENTER)
            .productGroup(UPDATED_PRODUCT_GROUP)
            .productGroupDescription(UPDATED_PRODUCT_GROUP_DESCRIPTION)
            .productCategory(UPDATED_PRODUCT_CATEGORY)
            .productCategoryDescription(UPDATED_PRODUCT_CATEGORY_DESCRIPTION)
            .productLine(UPDATED_PRODUCT_LINE)
            .productLineDescription(UPDATED_PRODUCT_LINE_DESCRIPTION)
            .productLineManager(UPDATED_PRODUCT_LINE_MANAGER)
            .productDivision(UPDATED_PRODUCT_DIVISION)
            .productCategoryManager(UPDATED_PRODUCT_CATEGORY_MANAGER)
            .plantCategory(UPDATED_PLANT_CATEGORY)
            .itemType(UPDATED_ITEM_TYPE)
            .primaryUnitOfMeasure(UPDATED_PRIMARY_UNIT_OF_MEASURE)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .inventoryItemStatusCode(UPDATED_INVENTORY_ITEM_STATUS_CODE)
            .planningMakeOrBuyCode(UPDATED_PLANNING_MAKE_OR_BUY_CODE)
            .itemPlannerCode(UPDATED_ITEM_PLANNER_CODE)
            .plannerName(UPDATED_PLANNER_NAME)
            .mrpPlanningMethod(UPDATED_MRP_PLANNING_METHOD)
            .inventoryPlanningMethod(UPDATED_INVENTORY_PLANNING_METHOD)
            .leadTimeLotSize(UPDATED_LEAD_TIME_LOT_SIZE)
            .fixedLeadTime(UPDATED_FIXED_LEAD_TIME)
            .fixedOrderQuanitiy(UPDATED_FIXED_ORDER_QUANITIY)
            .fixedLotMultiplier(UPDATED_FIXED_LOT_MULTIPLIER)
            .currentSafetyStockQuantity(UPDATED_CURRENT_SAFETY_STOCK_QUANTITY)
            .demandTimeFence(UPDATED_DEMAND_TIME_FENCE)
            .demandTimeFenceDays(UPDATED_DEMAND_TIME_FENCE_DAYS)
            .fixedDaysSupply(UPDATED_FIXED_DAYS_SUPPLY)
            .frozenItemCost(UPDATED_FROZEN_ITEM_COST)
            .frozenMaterialCost(UPDATED_FROZEN_MATERIAL_COST)
            .maxMinmaxQuantity(UPDATED_MAX_MINMAX_QUANTITY)
            .minMinmaxQuantity(UPDATED_MIN_MINMAX_QUANTITY)
            .orderQuantityMax(UPDATED_ORDER_QUANTITY_MAX)
            .orderQuantityMin(UPDATED_ORDER_QUANTITY_MIN)
            .planningTimeFenceDays(UPDATED_PLANNING_TIME_FENCE_DAYS)
            .postprocessingLeadTime(UPDATED_POSTPROCESSING_LEAD_TIME)
            .preprocessingLeadTime(UPDATED_PREPROCESSING_LEAD_TIME)
            .processingLeadTime(UPDATED_PROCESSING_LEAD_TIME)
            .mdmGoHCategory(UPDATED_MDM_GO_H_CATEGORY)
            .resourceName(UPDATED_RESOURCE_NAME)
            .usageRate(UPDATED_USAGE_RATE)
            .aslVmiEnabled(UPDATED_ASL_VMI_ENABLED)
            .aslConsignedFromSupplier(UPDATED_ASL_CONSIGNED_FROM_SUPPLIER)
            .aslSupplier(UPDATED_ASL_SUPPLIER)
            .aslSupplierNumber(UPDATED_ASL_SUPPLIER_NUMBER)
            .aslsupplierSite(UPDATED_ASLSUPPLIER_SITE)
            .aslSupplierItem(UPDATED_ASL_SUPPLIER_ITEM)
            .aslpurchasingUom(UPDATED_ASLPURCHASING_UOM)
            .slProcessCodeDetail(UPDATED_SL_PROCESS_CODE_DETAIL)
            .slEffectiveFromDate(UPDATED_SL_EFFECTIVE_FROM_DATE)
            .receiptRoutingDetail(UPDATED_RECEIPT_ROUTING_DETAIL)
            .vmiPlanningParty(UPDATED_VMI_PLANNING_PARTY)
            .listPrice(UPDATED_LIST_PRICE)
            .mdmGlobalId(UPDATED_MDM_GLOBAL_ID)
            .mdmArticleNumberYgart(UPDATED_MDM_ARTICLE_NUMBER_YGART)
            .lotControl(UPDATED_LOT_CONTROL)
            .shelfLifeControl(UPDATED_SHELF_LIFE_CONTROL)
            .shelfLifeDays(UPDATED_SHELF_LIFE_DAYS)
            .serialNumberControl(UPDATED_SERIAL_NUMBER_CONTROL)
            .unitVolume(UPDATED_UNIT_VOLUME)
            .volumeUnitOfMeasure(UPDATED_VOLUME_UNIT_OF_MEASURE)
            .unitWeight(UPDATED_UNIT_WEIGHT)
            .weightUnitOfMeasure(UPDATED_WEIGHT_UNIT_OF_MEASURE)
            .unitsInABoxPackage(UPDATED_UNITS_IN_A_BOX_PACKAGE)
            .revisionControl(UPDATED_REVISION_CONTROL)
            .dimensionUnitHeight(UPDATED_DIMENSION_UNIT_HEIGHT)
            .dimensionUnitLength(UPDATED_DIMENSION_UNIT_LENGTH)
            .dimensionUnitWidth(UPDATED_DIMENSION_UNIT_WIDTH)
            .dimensionUnitOfMeasure(UPDATED_DIMENSION_UNIT_OF_MEASURE)
            .hazardousWeight(UPDATED_HAZARDOUS_WEIGHT)
            .franceAbcPickingCategory(UPDATED_FRANCE_ABC_PICKING_CATEGORY)
            .mdmTempConditionsCategory(UPDATED_MDM_TEMP_CONDITIONS_CATEGORY)
            .perishCodeCategory(UPDATED_PERISH_CODE_CATEGORY)
            .unNumber(UPDATED_UN_NUMBER)
            .hazardClass(UPDATED_HAZARD_CLASS)
            .unitOfIssue(UPDATED_UNIT_OF_ISSUE)
            .roundingFactor(UPDATED_ROUNDING_FACTOR)
            .itemBuyerName(UPDATED_ITEM_BUYER_NAME)
            .hazardousWeightUom(UPDATED_HAZARDOUS_WEIGHT_UOM)
            .gphCode(UPDATED_GPH_CODE)
            .mos(UPDATED_MOS)
            .lpnPackage(UPDATED_LPN_PACKAGE)
            .qteUnitairePalette(UPDATED_QTE_UNITAIRE_PALETTE)
            .typeUm(UPDATED_TYPE_UM)
            .hauteurUm(UPDATED_HAUTEUR_UM)
            .longueurUm(UPDATED_LONGUEUR_UM)
            .largeurUm(UPDATED_LARGEUR_UM)
            .qteUnitaireCarton(UPDATED_QTE_UNITAIRE_CARTON)
            .dimensionsUc(UPDATED_DIMENSIONS_UC)
            .specifications(UPDATED_SPECIFICATIONS)
            .cadencePoste(UPDATED_CADENCE_POSTE)
            .pfse(UPDATED_PFSE)
            .rhenus(UPDATED_RHENUS)
            .testCapa(UPDATED_TEST_CAPA)
            .abc(UPDATED_ABC)
            .itemCreationDate(UPDATED_ITEM_CREATION_DATE)
            .categorieNm(UPDATED_CATEGORIE_NM);

        restItemMockMvc.perform(put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedItem)))
            .andExpect(status().isOk());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getOrganizationCode()).isEqualTo(UPDATED_ORGANIZATION_CODE);
        assertThat(testItem.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
        assertThat(testItem.getBusinessUnitDescription()).isEqualTo(UPDATED_BUSINESS_UNIT_DESCRIPTION);
        assertThat(testItem.getBusinessField()).isEqualTo(UPDATED_BUSINESS_FIELD);
        assertThat(testItem.getBusinessFieldDescription()).isEqualTo(UPDATED_BUSINESS_FIELD_DESCRIPTION);
        assertThat(testItem.getBusinessLine()).isEqualTo(UPDATED_BUSINESS_LINE);
        assertThat(testItem.getBusinessLineDescription()).isEqualTo(UPDATED_BUSINESS_LINE_DESCRIPTION);
        assertThat(testItem.getWorkcenter()).isEqualTo(UPDATED_WORKCENTER);
        assertThat(testItem.getProductGroup()).isEqualTo(UPDATED_PRODUCT_GROUP);
        assertThat(testItem.getProductGroupDescription()).isEqualTo(UPDATED_PRODUCT_GROUP_DESCRIPTION);
        assertThat(testItem.getProductCategory()).isEqualTo(UPDATED_PRODUCT_CATEGORY);
        assertThat(testItem.getProductCategoryDescription()).isEqualTo(UPDATED_PRODUCT_CATEGORY_DESCRIPTION);
        assertThat(testItem.getProductLine()).isEqualTo(UPDATED_PRODUCT_LINE);
        assertThat(testItem.getProductLineDescription()).isEqualTo(UPDATED_PRODUCT_LINE_DESCRIPTION);
        assertThat(testItem.getProductLineManager()).isEqualTo(UPDATED_PRODUCT_LINE_MANAGER);
        assertThat(testItem.getProductDivision()).isEqualTo(UPDATED_PRODUCT_DIVISION);
        assertThat(testItem.getProductCategoryManager()).isEqualTo(UPDATED_PRODUCT_CATEGORY_MANAGER);
        assertThat(testItem.getPlantCategory()).isEqualTo(UPDATED_PLANT_CATEGORY);
        assertThat(testItem.getItemType()).isEqualTo(UPDATED_ITEM_TYPE);
        assertThat(testItem.getPrimaryUnitOfMeasure()).isEqualTo(UPDATED_PRIMARY_UNIT_OF_MEASURE);
        assertThat(testItem.getItemNumber()).isEqualTo(UPDATED_ITEM_NUMBER);
        assertThat(testItem.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItem.getInventoryItemStatusCode()).isEqualTo(UPDATED_INVENTORY_ITEM_STATUS_CODE);
        assertThat(testItem.getPlanningMakeOrBuyCode()).isEqualTo(UPDATED_PLANNING_MAKE_OR_BUY_CODE);
        assertThat(testItem.getItemPlannerCode()).isEqualTo(UPDATED_ITEM_PLANNER_CODE);
        assertThat(testItem.getPlannerName()).isEqualTo(UPDATED_PLANNER_NAME);
        assertThat(testItem.getMrpPlanningMethod()).isEqualTo(UPDATED_MRP_PLANNING_METHOD);
        assertThat(testItem.getInventoryPlanningMethod()).isEqualTo(UPDATED_INVENTORY_PLANNING_METHOD);
        assertThat(testItem.getLeadTimeLotSize()).isEqualTo(UPDATED_LEAD_TIME_LOT_SIZE);
        assertThat(testItem.getFixedLeadTime()).isEqualTo(UPDATED_FIXED_LEAD_TIME);
        assertThat(testItem.getFixedOrderQuanitiy()).isEqualTo(UPDATED_FIXED_ORDER_QUANITIY);
        assertThat(testItem.getFixedLotMultiplier()).isEqualTo(UPDATED_FIXED_LOT_MULTIPLIER);
        assertThat(testItem.getCurrentSafetyStockQuantity()).isEqualTo(UPDATED_CURRENT_SAFETY_STOCK_QUANTITY);
        assertThat(testItem.getDemandTimeFence()).isEqualTo(UPDATED_DEMAND_TIME_FENCE);
        assertThat(testItem.getDemandTimeFenceDays()).isEqualTo(UPDATED_DEMAND_TIME_FENCE_DAYS);
        assertThat(testItem.getFixedDaysSupply()).isEqualTo(UPDATED_FIXED_DAYS_SUPPLY);
        assertThat(testItem.getFrozenItemCost()).isEqualTo(UPDATED_FROZEN_ITEM_COST);
        assertThat(testItem.getFrozenMaterialCost()).isEqualTo(UPDATED_FROZEN_MATERIAL_COST);
        assertThat(testItem.getMaxMinmaxQuantity()).isEqualTo(UPDATED_MAX_MINMAX_QUANTITY);
        assertThat(testItem.getMinMinmaxQuantity()).isEqualTo(UPDATED_MIN_MINMAX_QUANTITY);
        assertThat(testItem.getOrderQuantityMax()).isEqualTo(UPDATED_ORDER_QUANTITY_MAX);
        assertThat(testItem.getOrderQuantityMin()).isEqualTo(UPDATED_ORDER_QUANTITY_MIN);
        assertThat(testItem.getPlanningTimeFenceDays()).isEqualTo(UPDATED_PLANNING_TIME_FENCE_DAYS);
        assertThat(testItem.getPostprocessingLeadTime()).isEqualTo(UPDATED_POSTPROCESSING_LEAD_TIME);
        assertThat(testItem.getPreprocessingLeadTime()).isEqualTo(UPDATED_PREPROCESSING_LEAD_TIME);
        assertThat(testItem.getProcessingLeadTime()).isEqualTo(UPDATED_PROCESSING_LEAD_TIME);
        assertThat(testItem.getMdmGoHCategory()).isEqualTo(UPDATED_MDM_GO_H_CATEGORY);
        assertThat(testItem.getResourceName()).isEqualTo(UPDATED_RESOURCE_NAME);
        assertThat(testItem.getUsageRate()).isEqualTo(UPDATED_USAGE_RATE);
        assertThat(testItem.getAslVmiEnabled()).isEqualTo(UPDATED_ASL_VMI_ENABLED);
        assertThat(testItem.getAslConsignedFromSupplier()).isEqualTo(UPDATED_ASL_CONSIGNED_FROM_SUPPLIER);
        assertThat(testItem.getAslSupplier()).isEqualTo(UPDATED_ASL_SUPPLIER);
        assertThat(testItem.getAslSupplierNumber()).isEqualTo(UPDATED_ASL_SUPPLIER_NUMBER);
        assertThat(testItem.getAslsupplierSite()).isEqualTo(UPDATED_ASLSUPPLIER_SITE);
        assertThat(testItem.getAslSupplierItem()).isEqualTo(UPDATED_ASL_SUPPLIER_ITEM);
        assertThat(testItem.getAslpurchasingUom()).isEqualTo(UPDATED_ASLPURCHASING_UOM);
        assertThat(testItem.getSlProcessCodeDetail()).isEqualTo(UPDATED_SL_PROCESS_CODE_DETAIL);
        assertThat(testItem.getSlEffectiveFromDate()).isEqualTo(UPDATED_SL_EFFECTIVE_FROM_DATE);
        assertThat(testItem.getReceiptRoutingDetail()).isEqualTo(UPDATED_RECEIPT_ROUTING_DETAIL);
        assertThat(testItem.getVmiPlanningParty()).isEqualTo(UPDATED_VMI_PLANNING_PARTY);
        assertThat(testItem.getListPrice()).isEqualTo(UPDATED_LIST_PRICE);
        assertThat(testItem.getMdmGlobalId()).isEqualTo(UPDATED_MDM_GLOBAL_ID);
        assertThat(testItem.getMdmArticleNumberYgart()).isEqualTo(UPDATED_MDM_ARTICLE_NUMBER_YGART);
        assertThat(testItem.getLotControl()).isEqualTo(UPDATED_LOT_CONTROL);
        assertThat(testItem.getShelfLifeControl()).isEqualTo(UPDATED_SHELF_LIFE_CONTROL);
        assertThat(testItem.getShelfLifeDays()).isEqualTo(UPDATED_SHELF_LIFE_DAYS);
        assertThat(testItem.getSerialNumberControl()).isEqualTo(UPDATED_SERIAL_NUMBER_CONTROL);
        assertThat(testItem.getUnitVolume()).isEqualTo(UPDATED_UNIT_VOLUME);
        assertThat(testItem.getVolumeUnitOfMeasure()).isEqualTo(UPDATED_VOLUME_UNIT_OF_MEASURE);
        assertThat(testItem.getUnitWeight()).isEqualTo(UPDATED_UNIT_WEIGHT);
        assertThat(testItem.getWeightUnitOfMeasure()).isEqualTo(UPDATED_WEIGHT_UNIT_OF_MEASURE);
        assertThat(testItem.getUnitsInABoxPackage()).isEqualTo(UPDATED_UNITS_IN_A_BOX_PACKAGE);
        assertThat(testItem.getRevisionControl()).isEqualTo(UPDATED_REVISION_CONTROL);
        assertThat(testItem.getDimensionUnitHeight()).isEqualTo(UPDATED_DIMENSION_UNIT_HEIGHT);
        assertThat(testItem.getDimensionUnitLength()).isEqualTo(UPDATED_DIMENSION_UNIT_LENGTH);
        assertThat(testItem.getDimensionUnitWidth()).isEqualTo(UPDATED_DIMENSION_UNIT_WIDTH);
        assertThat(testItem.getDimensionUnitOfMeasure()).isEqualTo(UPDATED_DIMENSION_UNIT_OF_MEASURE);
        assertThat(testItem.getHazardousWeight()).isEqualTo(UPDATED_HAZARDOUS_WEIGHT);
        assertThat(testItem.getFranceAbcPickingCategory()).isEqualTo(UPDATED_FRANCE_ABC_PICKING_CATEGORY);
        assertThat(testItem.getMdmTempConditionsCategory()).isEqualTo(UPDATED_MDM_TEMP_CONDITIONS_CATEGORY);
        assertThat(testItem.getPerishCodeCategory()).isEqualTo(UPDATED_PERISH_CODE_CATEGORY);
        assertThat(testItem.getUnNumber()).isEqualTo(UPDATED_UN_NUMBER);
        assertThat(testItem.getHazardClass()).isEqualTo(UPDATED_HAZARD_CLASS);
        assertThat(testItem.getUnitOfIssue()).isEqualTo(UPDATED_UNIT_OF_ISSUE);
        assertThat(testItem.getRoundingFactor()).isEqualTo(UPDATED_ROUNDING_FACTOR);
        assertThat(testItem.getItemBuyerName()).isEqualTo(UPDATED_ITEM_BUYER_NAME);
        assertThat(testItem.getHazardousWeightUom()).isEqualTo(UPDATED_HAZARDOUS_WEIGHT_UOM);
        assertThat(testItem.getGphCode()).isEqualTo(UPDATED_GPH_CODE);
        assertThat(testItem.getMos()).isEqualTo(UPDATED_MOS);
        assertThat(testItem.getLpnPackage()).isEqualTo(UPDATED_LPN_PACKAGE);
        assertThat(testItem.getQteUnitairePalette()).isEqualTo(UPDATED_QTE_UNITAIRE_PALETTE);
        assertThat(testItem.getTypeUm()).isEqualTo(UPDATED_TYPE_UM);
        assertThat(testItem.getHauteurUm()).isEqualTo(UPDATED_HAUTEUR_UM);
        assertThat(testItem.getLongueurUm()).isEqualTo(UPDATED_LONGUEUR_UM);
        assertThat(testItem.getLargeurUm()).isEqualTo(UPDATED_LARGEUR_UM);
        assertThat(testItem.getQteUnitaireCarton()).isEqualTo(UPDATED_QTE_UNITAIRE_CARTON);
        assertThat(testItem.getDimensionsUc()).isEqualTo(UPDATED_DIMENSIONS_UC);
        assertThat(testItem.getSpecifications()).isEqualTo(UPDATED_SPECIFICATIONS);
        assertThat(testItem.getCadencePoste()).isEqualTo(UPDATED_CADENCE_POSTE);
        assertThat(testItem.getPfse()).isEqualTo(UPDATED_PFSE);
        assertThat(testItem.getRhenus()).isEqualTo(UPDATED_RHENUS);
        assertThat(testItem.getTestCapa()).isEqualTo(UPDATED_TEST_CAPA);
        assertThat(testItem.getAbc()).isEqualTo(UPDATED_ABC);
        assertThat(testItem.getItemCreationDate()).isEqualTo(UPDATED_ITEM_CREATION_DATE);
        assertThat(testItem.getCategorieNm()).isEqualTo(UPDATED_CATEGORIE_NM);
    }

    @Test
    @Transactional
    public void updateNonExistingItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Create the Item

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMockMvc.perform(put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        int databaseSizeBeforeDelete = itemRepository.findAll().size();

        // Delete the item
        restItemMockMvc.perform(delete("/api/items/{id}", item.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
