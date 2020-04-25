package com.stalyon.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 3)
    @Column(name = "organization_code", length = 3)
    private String organizationCode;

    @Size(max = 7)
    @Column(name = "business_unit", length = 7)
    private String businessUnit;

    @Size(max = 60)
    @Column(name = "business_unit_description", length = 60)
    private String businessUnitDescription;

    @Size(max = 6)
    @Column(name = "business_field", length = 6)
    private String businessField;

    @Size(max = 60)
    @Column(name = "business_field_description", length = 60)
    private String businessFieldDescription;

    @Size(max = 5)
    @Column(name = "business_line", length = 5)
    private String businessLine;

    @Size(max = 60)
    @Column(name = "business_line_description", length = 60)
    private String businessLineDescription;

    @Size(max = 5)
    @Column(name = "workcenter", length = 5)
    private String workcenter;

    @Size(max = 7)
    @Column(name = "product_group", length = 7)
    private String productGroup;

    @Size(max = 60)
    @Column(name = "product_group_description", length = 60)
    private String productGroupDescription;

    @Size(max = 3)
    @Column(name = "product_category", length = 3)
    private String productCategory;

    @Size(max = 60)
    @Column(name = "product_category_description", length = 60)
    private String productCategoryDescription;

    @Size(max = 2)
    @Column(name = "product_line", length = 2)
    private String productLine;

    @Size(max = 60)
    @Column(name = "product_line_description", length = 60)
    private String productLineDescription;

    @Size(max = 60)
    @Column(name = "product_line_manager", length = 60)
    private String productLineManager;

    @Size(max = 10)
    @Column(name = "product_division", length = 10)
    private String productDivision;

    @Size(max = 60)
    @Column(name = "product_category_manager", length = 60)
    private String productCategoryManager;

    @Size(max = 2)
    @Column(name = "plant_category", length = 2)
    private String plantCategory;

    @Size(max = 5)
    @Column(name = "item_type", length = 5)
    private String itemType;

    @Size(max = 2)
    @Column(name = "primary_unit_of_measure", length = 2)
    private String primaryUnitOfMeasure;

    @NotNull
    @Size(max = 18)
    @Column(name = "item_number", length = 18, nullable = false)
    private String itemNumber;

    @Size(max = 60)
    @Column(name = "item_description", length = 60)
    private String itemDescription;

    @Size(max = 8)
    @Column(name = "inventory_item_status_code", length = 8)
    private String inventoryItemStatusCode;

    @Size(max = 4)
    @Column(name = "planning_make_or_buy_code", length = 4)
    private String planningMakeOrBuyCode;

    @Size(max = 4)
    @Column(name = "item_planner_code", length = 4)
    private String itemPlannerCode;

    @Size(max = 60)
    @Column(name = "planner_name", length = 60)
    private String plannerName;

    @Size(max = 15)
    @Column(name = "mrp_planning_method", length = 15)
    private String mrpPlanningMethod;

    @Size(max = 16)
    @Column(name = "inventory_planning_method", length = 16)
    private String inventoryPlanningMethod;

    @Column(name = "lead_time_lot_size")
    private Float leadTimeLotSize;

    @Column(name = "fixed_lead_time")
    private Float fixedLeadTime;

    @Column(name = "fixed_order_quanitiy")
    private Float fixedOrderQuanitiy;

    @Size(max = 8)
    @Column(name = "fixed_lot_multiplier", length = 8)
    private String fixedLotMultiplier;

    @Column(name = "current_safety_stock_quantity")
    private Float currentSafetyStockQuantity;

    @Size(max = 12)
    @Column(name = "demand_time_fence", length = 12)
    private String demandTimeFence;

    @Column(name = "demand_time_fence_days")
    private Float demandTimeFenceDays;

    @Column(name = "fixed_days_supply")
    private Integer fixedDaysSupply;

    @Column(name = "frozen_item_cost")
    private Float frozenItemCost;

    @Column(name = "frozen_material_cost")
    private Float frozenMaterialCost;

    @Column(name = "max_minmax_quantity")
    private Integer maxMinmaxQuantity;

    @Column(name = "min_minmax_quantity")
    private Integer minMinmaxQuantity;

    @Column(name = "order_quantity_max")
    private Integer orderQuantityMax;

    @Column(name = "order_quantity_min")
    private Integer orderQuantityMin;

    @Column(name = "planning_time_fence_days")
    private Float planningTimeFenceDays;

    @Column(name = "postprocessing_lead_time")
    private Float postprocessingLeadTime;

    @Column(name = "preprocessing_lead_time")
    private Integer preprocessingLeadTime;

    @Column(name = "processing_lead_time")
    private Float processingLeadTime;

    @Size(max = 12)
    @Column(name = "mdm_go_h_category", length = 12)
    private String mdmGoHCategory;

    @Size(max = 10)
    @Column(name = "resource_name", length = 10)
    private String resourceName;

    @Column(name = "usage_rate")
    private Float usageRate;

    @Size(max = 1)
    @Column(name = "asl_vmi_enabled", length = 1)
    private String aslVmiEnabled;

    @Size(max = 1)
    @Column(name = "asl_consigned_from_supplier", length = 1)
    private String aslConsignedFromSupplier;

    @Size(max = 60)
    @Column(name = "asl_supplier", length = 60)
    private String aslSupplier;

    @Size(max = 10)
    @Column(name = "asl_supplier_number", length = 10)
    private String aslSupplierNumber;

    @Size(max = 8)
    @Column(name = "aslsupplier_site", length = 8)
    private String aslsupplierSite;

    @Size(max = 20)
    @Column(name = "asl_supplier_item", length = 20)
    private String aslSupplierItem;

    @Size(max = 3)
    @Column(name = "aslpurchasing_uom", length = 3)
    private String aslpurchasingUom;

    @Size(max = 25)
    @Column(name = "sl_process_code_detail", length = 25)
    private String slProcessCodeDetail;

    @Column(name = "sl_effective_from_date")
    private LocalDate slEffectiveFromDate;

    @Size(max = 25)
    @Column(name = "receipt_routing_detail", length = 25)
    private String receiptRoutingDetail;

    @Size(max = 35)
    @Column(name = "vmi_planning_party", length = 35)
    private String vmiPlanningParty;

    @Column(name = "list_price")
    private Float listPrice;

    @Size(max = 12)
    @Column(name = "mdm_global_id", length = 12)
    private String mdmGlobalId;

    @Size(max = 10)
    @Column(name = "mdm_article_number_ygart", length = 10)
    private String mdmArticleNumberYgart;

    @Size(max = 16)
    @Column(name = "lot_control", length = 16)
    private String lotControl;

    @Size(max = 28)
    @Column(name = "shelf_life_control", length = 28)
    private String shelfLifeControl;

    @Column(name = "shelf_life_days")
    private Float shelfLifeDays;

    @Size(max = 34)
    @Column(name = "serial_number_control", length = 34)
    private String serialNumberControl;

    @Column(name = "unit_volume")
    private Float unitVolume;

    @Size(max = 3)
    @Column(name = "volume_unit_of_measure", length = 3)
    private String volumeUnitOfMeasure;

    @Column(name = "unit_weight")
    private Float unitWeight;

    @Size(max = 2)
    @Column(name = "weight_unit_of_measure", length = 2)
    private String weightUnitOfMeasure;

    @Column(name = "units_in_a_box_package")
    private Float unitsInABoxPackage;

    @Size(max = 6)
    @Column(name = "revision_control", length = 6)
    private String revisionControl;

    @Column(name = "dimension_unit_height")
    private Float dimensionUnitHeight;

    @Column(name = "dimension_unit_length")
    private Float dimensionUnitLength;

    @Column(name = "dimension_unit_width")
    private Float dimensionUnitWidth;

    @Size(max = 2)
    @Column(name = "dimension_unit_of_measure", length = 2)
    private String dimensionUnitOfMeasure;

    @Size(max = 255)
    @Column(name = "hazardous_weight", length = 255)
    private String hazardousWeight;

    @Size(max = 255)
    @Column(name = "france_abc_picking_category", length = 255)
    private String franceAbcPickingCategory;

    @Size(max = 3)
    @Column(name = "mdm_temp_conditions_category", length = 3)
    private String mdmTempConditionsCategory;

    @Size(max = 5)
    @Column(name = "perish_code_category", length = 5)
    private String perishCodeCategory;

    @Size(max = 6)
    @Column(name = "un_number", length = 6)
    private String unNumber;

    @Size(max = 4)
    @Column(name = "hazard_class", length = 4)
    private String hazardClass;

    @Size(max = 2)
    @Column(name = "unit_of_issue", length = 2)
    private String unitOfIssue;

    @Size(max = 255)
    @Column(name = "rounding_factor", length = 255)
    private String roundingFactor;

    @Size(max = 40)
    @Column(name = "item_buyer_name", length = 40)
    private String itemBuyerName;

    @Size(max = 5)
    @Column(name = "hazardous_weight_uom", length = 5)
    private String hazardousWeightUom;

    @Size(max = 12)
    @Column(name = "gph_code", length = 12)
    private String gphCode;

    @Size(max = 9)
    @Column(name = "mos", length = 9)
    private String mos;

    @Size(max = 3)
    @Column(name = "lpn_package", length = 3)
    private String lpnPackage;

    @Column(name = "qte_unitaire_palette")
    private Float qteUnitairePalette;

    @Size(max = 10)
    @Column(name = "type_um", length = 10)
    private String typeUm;

    @Column(name = "hauteur_um")
    private Integer hauteurUm;

    @Column(name = "longueur_um")
    private Integer longueurUm;

    @Column(name = "largeur_um")
    private Integer largeurUm;

    @Column(name = "qte_unitaire_carton")
    private Float qteUnitaireCarton;

    @Size(max = 18)
    @Column(name = "dimensions_uc", length = 18)
    private String dimensionsUc;

    @Size(max = 20)
    @Column(name = "specifications", length = 20)
    private String specifications;

    @Size(max = 5)
    @Column(name = "cadence_poste", length = 5)
    private String cadencePoste;

    @Size(max = 40)
    @Column(name = "pfse", length = 40)
    private String pfse;

    @Size(max = 255)
    @Column(name = "rhenus", length = 255)
    private String rhenus;

    @Size(max = 8)
    @Column(name = "test_capa", length = 8)
    private String testCapa;

    @Size(max = 1)
    @Column(name = "abc", length = 1)
    private String abc;

    @Column(name = "item_creation_date")
    private LocalDate itemCreationDate;

    @Size(max = 30)
    @Column(name = "categorie_nm", length = 30)
    private String categorieNm;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public Item organizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
        return this;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public Item businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getBusinessUnitDescription() {
        return businessUnitDescription;
    }

    public Item businessUnitDescription(String businessUnitDescription) {
        this.businessUnitDescription = businessUnitDescription;
        return this;
    }

    public void setBusinessUnitDescription(String businessUnitDescription) {
        this.businessUnitDescription = businessUnitDescription;
    }

    public String getBusinessField() {
        return businessField;
    }

    public Item businessField(String businessField) {
        this.businessField = businessField;
        return this;
    }

    public void setBusinessField(String businessField) {
        this.businessField = businessField;
    }

    public String getBusinessFieldDescription() {
        return businessFieldDescription;
    }

    public Item businessFieldDescription(String businessFieldDescription) {
        this.businessFieldDescription = businessFieldDescription;
        return this;
    }

    public void setBusinessFieldDescription(String businessFieldDescription) {
        this.businessFieldDescription = businessFieldDescription;
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public Item businessLine(String businessLine) {
        this.businessLine = businessLine;
        return this;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public String getBusinessLineDescription() {
        return businessLineDescription;
    }

    public Item businessLineDescription(String businessLineDescription) {
        this.businessLineDescription = businessLineDescription;
        return this;
    }

    public void setBusinessLineDescription(String businessLineDescription) {
        this.businessLineDescription = businessLineDescription;
    }

    public String getWorkcenter() {
        return workcenter;
    }

    public Item workcenter(String workcenter) {
        this.workcenter = workcenter;
        return this;
    }

    public void setWorkcenter(String workcenter) {
        this.workcenter = workcenter;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public Item productGroup(String productGroup) {
        this.productGroup = productGroup;
        return this;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getProductGroupDescription() {
        return productGroupDescription;
    }

    public Item productGroupDescription(String productGroupDescription) {
        this.productGroupDescription = productGroupDescription;
        return this;
    }

    public void setProductGroupDescription(String productGroupDescription) {
        this.productGroupDescription = productGroupDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public Item productCategory(String productCategory) {
        this.productCategory = productCategory;
        return this;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategoryDescription() {
        return productCategoryDescription;
    }

    public Item productCategoryDescription(String productCategoryDescription) {
        this.productCategoryDescription = productCategoryDescription;
        return this;
    }

    public void setProductCategoryDescription(String productCategoryDescription) {
        this.productCategoryDescription = productCategoryDescription;
    }

    public String getProductLine() {
        return productLine;
    }

    public Item productLine(String productLine) {
        this.productLine = productLine;
        return this;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getProductLineDescription() {
        return productLineDescription;
    }

    public Item productLineDescription(String productLineDescription) {
        this.productLineDescription = productLineDescription;
        return this;
    }

    public void setProductLineDescription(String productLineDescription) {
        this.productLineDescription = productLineDescription;
    }

    public String getProductLineManager() {
        return productLineManager;
    }

    public Item productLineManager(String productLineManager) {
        this.productLineManager = productLineManager;
        return this;
    }

    public void setProductLineManager(String productLineManager) {
        this.productLineManager = productLineManager;
    }

    public String getProductDivision() {
        return productDivision;
    }

    public Item productDivision(String productDivision) {
        this.productDivision = productDivision;
        return this;
    }

    public void setProductDivision(String productDivision) {
        this.productDivision = productDivision;
    }

    public String getProductCategoryManager() {
        return productCategoryManager;
    }

    public Item productCategoryManager(String productCategoryManager) {
        this.productCategoryManager = productCategoryManager;
        return this;
    }

    public void setProductCategoryManager(String productCategoryManager) {
        this.productCategoryManager = productCategoryManager;
    }

    public String getPlantCategory() {
        return plantCategory;
    }

    public Item plantCategory(String plantCategory) {
        this.plantCategory = plantCategory;
        return this;
    }

    public void setPlantCategory(String plantCategory) {
        this.plantCategory = plantCategory;
    }

    public String getItemType() {
        return itemType;
    }

    public Item itemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getPrimaryUnitOfMeasure() {
        return primaryUnitOfMeasure;
    }

    public Item primaryUnitOfMeasure(String primaryUnitOfMeasure) {
        this.primaryUnitOfMeasure = primaryUnitOfMeasure;
        return this;
    }

    public void setPrimaryUnitOfMeasure(String primaryUnitOfMeasure) {
        this.primaryUnitOfMeasure = primaryUnitOfMeasure;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public Item itemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
        return this;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Item itemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getInventoryItemStatusCode() {
        return inventoryItemStatusCode;
    }

    public Item inventoryItemStatusCode(String inventoryItemStatusCode) {
        this.inventoryItemStatusCode = inventoryItemStatusCode;
        return this;
    }

    public void setInventoryItemStatusCode(String inventoryItemStatusCode) {
        this.inventoryItemStatusCode = inventoryItemStatusCode;
    }

    public String getPlanningMakeOrBuyCode() {
        return planningMakeOrBuyCode;
    }

    public Item planningMakeOrBuyCode(String planningMakeOrBuyCode) {
        this.planningMakeOrBuyCode = planningMakeOrBuyCode;
        return this;
    }

    public void setPlanningMakeOrBuyCode(String planningMakeOrBuyCode) {
        this.planningMakeOrBuyCode = planningMakeOrBuyCode;
    }

    public String getItemPlannerCode() {
        return itemPlannerCode;
    }

    public Item itemPlannerCode(String itemPlannerCode) {
        this.itemPlannerCode = itemPlannerCode;
        return this;
    }

    public void setItemPlannerCode(String itemPlannerCode) {
        this.itemPlannerCode = itemPlannerCode;
    }

    public String getPlannerName() {
        return plannerName;
    }

    public Item plannerName(String plannerName) {
        this.plannerName = plannerName;
        return this;
    }

    public void setPlannerName(String plannerName) {
        this.plannerName = plannerName;
    }

    public String getMrpPlanningMethod() {
        return mrpPlanningMethod;
    }

    public Item mrpPlanningMethod(String mrpPlanningMethod) {
        this.mrpPlanningMethod = mrpPlanningMethod;
        return this;
    }

    public void setMrpPlanningMethod(String mrpPlanningMethod) {
        this.mrpPlanningMethod = mrpPlanningMethod;
    }

    public String getInventoryPlanningMethod() {
        return inventoryPlanningMethod;
    }

    public Item inventoryPlanningMethod(String inventoryPlanningMethod) {
        this.inventoryPlanningMethod = inventoryPlanningMethod;
        return this;
    }

    public void setInventoryPlanningMethod(String inventoryPlanningMethod) {
        this.inventoryPlanningMethod = inventoryPlanningMethod;
    }

    public Float getLeadTimeLotSize() {
        return leadTimeLotSize;
    }

    public Item leadTimeLotSize(Float leadTimeLotSize) {
        this.leadTimeLotSize = leadTimeLotSize;
        return this;
    }

    public void setLeadTimeLotSize(Float leadTimeLotSize) {
        this.leadTimeLotSize = leadTimeLotSize;
    }

    public Float getFixedLeadTime() {
        return fixedLeadTime;
    }

    public Item fixedLeadTime(Float fixedLeadTime) {
        this.fixedLeadTime = fixedLeadTime;
        return this;
    }

    public void setFixedLeadTime(Float fixedLeadTime) {
        this.fixedLeadTime = fixedLeadTime;
    }

    public Float getFixedOrderQuanitiy() {
        return fixedOrderQuanitiy;
    }

    public Item fixedOrderQuanitiy(Float fixedOrderQuanitiy) {
        this.fixedOrderQuanitiy = fixedOrderQuanitiy;
        return this;
    }

    public void setFixedOrderQuanitiy(Float fixedOrderQuanitiy) {
        this.fixedOrderQuanitiy = fixedOrderQuanitiy;
    }

    public String getFixedLotMultiplier() {
        return fixedLotMultiplier;
    }

    public Item fixedLotMultiplier(String fixedLotMultiplier) {
        this.fixedLotMultiplier = fixedLotMultiplier;
        return this;
    }

    public void setFixedLotMultiplier(String fixedLotMultiplier) {
        this.fixedLotMultiplier = fixedLotMultiplier;
    }

    public Float getCurrentSafetyStockQuantity() {
        return currentSafetyStockQuantity;
    }

    public Item currentSafetyStockQuantity(Float currentSafetyStockQuantity) {
        this.currentSafetyStockQuantity = currentSafetyStockQuantity;
        return this;
    }

    public void setCurrentSafetyStockQuantity(Float currentSafetyStockQuantity) {
        this.currentSafetyStockQuantity = currentSafetyStockQuantity;
    }

    public String getDemandTimeFence() {
        return demandTimeFence;
    }

    public Item demandTimeFence(String demandTimeFence) {
        this.demandTimeFence = demandTimeFence;
        return this;
    }

    public void setDemandTimeFence(String demandTimeFence) {
        this.demandTimeFence = demandTimeFence;
    }

    public Float getDemandTimeFenceDays() {
        return demandTimeFenceDays;
    }

    public Item demandTimeFenceDays(Float demandTimeFenceDays) {
        this.demandTimeFenceDays = demandTimeFenceDays;
        return this;
    }

    public void setDemandTimeFenceDays(Float demandTimeFenceDays) {
        this.demandTimeFenceDays = demandTimeFenceDays;
    }

    public Integer getFixedDaysSupply() {
        return fixedDaysSupply;
    }

    public Item fixedDaysSupply(Integer fixedDaysSupply) {
        this.fixedDaysSupply = fixedDaysSupply;
        return this;
    }

    public void setFixedDaysSupply(Integer fixedDaysSupply) {
        this.fixedDaysSupply = fixedDaysSupply;
    }

    public Float getFrozenItemCost() {
        return frozenItemCost;
    }

    public Item frozenItemCost(Float frozenItemCost) {
        this.frozenItemCost = frozenItemCost;
        return this;
    }

    public void setFrozenItemCost(Float frozenItemCost) {
        this.frozenItemCost = frozenItemCost;
    }

    public Float getFrozenMaterialCost() {
        return frozenMaterialCost;
    }

    public Item frozenMaterialCost(Float frozenMaterialCost) {
        this.frozenMaterialCost = frozenMaterialCost;
        return this;
    }

    public void setFrozenMaterialCost(Float frozenMaterialCost) {
        this.frozenMaterialCost = frozenMaterialCost;
    }

    public Integer getMaxMinmaxQuantity() {
        return maxMinmaxQuantity;
    }

    public Item maxMinmaxQuantity(Integer maxMinmaxQuantity) {
        this.maxMinmaxQuantity = maxMinmaxQuantity;
        return this;
    }

    public void setMaxMinmaxQuantity(Integer maxMinmaxQuantity) {
        this.maxMinmaxQuantity = maxMinmaxQuantity;
    }

    public Integer getMinMinmaxQuantity() {
        return minMinmaxQuantity;
    }

    public Item minMinmaxQuantity(Integer minMinmaxQuantity) {
        this.minMinmaxQuantity = minMinmaxQuantity;
        return this;
    }

    public void setMinMinmaxQuantity(Integer minMinmaxQuantity) {
        this.minMinmaxQuantity = minMinmaxQuantity;
    }

    public Integer getOrderQuantityMax() {
        return orderQuantityMax;
    }

    public Item orderQuantityMax(Integer orderQuantityMax) {
        this.orderQuantityMax = orderQuantityMax;
        return this;
    }

    public void setOrderQuantityMax(Integer orderQuantityMax) {
        this.orderQuantityMax = orderQuantityMax;
    }

    public Integer getOrderQuantityMin() {
        return orderQuantityMin;
    }

    public Item orderQuantityMin(Integer orderQuantityMin) {
        this.orderQuantityMin = orderQuantityMin;
        return this;
    }

    public void setOrderQuantityMin(Integer orderQuantityMin) {
        this.orderQuantityMin = orderQuantityMin;
    }

    public Float getPlanningTimeFenceDays() {
        return planningTimeFenceDays;
    }

    public Item planningTimeFenceDays(Float planningTimeFenceDays) {
        this.planningTimeFenceDays = planningTimeFenceDays;
        return this;
    }

    public void setPlanningTimeFenceDays(Float planningTimeFenceDays) {
        this.planningTimeFenceDays = planningTimeFenceDays;
    }

    public Float getPostprocessingLeadTime() {
        return postprocessingLeadTime;
    }

    public Item postprocessingLeadTime(Float postprocessingLeadTime) {
        this.postprocessingLeadTime = postprocessingLeadTime;
        return this;
    }

    public void setPostprocessingLeadTime(Float postprocessingLeadTime) {
        this.postprocessingLeadTime = postprocessingLeadTime;
    }

    public Integer getPreprocessingLeadTime() {
        return preprocessingLeadTime;
    }

    public Item preprocessingLeadTime(Integer preprocessingLeadTime) {
        this.preprocessingLeadTime = preprocessingLeadTime;
        return this;
    }

    public void setPreprocessingLeadTime(Integer preprocessingLeadTime) {
        this.preprocessingLeadTime = preprocessingLeadTime;
    }

    public Float getProcessingLeadTime() {
        return processingLeadTime;
    }

    public Item processingLeadTime(Float processingLeadTime) {
        this.processingLeadTime = processingLeadTime;
        return this;
    }

    public void setProcessingLeadTime(Float processingLeadTime) {
        this.processingLeadTime = processingLeadTime;
    }

    public String getMdmGoHCategory() {
        return mdmGoHCategory;
    }

    public Item mdmGoHCategory(String mdmGoHCategory) {
        this.mdmGoHCategory = mdmGoHCategory;
        return this;
    }

    public void setMdmGoHCategory(String mdmGoHCategory) {
        this.mdmGoHCategory = mdmGoHCategory;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Item resourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Float getUsageRate() {
        return usageRate;
    }

    public Item usageRate(Float usageRate) {
        this.usageRate = usageRate;
        return this;
    }

    public void setUsageRate(Float usageRate) {
        this.usageRate = usageRate;
    }

    public String getAslVmiEnabled() {
        return aslVmiEnabled;
    }

    public Item aslVmiEnabled(String aslVmiEnabled) {
        this.aslVmiEnabled = aslVmiEnabled;
        return this;
    }

    public void setAslVmiEnabled(String aslVmiEnabled) {
        this.aslVmiEnabled = aslVmiEnabled;
    }

    public String getAslConsignedFromSupplier() {
        return aslConsignedFromSupplier;
    }

    public Item aslConsignedFromSupplier(String aslConsignedFromSupplier) {
        this.aslConsignedFromSupplier = aslConsignedFromSupplier;
        return this;
    }

    public void setAslConsignedFromSupplier(String aslConsignedFromSupplier) {
        this.aslConsignedFromSupplier = aslConsignedFromSupplier;
    }

    public String getAslSupplier() {
        return aslSupplier;
    }

    public Item aslSupplier(String aslSupplier) {
        this.aslSupplier = aslSupplier;
        return this;
    }

    public void setAslSupplier(String aslSupplier) {
        this.aslSupplier = aslSupplier;
    }

    public String getAslSupplierNumber() {
        return aslSupplierNumber;
    }

    public Item aslSupplierNumber(String aslSupplierNumber) {
        this.aslSupplierNumber = aslSupplierNumber;
        return this;
    }

    public void setAslSupplierNumber(String aslSupplierNumber) {
        this.aslSupplierNumber = aslSupplierNumber;
    }

    public String getAslsupplierSite() {
        return aslsupplierSite;
    }

    public Item aslsupplierSite(String aslsupplierSite) {
        this.aslsupplierSite = aslsupplierSite;
        return this;
    }

    public void setAslsupplierSite(String aslsupplierSite) {
        this.aslsupplierSite = aslsupplierSite;
    }

    public String getAslSupplierItem() {
        return aslSupplierItem;
    }

    public Item aslSupplierItem(String aslSupplierItem) {
        this.aslSupplierItem = aslSupplierItem;
        return this;
    }

    public void setAslSupplierItem(String aslSupplierItem) {
        this.aslSupplierItem = aslSupplierItem;
    }

    public String getAslpurchasingUom() {
        return aslpurchasingUom;
    }

    public Item aslpurchasingUom(String aslpurchasingUom) {
        this.aslpurchasingUom = aslpurchasingUom;
        return this;
    }

    public void setAslpurchasingUom(String aslpurchasingUom) {
        this.aslpurchasingUom = aslpurchasingUom;
    }

    public String getSlProcessCodeDetail() {
        return slProcessCodeDetail;
    }

    public Item slProcessCodeDetail(String slProcessCodeDetail) {
        this.slProcessCodeDetail = slProcessCodeDetail;
        return this;
    }

    public void setSlProcessCodeDetail(String slProcessCodeDetail) {
        this.slProcessCodeDetail = slProcessCodeDetail;
    }

    public LocalDate getSlEffectiveFromDate() {
        return slEffectiveFromDate;
    }

    public Item slEffectiveFromDate(LocalDate slEffectiveFromDate) {
        this.slEffectiveFromDate = slEffectiveFromDate;
        return this;
    }

    public void setSlEffectiveFromDate(LocalDate slEffectiveFromDate) {
        this.slEffectiveFromDate = slEffectiveFromDate;
    }

    public String getReceiptRoutingDetail() {
        return receiptRoutingDetail;
    }

    public Item receiptRoutingDetail(String receiptRoutingDetail) {
        this.receiptRoutingDetail = receiptRoutingDetail;
        return this;
    }

    public void setReceiptRoutingDetail(String receiptRoutingDetail) {
        this.receiptRoutingDetail = receiptRoutingDetail;
    }

    public String getVmiPlanningParty() {
        return vmiPlanningParty;
    }

    public Item vmiPlanningParty(String vmiPlanningParty) {
        this.vmiPlanningParty = vmiPlanningParty;
        return this;
    }

    public void setVmiPlanningParty(String vmiPlanningParty) {
        this.vmiPlanningParty = vmiPlanningParty;
    }

    public Float getListPrice() {
        return listPrice;
    }

    public Item listPrice(Float listPrice) {
        this.listPrice = listPrice;
        return this;
    }

    public void setListPrice(Float listPrice) {
        this.listPrice = listPrice;
    }

    public String getMdmGlobalId() {
        return mdmGlobalId;
    }

    public Item mdmGlobalId(String mdmGlobalId) {
        this.mdmGlobalId = mdmGlobalId;
        return this;
    }

    public void setMdmGlobalId(String mdmGlobalId) {
        this.mdmGlobalId = mdmGlobalId;
    }

    public String getMdmArticleNumberYgart() {
        return mdmArticleNumberYgart;
    }

    public Item mdmArticleNumberYgart(String mdmArticleNumberYgart) {
        this.mdmArticleNumberYgart = mdmArticleNumberYgart;
        return this;
    }

    public void setMdmArticleNumberYgart(String mdmArticleNumberYgart) {
        this.mdmArticleNumberYgart = mdmArticleNumberYgart;
    }

    public String getLotControl() {
        return lotControl;
    }

    public Item lotControl(String lotControl) {
        this.lotControl = lotControl;
        return this;
    }

    public void setLotControl(String lotControl) {
        this.lotControl = lotControl;
    }

    public String getShelfLifeControl() {
        return shelfLifeControl;
    }

    public Item shelfLifeControl(String shelfLifeControl) {
        this.shelfLifeControl = shelfLifeControl;
        return this;
    }

    public void setShelfLifeControl(String shelfLifeControl) {
        this.shelfLifeControl = shelfLifeControl;
    }

    public Float getShelfLifeDays() {
        return shelfLifeDays;
    }

    public Item shelfLifeDays(Float shelfLifeDays) {
        this.shelfLifeDays = shelfLifeDays;
        return this;
    }

    public void setShelfLifeDays(Float shelfLifeDays) {
        this.shelfLifeDays = shelfLifeDays;
    }

    public String getSerialNumberControl() {
        return serialNumberControl;
    }

    public Item serialNumberControl(String serialNumberControl) {
        this.serialNumberControl = serialNumberControl;
        return this;
    }

    public void setSerialNumberControl(String serialNumberControl) {
        this.serialNumberControl = serialNumberControl;
    }

    public Float getUnitVolume() {
        return unitVolume;
    }

    public Item unitVolume(Float unitVolume) {
        this.unitVolume = unitVolume;
        return this;
    }

    public void setUnitVolume(Float unitVolume) {
        this.unitVolume = unitVolume;
    }

    public String getVolumeUnitOfMeasure() {
        return volumeUnitOfMeasure;
    }

    public Item volumeUnitOfMeasure(String volumeUnitOfMeasure) {
        this.volumeUnitOfMeasure = volumeUnitOfMeasure;
        return this;
    }

    public void setVolumeUnitOfMeasure(String volumeUnitOfMeasure) {
        this.volumeUnitOfMeasure = volumeUnitOfMeasure;
    }

    public Float getUnitWeight() {
        return unitWeight;
    }

    public Item unitWeight(Float unitWeight) {
        this.unitWeight = unitWeight;
        return this;
    }

    public void setUnitWeight(Float unitWeight) {
        this.unitWeight = unitWeight;
    }

    public String getWeightUnitOfMeasure() {
        return weightUnitOfMeasure;
    }

    public Item weightUnitOfMeasure(String weightUnitOfMeasure) {
        this.weightUnitOfMeasure = weightUnitOfMeasure;
        return this;
    }

    public void setWeightUnitOfMeasure(String weightUnitOfMeasure) {
        this.weightUnitOfMeasure = weightUnitOfMeasure;
    }

    public Float getUnitsInABoxPackage() {
        return unitsInABoxPackage;
    }

    public Item unitsInABoxPackage(Float unitsInABoxPackage) {
        this.unitsInABoxPackage = unitsInABoxPackage;
        return this;
    }

    public void setUnitsInABoxPackage(Float unitsInABoxPackage) {
        this.unitsInABoxPackage = unitsInABoxPackage;
    }

    public String getRevisionControl() {
        return revisionControl;
    }

    public Item revisionControl(String revisionControl) {
        this.revisionControl = revisionControl;
        return this;
    }

    public void setRevisionControl(String revisionControl) {
        this.revisionControl = revisionControl;
    }

    public Float getDimensionUnitHeight() {
        return dimensionUnitHeight;
    }

    public Item dimensionUnitHeight(Float dimensionUnitHeight) {
        this.dimensionUnitHeight = dimensionUnitHeight;
        return this;
    }

    public void setDimensionUnitHeight(Float dimensionUnitHeight) {
        this.dimensionUnitHeight = dimensionUnitHeight;
    }

    public Float getDimensionUnitLength() {
        return dimensionUnitLength;
    }

    public Item dimensionUnitLength(Float dimensionUnitLength) {
        this.dimensionUnitLength = dimensionUnitLength;
        return this;
    }

    public void setDimensionUnitLength(Float dimensionUnitLength) {
        this.dimensionUnitLength = dimensionUnitLength;
    }

    public Float getDimensionUnitWidth() {
        return dimensionUnitWidth;
    }

    public Item dimensionUnitWidth(Float dimensionUnitWidth) {
        this.dimensionUnitWidth = dimensionUnitWidth;
        return this;
    }

    public void setDimensionUnitWidth(Float dimensionUnitWidth) {
        this.dimensionUnitWidth = dimensionUnitWidth;
    }

    public String getDimensionUnitOfMeasure() {
        return dimensionUnitOfMeasure;
    }

    public Item dimensionUnitOfMeasure(String dimensionUnitOfMeasure) {
        this.dimensionUnitOfMeasure = dimensionUnitOfMeasure;
        return this;
    }

    public void setDimensionUnitOfMeasure(String dimensionUnitOfMeasure) {
        this.dimensionUnitOfMeasure = dimensionUnitOfMeasure;
    }

    public String getHazardousWeight() {
        return hazardousWeight;
    }

    public Item hazardousWeight(String hazardousWeight) {
        this.hazardousWeight = hazardousWeight;
        return this;
    }

    public void setHazardousWeight(String hazardousWeight) {
        this.hazardousWeight = hazardousWeight;
    }

    public String getFranceAbcPickingCategory() {
        return franceAbcPickingCategory;
    }

    public Item franceAbcPickingCategory(String franceAbcPickingCategory) {
        this.franceAbcPickingCategory = franceAbcPickingCategory;
        return this;
    }

    public void setFranceAbcPickingCategory(String franceAbcPickingCategory) {
        this.franceAbcPickingCategory = franceAbcPickingCategory;
    }

    public String getMdmTempConditionsCategory() {
        return mdmTempConditionsCategory;
    }

    public Item mdmTempConditionsCategory(String mdmTempConditionsCategory) {
        this.mdmTempConditionsCategory = mdmTempConditionsCategory;
        return this;
    }

    public void setMdmTempConditionsCategory(String mdmTempConditionsCategory) {
        this.mdmTempConditionsCategory = mdmTempConditionsCategory;
    }

    public String getPerishCodeCategory() {
        return perishCodeCategory;
    }

    public Item perishCodeCategory(String perishCodeCategory) {
        this.perishCodeCategory = perishCodeCategory;
        return this;
    }

    public void setPerishCodeCategory(String perishCodeCategory) {
        this.perishCodeCategory = perishCodeCategory;
    }

    public String getUnNumber() {
        return unNumber;
    }

    public Item unNumber(String unNumber) {
        this.unNumber = unNumber;
        return this;
    }

    public void setUnNumber(String unNumber) {
        this.unNumber = unNumber;
    }

    public String getHazardClass() {
        return hazardClass;
    }

    public Item hazardClass(String hazardClass) {
        this.hazardClass = hazardClass;
        return this;
    }

    public void setHazardClass(String hazardClass) {
        this.hazardClass = hazardClass;
    }

    public String getUnitOfIssue() {
        return unitOfIssue;
    }

    public Item unitOfIssue(String unitOfIssue) {
        this.unitOfIssue = unitOfIssue;
        return this;
    }

    public void setUnitOfIssue(String unitOfIssue) {
        this.unitOfIssue = unitOfIssue;
    }

    public String getRoundingFactor() {
        return roundingFactor;
    }

    public Item roundingFactor(String roundingFactor) {
        this.roundingFactor = roundingFactor;
        return this;
    }

    public void setRoundingFactor(String roundingFactor) {
        this.roundingFactor = roundingFactor;
    }

    public String getItemBuyerName() {
        return itemBuyerName;
    }

    public Item itemBuyerName(String itemBuyerName) {
        this.itemBuyerName = itemBuyerName;
        return this;
    }

    public void setItemBuyerName(String itemBuyerName) {
        this.itemBuyerName = itemBuyerName;
    }

    public String getHazardousWeightUom() {
        return hazardousWeightUom;
    }

    public Item hazardousWeightUom(String hazardousWeightUom) {
        this.hazardousWeightUom = hazardousWeightUom;
        return this;
    }

    public void setHazardousWeightUom(String hazardousWeightUom) {
        this.hazardousWeightUom = hazardousWeightUom;
    }

    public String getGphCode() {
        return gphCode;
    }

    public Item gphCode(String gphCode) {
        this.gphCode = gphCode;
        return this;
    }

    public void setGphCode(String gphCode) {
        this.gphCode = gphCode;
    }

    public String getMos() {
        return mos;
    }

    public Item mos(String mos) {
        this.mos = mos;
        return this;
    }

    public void setMos(String mos) {
        this.mos = mos;
    }

    public String getLpnPackage() {
        return lpnPackage;
    }

    public Item lpnPackage(String lpnPackage) {
        this.lpnPackage = lpnPackage;
        return this;
    }

    public void setLpnPackage(String lpnPackage) {
        this.lpnPackage = lpnPackage;
    }

    public Float getQteUnitairePalette() {
        return qteUnitairePalette;
    }

    public Item qteUnitairePalette(Float qteUnitairePalette) {
        this.qteUnitairePalette = qteUnitairePalette;
        return this;
    }

    public void setQteUnitairePalette(Float qteUnitairePalette) {
        this.qteUnitairePalette = qteUnitairePalette;
    }

    public String getTypeUm() {
        return typeUm;
    }

    public Item typeUm(String typeUm) {
        this.typeUm = typeUm;
        return this;
    }

    public void setTypeUm(String typeUm) {
        this.typeUm = typeUm;
    }

    public Integer getHauteurUm() {
        return hauteurUm;
    }

    public Item hauteurUm(Integer hauteurUm) {
        this.hauteurUm = hauteurUm;
        return this;
    }

    public void setHauteurUm(Integer hauteurUm) {
        this.hauteurUm = hauteurUm;
    }

    public Integer getLongueurUm() {
        return longueurUm;
    }

    public Item longueurUm(Integer longueurUm) {
        this.longueurUm = longueurUm;
        return this;
    }

    public void setLongueurUm(Integer longueurUm) {
        this.longueurUm = longueurUm;
    }

    public Integer getLargeurUm() {
        return largeurUm;
    }

    public Item largeurUm(Integer largeurUm) {
        this.largeurUm = largeurUm;
        return this;
    }

    public void setLargeurUm(Integer largeurUm) {
        this.largeurUm = largeurUm;
    }

    public Float getQteUnitaireCarton() {
        return qteUnitaireCarton;
    }

    public Item qteUnitaireCarton(Float qteUnitaireCarton) {
        this.qteUnitaireCarton = qteUnitaireCarton;
        return this;
    }

    public void setQteUnitaireCarton(Float qteUnitaireCarton) {
        this.qteUnitaireCarton = qteUnitaireCarton;
    }

    public String getDimensionsUc() {
        return dimensionsUc;
    }

    public Item dimensionsUc(String dimensionsUc) {
        this.dimensionsUc = dimensionsUc;
        return this;
    }

    public void setDimensionsUc(String dimensionsUc) {
        this.dimensionsUc = dimensionsUc;
    }

    public String getSpecifications() {
        return specifications;
    }

    public Item specifications(String specifications) {
        this.specifications = specifications;
        return this;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getCadencePoste() {
        return cadencePoste;
    }

    public Item cadencePoste(String cadencePoste) {
        this.cadencePoste = cadencePoste;
        return this;
    }

    public void setCadencePoste(String cadencePoste) {
        this.cadencePoste = cadencePoste;
    }

    public String getPfse() {
        return pfse;
    }

    public Item pfse(String pfse) {
        this.pfse = pfse;
        return this;
    }

    public void setPfse(String pfse) {
        this.pfse = pfse;
    }

    public String getRhenus() {
        return rhenus;
    }

    public Item rhenus(String rhenus) {
        this.rhenus = rhenus;
        return this;
    }

    public void setRhenus(String rhenus) {
        this.rhenus = rhenus;
    }

    public String getTestCapa() {
        return testCapa;
    }

    public Item testCapa(String testCapa) {
        this.testCapa = testCapa;
        return this;
    }

    public void setTestCapa(String testCapa) {
        this.testCapa = testCapa;
    }

    public String getAbc() {
        return abc;
    }

    public Item abc(String abc) {
        this.abc = abc;
        return this;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

    public LocalDate getItemCreationDate() {
        return itemCreationDate;
    }

    public Item itemCreationDate(LocalDate itemCreationDate) {
        this.itemCreationDate = itemCreationDate;
        return this;
    }

    public void setItemCreationDate(LocalDate itemCreationDate) {
        this.itemCreationDate = itemCreationDate;
    }

    public String getCategorieNm() {
        return categorieNm;
    }

    public Item categorieNm(String categorieNm) {
        this.categorieNm = categorieNm;
        return this;
    }

    public void setCategorieNm(String categorieNm) {
        this.categorieNm = categorieNm;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return id != null && id.equals(((Item) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", organizationCode='" + getOrganizationCode() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", businessUnitDescription='" + getBusinessUnitDescription() + "'" +
            ", businessField='" + getBusinessField() + "'" +
            ", businessFieldDescription='" + getBusinessFieldDescription() + "'" +
            ", businessLine='" + getBusinessLine() + "'" +
            ", businessLineDescription='" + getBusinessLineDescription() + "'" +
            ", workcenter='" + getWorkcenter() + "'" +
            ", productGroup='" + getProductGroup() + "'" +
            ", productGroupDescription='" + getProductGroupDescription() + "'" +
            ", productCategory='" + getProductCategory() + "'" +
            ", productCategoryDescription='" + getProductCategoryDescription() + "'" +
            ", productLine='" + getProductLine() + "'" +
            ", productLineDescription='" + getProductLineDescription() + "'" +
            ", productLineManager='" + getProductLineManager() + "'" +
            ", productDivision='" + getProductDivision() + "'" +
            ", productCategoryManager='" + getProductCategoryManager() + "'" +
            ", plantCategory='" + getPlantCategory() + "'" +
            ", itemType='" + getItemType() + "'" +
            ", primaryUnitOfMeasure='" + getPrimaryUnitOfMeasure() + "'" +
            ", itemNumber='" + getItemNumber() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", inventoryItemStatusCode='" + getInventoryItemStatusCode() + "'" +
            ", planningMakeOrBuyCode='" + getPlanningMakeOrBuyCode() + "'" +
            ", itemPlannerCode='" + getItemPlannerCode() + "'" +
            ", plannerName='" + getPlannerName() + "'" +
            ", mrpPlanningMethod='" + getMrpPlanningMethod() + "'" +
            ", inventoryPlanningMethod='" + getInventoryPlanningMethod() + "'" +
            ", leadTimeLotSize=" + getLeadTimeLotSize() +
            ", fixedLeadTime=" + getFixedLeadTime() +
            ", fixedOrderQuanitiy=" + getFixedOrderQuanitiy() +
            ", fixedLotMultiplier='" + getFixedLotMultiplier() + "'" +
            ", currentSafetyStockQuantity=" + getCurrentSafetyStockQuantity() +
            ", demandTimeFence='" + getDemandTimeFence() + "'" +
            ", demandTimeFenceDays=" + getDemandTimeFenceDays() +
            ", fixedDaysSupply=" + getFixedDaysSupply() +
            ", frozenItemCost=" + getFrozenItemCost() +
            ", frozenMaterialCost=" + getFrozenMaterialCost() +
            ", maxMinmaxQuantity=" + getMaxMinmaxQuantity() +
            ", minMinmaxQuantity=" + getMinMinmaxQuantity() +
            ", orderQuantityMax=" + getOrderQuantityMax() +
            ", orderQuantityMin=" + getOrderQuantityMin() +
            ", planningTimeFenceDays=" + getPlanningTimeFenceDays() +
            ", postprocessingLeadTime=" + getPostprocessingLeadTime() +
            ", preprocessingLeadTime=" + getPreprocessingLeadTime() +
            ", processingLeadTime=" + getProcessingLeadTime() +
            ", mdmGoHCategory='" + getMdmGoHCategory() + "'" +
            ", resourceName='" + getResourceName() + "'" +
            ", usageRate=" + getUsageRate() +
            ", aslVmiEnabled='" + getAslVmiEnabled() + "'" +
            ", aslConsignedFromSupplier='" + getAslConsignedFromSupplier() + "'" +
            ", aslSupplier='" + getAslSupplier() + "'" +
            ", aslSupplierNumber='" + getAslSupplierNumber() + "'" +
            ", aslsupplierSite='" + getAslsupplierSite() + "'" +
            ", aslSupplierItem='" + getAslSupplierItem() + "'" +
            ", aslpurchasingUom='" + getAslpurchasingUom() + "'" +
            ", slProcessCodeDetail='" + getSlProcessCodeDetail() + "'" +
            ", slEffectiveFromDate='" + getSlEffectiveFromDate() + "'" +
            ", receiptRoutingDetail='" + getReceiptRoutingDetail() + "'" +
            ", vmiPlanningParty='" + getVmiPlanningParty() + "'" +
            ", listPrice=" + getListPrice() +
            ", mdmGlobalId='" + getMdmGlobalId() + "'" +
            ", mdmArticleNumberYgart='" + getMdmArticleNumberYgart() + "'" +
            ", lotControl='" + getLotControl() + "'" +
            ", shelfLifeControl='" + getShelfLifeControl() + "'" +
            ", shelfLifeDays=" + getShelfLifeDays() +
            ", serialNumberControl='" + getSerialNumberControl() + "'" +
            ", unitVolume=" + getUnitVolume() +
            ", volumeUnitOfMeasure='" + getVolumeUnitOfMeasure() + "'" +
            ", unitWeight=" + getUnitWeight() +
            ", weightUnitOfMeasure='" + getWeightUnitOfMeasure() + "'" +
            ", unitsInABoxPackage=" + getUnitsInABoxPackage() +
            ", revisionControl='" + getRevisionControl() + "'" +
            ", dimensionUnitHeight=" + getDimensionUnitHeight() +
            ", dimensionUnitLength=" + getDimensionUnitLength() +
            ", dimensionUnitWidth=" + getDimensionUnitWidth() +
            ", dimensionUnitOfMeasure='" + getDimensionUnitOfMeasure() + "'" +
            ", hazardousWeight='" + getHazardousWeight() + "'" +
            ", franceAbcPickingCategory='" + getFranceAbcPickingCategory() + "'" +
            ", mdmTempConditionsCategory='" + getMdmTempConditionsCategory() + "'" +
            ", perishCodeCategory='" + getPerishCodeCategory() + "'" +
            ", unNumber='" + getUnNumber() + "'" +
            ", hazardClass='" + getHazardClass() + "'" +
            ", unitOfIssue='" + getUnitOfIssue() + "'" +
            ", roundingFactor='" + getRoundingFactor() + "'" +
            ", itemBuyerName='" + getItemBuyerName() + "'" +
            ", hazardousWeightUom='" + getHazardousWeightUom() + "'" +
            ", gphCode='" + getGphCode() + "'" +
            ", mos='" + getMos() + "'" +
            ", lpnPackage='" + getLpnPackage() + "'" +
            ", qteUnitairePalette=" + getQteUnitairePalette() +
            ", typeUm='" + getTypeUm() + "'" +
            ", hauteurUm=" + getHauteurUm() +
            ", longueurUm=" + getLongueurUm() +
            ", largeurUm=" + getLargeurUm() +
            ", qteUnitaireCarton=" + getQteUnitaireCarton() +
            ", dimensionsUc='" + getDimensionsUc() + "'" +
            ", specifications='" + getSpecifications() + "'" +
            ", cadencePoste='" + getCadencePoste() + "'" +
            ", pfse='" + getPfse() + "'" +
            ", rhenus='" + getRhenus() + "'" +
            ", testCapa='" + getTestCapa() + "'" +
            ", abc='" + getAbc() + "'" +
            ", itemCreationDate='" + getItemCreationDate() + "'" +
            ", categorieNm='" + getCategorieNm() + "'" +
            "}";
    }
}
