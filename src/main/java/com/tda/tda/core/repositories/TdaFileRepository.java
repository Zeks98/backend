package com.tda.tda.core.repositories;

import com.tda.tda.infrastructure.sql.entities.TdaFileEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TdaFileRepository extends JpaRepository<TdaFileEntity, Long> {
    List<TdaFileEntity> findByFileId(Long id);
}
