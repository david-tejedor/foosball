package com.myproject.foosball;

import com.myproject.foosball.application.team.TeamService;
import com.myproject.foosball.application.team.impl.TeamServiceImpl;
import com.myproject.foosball.domain.team.TeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TeamService teamService(TeamRepository teamRepository) {
        return new TeamServiceImpl(teamRepository);
    }

    @Bean
    public TeamRepository teamRepository() {
        //todo
        return null;
    }
}
