package com.tda.tda.core.repositories;

import com.tda.tda.infrastructure.sql.entities.TdaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TdaRepository extends JpaRepository<TdaEntity, Long> {
    // Additional custom query methods can be defined here if needed

/*
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveFile(String file) {

        var result = this.entityManager.createStoredProcedureQuery("sp_tda_add");
        result.registerStoredProcedureParameter("name", String.class, ParameterMode.IN);
        result.registerStoredProcedureParameter("file_name", String.class, ParameterMode.IN);
        result.registerStoredProcedureParameter("file_content", String.class, ParameterMode.IN);
        result.registerStoredProcedureParameter("success", Integer.class, ParameterMode.OUT);

        result.setParameter("name", "admin");
        result.setParameter("file_name", "ime_fajla");
        result.setParameter("file_content", file);

        result.execute();

        var response = result.getOutputParameterValue("success");

        return true;
    }

    @Override
    public List<String> getContentById(String filename) {


        return new ArrayList<String>();
    }*/
}
