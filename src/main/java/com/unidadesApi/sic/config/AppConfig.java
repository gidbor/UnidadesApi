package com.unidadesApi.sic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.modelmapper.ModelMapper;

@Configuration
public class AppConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();  // Bean para el mapeo entre DTOs y entidades
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


}