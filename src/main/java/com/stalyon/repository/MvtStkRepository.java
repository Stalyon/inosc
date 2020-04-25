package com.stalyon.repository;

import com.stalyon.domain.MvtStk;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MvtStk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MvtStkRepository extends JpaRepository<MvtStk, Long> {
}
