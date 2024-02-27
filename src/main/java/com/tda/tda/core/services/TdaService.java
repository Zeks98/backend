package com.tda.tda.core.services;

import com.tda.tda.core.models.Tda;
import com.tda.tda.infrastructure.sql.entities.TdaEntity;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface TdaService {

    public boolean saveFile(String file) throws IOException;

    public List<Tda> getContentById(UUID id);


    List<TdaEntity> getAllFiles();

    void saveAllFilesList(List<TdaEntity> fileList);

    // DoubleStream loadAll();

//    Resource load(String filename);
}
