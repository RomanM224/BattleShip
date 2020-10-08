package com.maistruk.battleship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.maistruk.battleship.controller.BattleshipController;
import com.maistruk.battleship.service.FieldChecker;
import com.maistruk.battleship.service.GamaManager;
import com.maistruk.battleship.service.ShipsGenerator;
import com.maistruk.battleship.service.TableCreater;
import com.maistruk.battleship.service.UserInputChecker;

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
    
    @Bean
    public FieldChecker fieldChecker() {
         return new FieldChecker();
    }
    
    @Bean
    public TableCreater tableCreater() {
         return new TableCreater();
    }
    
    @Bean UserInputChecker userInputChecker() {
        return new UserInputChecker();
    }
    
    @Bean
    public ShipsGenerator shipsGenerator() {
        return new ShipsGenerator();
    }

}
