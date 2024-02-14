package com.tda.tda.infrastructure.services;

import com.tda.tda.core.repositories.TdaRepository;
import com.tda.tda.core.services.TdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdaServiceImpl implements TdaService {

    @Autowired
    private TdaRepository tdaRepository;

    @Override
    public boolean saveFile(String file) {
        var result = this.tdaRepository.saveFile(file);

        return result;
    }
}
