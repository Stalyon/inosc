package com.stalyon.web.rest;

import com.stalyon.InoscApp;
import com.stalyon.domain.MvtStk;
import com.stalyon.repository.MvtStkRepository;

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
 * Integration tests for the {@link MvtStkResource} REST controller.
 */
@SpringBootTest(classes = InoscApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MvtStkResourceIT {

    private static final LocalDate DEFAULT_DATE_MVT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MVT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CODE_MVT = "AAA";
    private static final String UPDATED_CODE_MVT = "BBB";

    private static final String DEFAULT_ITEM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MAGASIN = "AAAAAAAAAA";
    private static final String UPDATED_MAGASIN = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLACEMENT = "AAAAAAAAAA";
    private static final String UPDATED_EMPLACEMENT = "BBBBBBBBBB";

    private static final Float DEFAULT_QTE = 1F;
    private static final Float UPDATED_QTE = 2F;

    private static final String DEFAULT_NUM_ORDRE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ORDRE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_LIGNE_ORDRE = "AAAA";
    private static final String UPDATED_NUM_LIGNE_ORDRE = "BBBB";

    private static final String DEFAULT_LOT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOT_NUMBER = "BBBBBBBBBB";

    @Autowired
    private MvtStkRepository mvtStkRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMvtStkMockMvc;

    private MvtStk mvtStk;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MvtStk createEntity(EntityManager em) {
        MvtStk mvtStk = new MvtStk()
            .dateMvt(DEFAULT_DATE_MVT)
            .codeMvt(DEFAULT_CODE_MVT)
            .itemNumber(DEFAULT_ITEM_NUMBER)
            .magasin(DEFAULT_MAGASIN)
            .emplacement(DEFAULT_EMPLACEMENT)
            .qte(DEFAULT_QTE)
            .numOrdre(DEFAULT_NUM_ORDRE)
            .numLigneOrdre(DEFAULT_NUM_LIGNE_ORDRE)
            .lotNumber(DEFAULT_LOT_NUMBER);
        return mvtStk;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MvtStk createUpdatedEntity(EntityManager em) {
        MvtStk mvtStk = new MvtStk()
            .dateMvt(UPDATED_DATE_MVT)
            .codeMvt(UPDATED_CODE_MVT)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .magasin(UPDATED_MAGASIN)
            .emplacement(UPDATED_EMPLACEMENT)
            .qte(UPDATED_QTE)
            .numOrdre(UPDATED_NUM_ORDRE)
            .numLigneOrdre(UPDATED_NUM_LIGNE_ORDRE)
            .lotNumber(UPDATED_LOT_NUMBER);
        return mvtStk;
    }

    @BeforeEach
    public void initTest() {
        mvtStk = createEntity(em);
    }

    @Test
    @Transactional
    public void createMvtStk() throws Exception {
        int databaseSizeBeforeCreate = mvtStkRepository.findAll().size();

        // Create the MvtStk
        restMvtStkMockMvc.perform(post("/api/mvt-stks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtStk)))
            .andExpect(status().isCreated());

        // Validate the MvtStk in the database
        List<MvtStk> mvtStkList = mvtStkRepository.findAll();
        assertThat(mvtStkList).hasSize(databaseSizeBeforeCreate + 1);
        MvtStk testMvtStk = mvtStkList.get(mvtStkList.size() - 1);
        assertThat(testMvtStk.getDateMvt()).isEqualTo(DEFAULT_DATE_MVT);
        assertThat(testMvtStk.getCodeMvt()).isEqualTo(DEFAULT_CODE_MVT);
        assertThat(testMvtStk.getItemNumber()).isEqualTo(DEFAULT_ITEM_NUMBER);
        assertThat(testMvtStk.getMagasin()).isEqualTo(DEFAULT_MAGASIN);
        assertThat(testMvtStk.getEmplacement()).isEqualTo(DEFAULT_EMPLACEMENT);
        assertThat(testMvtStk.getQte()).isEqualTo(DEFAULT_QTE);
        assertThat(testMvtStk.getNumOrdre()).isEqualTo(DEFAULT_NUM_ORDRE);
        assertThat(testMvtStk.getNumLigneOrdre()).isEqualTo(DEFAULT_NUM_LIGNE_ORDRE);
        assertThat(testMvtStk.getLotNumber()).isEqualTo(DEFAULT_LOT_NUMBER);
    }

    @Test
    @Transactional
    public void createMvtStkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mvtStkRepository.findAll().size();

        // Create the MvtStk with an existing ID
        mvtStk.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMvtStkMockMvc.perform(post("/api/mvt-stks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtStk)))
            .andExpect(status().isBadRequest());

        // Validate the MvtStk in the database
        List<MvtStk> mvtStkList = mvtStkRepository.findAll();
        assertThat(mvtStkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateMvtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtStkRepository.findAll().size();
        // set the field null
        mvtStk.setDateMvt(null);

        // Create the MvtStk, which fails.

        restMvtStkMockMvc.perform(post("/api/mvt-stks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtStk)))
            .andExpect(status().isBadRequest());

        List<MvtStk> mvtStkList = mvtStkRepository.findAll();
        assertThat(mvtStkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkItemNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtStkRepository.findAll().size();
        // set the field null
        mvtStk.setItemNumber(null);

        // Create the MvtStk, which fails.

        restMvtStkMockMvc.perform(post("/api/mvt-stks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtStk)))
            .andExpect(status().isBadRequest());

        List<MvtStk> mvtStkList = mvtStkRepository.findAll();
        assertThat(mvtStkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtStkRepository.findAll().size();
        // set the field null
        mvtStk.setQte(null);

        // Create the MvtStk, which fails.

        restMvtStkMockMvc.perform(post("/api/mvt-stks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtStk)))
            .andExpect(status().isBadRequest());

        List<MvtStk> mvtStkList = mvtStkRepository.findAll();
        assertThat(mvtStkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMvtStks() throws Exception {
        // Initialize the database
        mvtStkRepository.saveAndFlush(mvtStk);

        // Get all the mvtStkList
        restMvtStkMockMvc.perform(get("/api/mvt-stks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mvtStk.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateMvt").value(hasItem(DEFAULT_DATE_MVT.toString())))
            .andExpect(jsonPath("$.[*].codeMvt").value(hasItem(DEFAULT_CODE_MVT)))
            .andExpect(jsonPath("$.[*].itemNumber").value(hasItem(DEFAULT_ITEM_NUMBER)))
            .andExpect(jsonPath("$.[*].magasin").value(hasItem(DEFAULT_MAGASIN)))
            .andExpect(jsonPath("$.[*].emplacement").value(hasItem(DEFAULT_EMPLACEMENT)))
            .andExpect(jsonPath("$.[*].qte").value(hasItem(DEFAULT_QTE.doubleValue())))
            .andExpect(jsonPath("$.[*].numOrdre").value(hasItem(DEFAULT_NUM_ORDRE)))
            .andExpect(jsonPath("$.[*].numLigneOrdre").value(hasItem(DEFAULT_NUM_LIGNE_ORDRE)))
            .andExpect(jsonPath("$.[*].lotNumber").value(hasItem(DEFAULT_LOT_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getMvtStk() throws Exception {
        // Initialize the database
        mvtStkRepository.saveAndFlush(mvtStk);

        // Get the mvtStk
        restMvtStkMockMvc.perform(get("/api/mvt-stks/{id}", mvtStk.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mvtStk.getId().intValue()))
            .andExpect(jsonPath("$.dateMvt").value(DEFAULT_DATE_MVT.toString()))
            .andExpect(jsonPath("$.codeMvt").value(DEFAULT_CODE_MVT))
            .andExpect(jsonPath("$.itemNumber").value(DEFAULT_ITEM_NUMBER))
            .andExpect(jsonPath("$.magasin").value(DEFAULT_MAGASIN))
            .andExpect(jsonPath("$.emplacement").value(DEFAULT_EMPLACEMENT))
            .andExpect(jsonPath("$.qte").value(DEFAULT_QTE.doubleValue()))
            .andExpect(jsonPath("$.numOrdre").value(DEFAULT_NUM_ORDRE))
            .andExpect(jsonPath("$.numLigneOrdre").value(DEFAULT_NUM_LIGNE_ORDRE))
            .andExpect(jsonPath("$.lotNumber").value(DEFAULT_LOT_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingMvtStk() throws Exception {
        // Get the mvtStk
        restMvtStkMockMvc.perform(get("/api/mvt-stks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMvtStk() throws Exception {
        // Initialize the database
        mvtStkRepository.saveAndFlush(mvtStk);

        int databaseSizeBeforeUpdate = mvtStkRepository.findAll().size();

        // Update the mvtStk
        MvtStk updatedMvtStk = mvtStkRepository.findById(mvtStk.getId()).get();
        // Disconnect from session so that the updates on updatedMvtStk are not directly saved in db
        em.detach(updatedMvtStk);
        updatedMvtStk
            .dateMvt(UPDATED_DATE_MVT)
            .codeMvt(UPDATED_CODE_MVT)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .magasin(UPDATED_MAGASIN)
            .emplacement(UPDATED_EMPLACEMENT)
            .qte(UPDATED_QTE)
            .numOrdre(UPDATED_NUM_ORDRE)
            .numLigneOrdre(UPDATED_NUM_LIGNE_ORDRE)
            .lotNumber(UPDATED_LOT_NUMBER);

        restMvtStkMockMvc.perform(put("/api/mvt-stks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMvtStk)))
            .andExpect(status().isOk());

        // Validate the MvtStk in the database
        List<MvtStk> mvtStkList = mvtStkRepository.findAll();
        assertThat(mvtStkList).hasSize(databaseSizeBeforeUpdate);
        MvtStk testMvtStk = mvtStkList.get(mvtStkList.size() - 1);
        assertThat(testMvtStk.getDateMvt()).isEqualTo(UPDATED_DATE_MVT);
        assertThat(testMvtStk.getCodeMvt()).isEqualTo(UPDATED_CODE_MVT);
        assertThat(testMvtStk.getItemNumber()).isEqualTo(UPDATED_ITEM_NUMBER);
        assertThat(testMvtStk.getMagasin()).isEqualTo(UPDATED_MAGASIN);
        assertThat(testMvtStk.getEmplacement()).isEqualTo(UPDATED_EMPLACEMENT);
        assertThat(testMvtStk.getQte()).isEqualTo(UPDATED_QTE);
        assertThat(testMvtStk.getNumOrdre()).isEqualTo(UPDATED_NUM_ORDRE);
        assertThat(testMvtStk.getNumLigneOrdre()).isEqualTo(UPDATED_NUM_LIGNE_ORDRE);
        assertThat(testMvtStk.getLotNumber()).isEqualTo(UPDATED_LOT_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingMvtStk() throws Exception {
        int databaseSizeBeforeUpdate = mvtStkRepository.findAll().size();

        // Create the MvtStk

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMvtStkMockMvc.perform(put("/api/mvt-stks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtStk)))
            .andExpect(status().isBadRequest());

        // Validate the MvtStk in the database
        List<MvtStk> mvtStkList = mvtStkRepository.findAll();
        assertThat(mvtStkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMvtStk() throws Exception {
        // Initialize the database
        mvtStkRepository.saveAndFlush(mvtStk);

        int databaseSizeBeforeDelete = mvtStkRepository.findAll().size();

        // Delete the mvtStk
        restMvtStkMockMvc.perform(delete("/api/mvt-stks/{id}", mvtStk.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MvtStk> mvtStkList = mvtStkRepository.findAll();
        assertThat(mvtStkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
