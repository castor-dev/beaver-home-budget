package dev.beaver.beaverhomebudgetspring.configuration;

import dev.beaver.beaverhomebudgetspring.dao.TransactionDAO;
import dev.beaver.beaverhomebudgetspring.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
