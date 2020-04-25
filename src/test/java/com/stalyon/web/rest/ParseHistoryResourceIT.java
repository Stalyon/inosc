package com.stalyon.web.rest;

import com.stalyon.InoscApp;
import com.stalyon.domain.ParseHistory;
import com.stalyon.repository.ParseHistoryRepository;

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
 * Integration tests for the {@link ParseHistoryResource} REST controller.
 */
@SpringBootTest(classes = InoscApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ParseHistoryResourceIT {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_FILE_SIZE = 1;
    private static final Integer UPDATED_FILE_SIZE = 2;

    private static final LocalDate DEFAULT_PARSED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PARSED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ParseHistoryRepository parseHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParseHistoryMockMvc;

    private ParseHistory parseHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParseHistory createEntity(EntityManager em) {
        ParseHistory parseHistory = new ParseHistory()
            .fileName(DEFAULT_FILE_NAME)
            .fileSize(DEFAULT_FILE_SIZE)
            .parsedDate(DEFAULT_PARSED_DATE);
        return parseHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParseHistory createUpdatedEntity(EntityManager em) {
        ParseHistory parseHistory = new ParseHistory()
            .fileName(UPDATED_FILE_NAME)
            .fileSize(UPDATED_FILE_SIZE)
            .parsedDate(UPDATED_PARSED_DATE);
        return parseHistory;
    }

    @BeforeEach
    public void initTest() {
        parseHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createParseHistory() throws Exception {
        int databaseSizeBeforeCreate = parseHistoryRepository.findAll().size();

        // Create the ParseHistory
        restParseHistoryMockMvc.perform(post("/api/parse-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parseHistory)))
            .andExpect(status().isCreated());

        // Validate the ParseHistory in the database
        List<ParseHistory> parseHistoryList = parseHistoryRepository.findAll();
        assertThat(parseHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        ParseHistory testParseHistory = parseHistoryList.get(parseHistoryList.size() - 1);
        assertThat(testParseHistory.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testParseHistory.getFileSize()).isEqualTo(DEFAULT_FILE_SIZE);
        assertThat(testParseHistory.getParsedDate()).isEqualTo(DEFAULT_PARSED_DATE);
    }

    @Test
    @Transactional
    public void createParseHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parseHistoryRepository.findAll().size();

        // Create the ParseHistory with an existing ID
        parseHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParseHistoryMockMvc.perform(post("/api/parse-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parseHistory)))
            .andExpect(status().isBadRequest());

        // Validate the ParseHistory in the database
        List<ParseHistory> parseHistoryList = parseHistoryRepository.findAll();
        assertThat(parseHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllParseHistories() throws Exception {
        // Initialize the database
        parseHistoryRepository.saveAndFlush(parseHistory);

        // Get all the parseHistoryList
        restParseHistoryMockMvc.perform(get("/api/parse-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parseHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE)))
            .andExpect(jsonPath("$.[*].parsedDate").value(hasItem(DEFAULT_PARSED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getParseHistory() throws Exception {
        // Initialize the database
        parseHistoryRepository.saveAndFlush(parseHistory);

        // Get the parseHistory
        restParseHistoryMockMvc.perform(get("/api/parse-histories/{id}", parseHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parseHistory.getId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.fileSize").value(DEFAULT_FILE_SIZE))
            .andExpect(jsonPath("$.parsedDate").value(DEFAULT_PARSED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingParseHistory() throws Exception {
        // Get the parseHistory
        restParseHistoryMockMvc.perform(get("/api/parse-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParseHistory() throws Exception {
        // Initialize the database
        parseHistoryRepository.saveAndFlush(parseHistory);

        int databaseSizeBeforeUpdate = parseHistoryRepository.findAll().size();

        // Update the parseHistory
        ParseHistory updatedParseHistory = parseHistoryRepository.findById(parseHistory.getId()).get();
        // Disconnect from session so that the updates on updatedParseHistory are not directly saved in db
        em.detach(updatedParseHistory);
        updatedParseHistory
            .fileName(UPDATED_FILE_NAME)
            .fileSize(UPDATED_FILE_SIZE)
            .parsedDate(UPDATED_PARSED_DATE);

        restParseHistoryMockMvc.perform(put("/api/parse-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParseHistory)))
            .andExpect(status().isOk());

        // Validate the ParseHistory in the database
        List<ParseHistory> parseHistoryList = parseHistoryRepository.findAll();
        assertThat(parseHistoryList).hasSize(databaseSizeBeforeUpdate);
        ParseHistory testParseHistory = parseHistoryList.get(parseHistoryList.size() - 1);
        assertThat(testParseHistory.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testParseHistory.getFileSize()).isEqualTo(UPDATED_FILE_SIZE);
        assertThat(testParseHistory.getParsedDate()).isEqualTo(UPDATED_PARSED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingParseHistory() throws Exception {
        int databaseSizeBeforeUpdate = parseHistoryRepository.findAll().size();

        // Create the ParseHistory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParseHistoryMockMvc.perform(put("/api/parse-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parseHistory)))
            .andExpect(status().isBadRequest());

        // Validate the ParseHistory in the database
        List<ParseHistory> parseHistoryList = parseHistoryRepository.findAll();
        assertThat(parseHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParseHistory() throws Exception {
        // Initialize the database
        parseHistoryRepository.saveAndFlush(parseHistory);

        int databaseSizeBeforeDelete = parseHistoryRepository.findAll().size();

        // Delete the parseHistory
        restParseHistoryMockMvc.perform(delete("/api/parse-histories/{id}", parseHistory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParseHistory> parseHistoryList = parseHistoryRepository.findAll();
        assertThat(parseHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
