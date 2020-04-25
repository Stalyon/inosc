package com.stalyon.web.rest;

import com.stalyon.InoscApp;
import com.stalyon.domain.Bom;
import com.stalyon.repository.BomRepository;

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
 * Integration tests for the {@link BomResource} REST controller.
 */
@SpringBootTest(classes = InoscApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class BomResourceIT {

    private static final String DEFAULT_ITEM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_BOM_OPERATION_NUMBER = 1;
    private static final Integer UPDATED_BOM_OPERATION_NUMBER = 2;

    private static final Integer DEFAULT_BOM_SEQUENCE_NUMBER = 1;
    private static final Integer UPDATED_BOM_SEQUENCE_NUMBER = 2;

    private static final String DEFAULT_COMPONENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COMPONENT_NUMBER = "BBBBBBBBBB";

    private static final Float DEFAULT_QUANTITY = 1F;
    private static final Float UPDATED_QUANTITY = 2F;

    private static final Float DEFAULT_BOM_YIELD = 1F;
    private static final Float UPDATED_BOM_YIELD = 2F;

    private static final LocalDate DEFAULT_EFF_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFF_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DIS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIS_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private BomRepository bomRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBomMockMvc;

    private Bom bom;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bom createEntity(EntityManager em) {
        Bom bom = new Bom()
            .itemNumber(DEFAULT_ITEM_NUMBER)
            .bomOperationNumber(DEFAULT_BOM_OPERATION_NUMBER)
            .bomSequenceNumber(DEFAULT_BOM_SEQUENCE_NUMBER)
            .componentNumber(DEFAULT_COMPONENT_NUMBER)
            .quantity(DEFAULT_QUANTITY)
            .bomYield(DEFAULT_BOM_YIELD)
            .effDate(DEFAULT_EFF_DATE)
            .disDate(DEFAULT_DIS_DATE);
        return bom;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bom createUpdatedEntity(EntityManager em) {
        Bom bom = new Bom()
            .itemNumber(UPDATED_ITEM_NUMBER)
            .bomOperationNumber(UPDATED_BOM_OPERATION_NUMBER)
            .bomSequenceNumber(UPDATED_BOM_SEQUENCE_NUMBER)
            .componentNumber(UPDATED_COMPONENT_NUMBER)
            .quantity(UPDATED_QUANTITY)
            .bomYield(UPDATED_BOM_YIELD)
            .effDate(UPDATED_EFF_DATE)
            .disDate(UPDATED_DIS_DATE);
        return bom;
    }

    @BeforeEach
    public void initTest() {
        bom = createEntity(em);
    }

    @Test
    @Transactional
    public void createBom() throws Exception {
        int databaseSizeBeforeCreate = bomRepository.findAll().size();

        // Create the Bom
        restBomMockMvc.perform(post("/api/boms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bom)))
            .andExpect(status().isCreated());

        // Validate the Bom in the database
        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeCreate + 1);
        Bom testBom = bomList.get(bomList.size() - 1);
        assertThat(testBom.getItemNumber()).isEqualTo(DEFAULT_ITEM_NUMBER);
        assertThat(testBom.getBomOperationNumber()).isEqualTo(DEFAULT_BOM_OPERATION_NUMBER);
        assertThat(testBom.getBomSequenceNumber()).isEqualTo(DEFAULT_BOM_SEQUENCE_NUMBER);
        assertThat(testBom.getComponentNumber()).isEqualTo(DEFAULT_COMPONENT_NUMBER);
        assertThat(testBom.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testBom.getBomYield()).isEqualTo(DEFAULT_BOM_YIELD);
        assertThat(testBom.getEffDate()).isEqualTo(DEFAULT_EFF_DATE);
        assertThat(testBom.getDisDate()).isEqualTo(DEFAULT_DIS_DATE);
    }

    @Test
    @Transactional
    public void createBomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bomRepository.findAll().size();

        // Create the Bom with an existing ID
        bom.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBomMockMvc.perform(post("/api/boms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bom)))
            .andExpect(status().isBadRequest());

        // Validate the Bom in the database
        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkItemNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = bomRepository.findAll().size();
        // set the field null
        bom.setItemNumber(null);

        // Create the Bom, which fails.

        restBomMockMvc.perform(post("/api/boms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bom)))
            .andExpect(status().isBadRequest());

        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkComponentNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = bomRepository.findAll().size();
        // set the field null
        bom.setComponentNumber(null);

        // Create the Bom, which fails.

        restBomMockMvc.perform(post("/api/boms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bom)))
            .andExpect(status().isBadRequest());

        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = bomRepository.findAll().size();
        // set the field null
        bom.setQuantity(null);

        // Create the Bom, which fails.

        restBomMockMvc.perform(post("/api/boms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bom)))
            .andExpect(status().isBadRequest());

        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBomYieldIsRequired() throws Exception {
        int databaseSizeBeforeTest = bomRepository.findAll().size();
        // set the field null
        bom.setBomYield(null);

        // Create the Bom, which fails.

        restBomMockMvc.perform(post("/api/boms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bom)))
            .andExpect(status().isBadRequest());

        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBoms() throws Exception {
        // Initialize the database
        bomRepository.saveAndFlush(bom);

        // Get all the bomList
        restBomMockMvc.perform(get("/api/boms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bom.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemNumber").value(hasItem(DEFAULT_ITEM_NUMBER)))
            .andExpect(jsonPath("$.[*].bomOperationNumber").value(hasItem(DEFAULT_BOM_OPERATION_NUMBER)))
            .andExpect(jsonPath("$.[*].bomSequenceNumber").value(hasItem(DEFAULT_BOM_SEQUENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].componentNumber").value(hasItem(DEFAULT_COMPONENT_NUMBER)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].bomYield").value(hasItem(DEFAULT_BOM_YIELD.doubleValue())))
            .andExpect(jsonPath("$.[*].effDate").value(hasItem(DEFAULT_EFF_DATE.toString())))
            .andExpect(jsonPath("$.[*].disDate").value(hasItem(DEFAULT_DIS_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getBom() throws Exception {
        // Initialize the database
        bomRepository.saveAndFlush(bom);

        // Get the bom
        restBomMockMvc.perform(get("/api/boms/{id}", bom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bom.getId().intValue()))
            .andExpect(jsonPath("$.itemNumber").value(DEFAULT_ITEM_NUMBER))
            .andExpect(jsonPath("$.bomOperationNumber").value(DEFAULT_BOM_OPERATION_NUMBER))
            .andExpect(jsonPath("$.bomSequenceNumber").value(DEFAULT_BOM_SEQUENCE_NUMBER))
            .andExpect(jsonPath("$.componentNumber").value(DEFAULT_COMPONENT_NUMBER))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.bomYield").value(DEFAULT_BOM_YIELD.doubleValue()))
            .andExpect(jsonPath("$.effDate").value(DEFAULT_EFF_DATE.toString()))
            .andExpect(jsonPath("$.disDate").value(DEFAULT_DIS_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBom() throws Exception {
        // Get the bom
        restBomMockMvc.perform(get("/api/boms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBom() throws Exception {
        // Initialize the database
        bomRepository.saveAndFlush(bom);

        int databaseSizeBeforeUpdate = bomRepository.findAll().size();

        // Update the bom
        Bom updatedBom = bomRepository.findById(bom.getId()).get();
        // Disconnect from session so that the updates on updatedBom are not directly saved in db
        em.detach(updatedBom);
        updatedBom
            .itemNumber(UPDATED_ITEM_NUMBER)
            .bomOperationNumber(UPDATED_BOM_OPERATION_NUMBER)
            .bomSequenceNumber(UPDATED_BOM_SEQUENCE_NUMBER)
            .componentNumber(UPDATED_COMPONENT_NUMBER)
            .quantity(UPDATED_QUANTITY)
            .bomYield(UPDATED_BOM_YIELD)
            .effDate(UPDATED_EFF_DATE)
            .disDate(UPDATED_DIS_DATE);

        restBomMockMvc.perform(put("/api/boms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBom)))
            .andExpect(status().isOk());

        // Validate the Bom in the database
        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeUpdate);
        Bom testBom = bomList.get(bomList.size() - 1);
        assertThat(testBom.getItemNumber()).isEqualTo(UPDATED_ITEM_NUMBER);
        assertThat(testBom.getBomOperationNumber()).isEqualTo(UPDATED_BOM_OPERATION_NUMBER);
        assertThat(testBom.getBomSequenceNumber()).isEqualTo(UPDATED_BOM_SEQUENCE_NUMBER);
        assertThat(testBom.getComponentNumber()).isEqualTo(UPDATED_COMPONENT_NUMBER);
        assertThat(testBom.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testBom.getBomYield()).isEqualTo(UPDATED_BOM_YIELD);
        assertThat(testBom.getEffDate()).isEqualTo(UPDATED_EFF_DATE);
        assertThat(testBom.getDisDate()).isEqualTo(UPDATED_DIS_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBom() throws Exception {
        int databaseSizeBeforeUpdate = bomRepository.findAll().size();

        // Create the Bom

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBomMockMvc.perform(put("/api/boms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bom)))
            .andExpect(status().isBadRequest());

        // Validate the Bom in the database
        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBom() throws Exception {
        // Initialize the database
        bomRepository.saveAndFlush(bom);

        int databaseSizeBeforeDelete = bomRepository.findAll().size();

        // Delete the bom
        restBomMockMvc.perform(delete("/api/boms/{id}", bom.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bom> bomList = bomRepository.findAll();
        assertThat(bomList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
