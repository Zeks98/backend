package com.tda.tda.core.configuration;

import com.tda.tda.webApi.dto.TdaResponseDTO;
import org.apache.poi.ss.formula.functions.Address;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;

public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategy.LOOSE);
        mapper.typeMap(Address.class, Tda);
        mapper.typeMap(Address.class,);
        return mapper;
    }
}
