package com.stalyon.repository;

import com.stalyon.domain.Bom;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BomRepository extends JpaRepository<Bom, Long> {
}
