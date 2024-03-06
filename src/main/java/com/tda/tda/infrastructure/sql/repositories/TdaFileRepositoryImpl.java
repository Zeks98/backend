package com.tda.tda.infrastructure.sql.repositories;

import com.tda.tda.core.repositories.TdaFileRepository;
import com.tda.tda.infrastructure.sql.entities.TdaFileEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class TdaFileRepositoryImpl implements com.tda.tda.core.repositories.TdaFileExtendedRepository {

    @Autowired
    private EntityManager entityManager;

    private final TdaFileRepository tdaFileRepository;

    @Autowired
    public TdaFileRepositoryImpl(TdaFileRepository tdaFileRepository){
        this.tdaFileRepository = tdaFileRepository;
    }

    @Override
    public List<TdaFileEntity> getFilteredFilesBySearchTerm(String searchTerm) {
        var result = this.entityManager.createStoredProcedureQuery("filter_content_by_search_term", TdaFileEntity.class)
                .setParameter("search_term", searchTerm)
                .getResultList();

        return null;
    }
}
