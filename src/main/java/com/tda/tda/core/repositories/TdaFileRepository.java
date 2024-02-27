package com.tda.tda.core.repositories;

import com.tda.tda.infrastructure.sql.entities.TdaEntity;
import com.tda.tda.infrastructure.sql.entities.TdaFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TdaFileRepository extends JpaRepository<TdaFileEntity, UUID> {

}
