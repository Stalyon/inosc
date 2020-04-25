package com.stalyon.web.rest;

import com.stalyon.domain.Bom;
import com.stalyon.repository.BomRepository;
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
 * REST controller for managing {@link com.stalyon.domain.Bom}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BomResource {

    private final Logger log = LoggerFactory.getLogger(BomResource.class);

    private static final String ENTITY_NAME = "bom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BomRepository bomRepository;

    public BomResource(BomRepository bomRepository) {
        this.bomRepository = bomRepository;
    }

    /**
     * {@code POST  /boms} : Create a new bom.
     *
     * @param bom the bom to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bom, or with status {@code 400 (Bad Request)} if the bom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/boms")
    public ResponseEntity<Bom> createBom(@Valid @RequestBody Bom bom) throws URISyntaxException {
        log.debug("REST request to save Bom : {}", bom);
        if (bom.getId() != null) {
            throw new BadRequestAlertException("A new bom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bom result = bomRepository.save(bom);
        return ResponseEntity.created(new URI("/api/boms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /boms} : Updates an existing bom.
     *
     * @param bom the bom to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bom,
     * or with status {@code 400 (Bad Request)} if the bom is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bom couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/boms")
    public ResponseEntity<Bom> updateBom(@Valid @RequestBody Bom bom) throws URISyntaxException {
        log.debug("REST request to update Bom : {}", bom);
        if (bom.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bom result = bomRepository.save(bom);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bom.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /boms} : get all the boms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of boms in body.
     */
    @GetMapping("/boms")
    public List<Bom> getAllBoms() {
        log.debug("REST request to get all Boms");
        return bomRepository.findAll();
    }

    /**
     * {@code GET  /boms/:id} : get the "id" bom.
     *
     * @param id the id of the bom to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bom, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/boms/{id}")
    public ResponseEntity<Bom> getBom(@PathVariable Long id) {
        log.debug("REST request to get Bom : {}", id);
        Optional<Bom> bom = bomRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bom);
    }

    /**
     * {@code DELETE  /boms/:id} : delete the "id" bom.
     *
     * @param id the id of the bom to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/boms/{id}")
    public ResponseEntity<Void> deleteBom(@PathVariable Long id) {
        log.debug("REST request to delete Bom : {}", id);
        bomRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
