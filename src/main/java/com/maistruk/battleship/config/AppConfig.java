package com.maistruk.battleship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.maistruk.battleship.controller.BattleshipController;
import com.maistruk.battleship.service.GamaManager;

@Configuration
@ComponentScan({ "com.maistruk.battleship.config" })
public class AppConfig {
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public BattleshipController battleshipController() {
        return new BattleshipController();
    }
    
    @Bean
    public GamaManager gamaManager() {
         return new GamaManager();
    }

}
