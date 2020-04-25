package com.stalyon.repository;

import com.stalyon.domain.ParseHistory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ParseHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParseHistoryRepository extends JpaRepository<ParseHistory, Long> {
}
