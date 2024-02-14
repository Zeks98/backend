package com.tda.tda.infrastructure.sql.repositories;

import com.tda.tda.core.repositories.TdaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TdaRepositoryImpl implements TdaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveFile(String file) {

        var result = this.entityManager.createStoredProcedureQuery("sp_tda_add");
        result.registerStoredProcedureParameter("name", String.class, ParameterMode.IN);
        result.registerStoredProcedureParameter("content", String.class, ParameterMode.IN);
        result.registerStoredProcedureParameter("success", Integer.class, ParameterMode.OUT);

        result.setParameter("name", "admin");
        result.setParameter("content", file);

        result.execute();

        var response = result.getOutputParameterValue("success");

        return true;
    }
}
