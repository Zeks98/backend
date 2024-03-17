package com.tda.tda.core.services;

import com.tda.tda.core.models.Tda;
import com.tda.tda.core.models.TdaSingle;
import com.tda.tda.infrastructure.sql.entities.TdaEntity;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface TdaService {

    public List<Tda> saveFile(String file);

    public List<Tda> getContentById(Long id, String sortBy);

    List<TdaSingle> getAllFiles();

    void saveAllFilesList(List<TdaEntity> fileList);

    List<Tda> getFilteredFilesBySearchTerm(int fileId, String searchTerm, String sortBy);

    Tda updateRow(Tda row);
}
