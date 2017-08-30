package com.game;

import com.game.services.GameManagerService;
import com.game.services.GameManagerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.game"})
public class AppConfig {
    @Bean(name = "gameManagerService")
    public GameManagerService getGameManagerService() {
        return new GameManagerServiceImpl();
    }
}
