package com.stalyon.web.rest;

import com.stalyon.domain.MvtStk;
import com.stalyon.repository.MvtStkRepository;
import com.stalyon.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.stalyon.domain.MvtStk}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MvtStkResource {

    private final Logger log = LoggerFactory.getLogger(MvtStkResource.class);

    private static final String ENTITY_NAME = "mvtStk";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MvtStkRepository mvtStkRepository;

    public MvtStkResource(MvtStkRepository mvtStkRepository) {
        this.mvtStkRepository = mvtStkRepository;
    }

    /**
     * {@code POST  /mvt-stks} : Create a new mvtStk.
     *
     * @param mvtStk the mvtStk to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mvtStk, or with status {@code 400 (Bad Request)} if the mvtStk has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mvt-stks")
    public ResponseEntity<MvtStk> createMvtStk(@Valid @RequestBody MvtStk mvtStk) throws URISyntaxException {
        log.debug("REST request to save MvtStk : {}", mvtStk);
        if (mvtStk.getId() != null) {
            throw new BadRequestAlertException("A new mvtStk cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MvtStk result = mvtStkRepository.save(mvtStk);
        return ResponseEntity.created(new URI("/api/mvt-stks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mvt-stks} : Updates an existing mvtStk.
     *
     * @param mvtStk the mvtStk to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mvtStk,
     * or with status {@code 400 (Bad Request)} if the mvtStk is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mvtStk couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mvt-stks")
    public ResponseEntity<MvtStk> updateMvtStk(@Valid @RequestBody MvtStk mvtStk) throws URISyntaxException {
        log.debug("REST request to update MvtStk : {}", mvtStk);
        if (mvtStk.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MvtStk result = mvtStkRepository.save(mvtStk);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mvtStk.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mvt-stks} : get all the mvtStks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mvtStks in body.
     */
    @GetMapping("/mvt-stks")
    public List<MvtStk> getAllMvtStks() {
        log.debug("REST request to get all MvtStks");
        return mvtStkRepository.findAll();
    }

    /**
     * {@code GET  /mvt-stks/:id} : get the "id" mvtStk.
     *
     * @param id the id of the mvtStk to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mvtStk, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mvt-stks/{id}")
    public ResponseEntity<MvtStk> getMvtStk(@PathVariable Long id) {
        log.debug("REST request to get MvtStk : {}", id);
        Optional<MvtStk> mvtStk = mvtStkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mvtStk);
    }

    /**
     * {@code DELETE  /mvt-stks/:id} : delete the "id" mvtStk.
     *
     * @param id the id of the mvtStk to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mvt-stks/{id}")
    public ResponseEntity<Void> deleteMvtStk(@PathVariable Long id) {
        log.debug("REST request to delete MvtStk : {}", id);
        mvtStkRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
