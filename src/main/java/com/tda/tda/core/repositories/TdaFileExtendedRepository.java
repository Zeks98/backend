package com.tda.tda.core.repositories;

import com.tda.tda.infrastructure.sql.entities.TdaFileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TdaFileExtendedRepository{
    List<TdaFileEntity> getFilteredFilesBySearchTerm(String searchTerm);
}
