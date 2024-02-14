package com.tda.tda.core.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface TdaRepository {

    public boolean saveFile(String file);
}
