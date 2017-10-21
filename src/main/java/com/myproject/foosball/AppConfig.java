package com.myproject.foosball;

import com.myproject.foosball.application.team.TeamService;
import com.myproject.foosball.application.team.impl.TeamServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TeamService teamService() {
        return new TeamServiceImpl();
    }

}
