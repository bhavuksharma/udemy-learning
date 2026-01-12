package com.udemylearning.springcoredemo.config;

import com.udemylearning.springcoredemo.common.Coach;
import com.udemylearning.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
