package com.tda.tda.core.services;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.stream.DoubleStream;

public interface TdaService {

    public boolean saveFile(String file) throws IOException;

   // DoubleStream loadAll();

//    Resource load(String filename);
}
