package com.tda.tda.core.repositories;

import com.tda.tda.infrastructure.sql.entities.TdaEntity;
import com.tda.tda.infrastructure.sql.entities.TdaFileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TdaFileRepository extends JpaRepository<TdaFileEntity, Long> {
    List<TdaFileEntity> findByFileId(Long id);
}
