package com.tda.tda.core.configuration;

import com.tda.tda.TdaApplication;
import com.tda.tda.core.models.Tda;
import com.tda.tda.webApi.controllers.TdaController;
import com.tda.tda.webApi.dto.TdaResponseDTO;
import org.apache.poi.ss.formula.functions.Address;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean(name = "mapper")
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.typeMap(Address.class, TdaResponseDTO.class);
        mapper.typeMap(Address.class, Tda.class);
        return mapper;
    }
}
