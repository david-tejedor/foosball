package com.myproject.foosball.application.team.impl;

import com.myproject.foosball.application.team.TeamService;
import com.myproject.foosball.domain.player.Player;
import com.myproject.foosball.domain.team.Team;
import com.myproject.foosball.domain.team.TeamRepository;
import com.myproject.foosball.domain.team.TeamUniquePlayersSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamServiceImpl implements TeamService {

    TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team getOrCreate(Player playerA, Player playerB) {
        Optional<Team> existingTeam = teamRepository.find(playerA, playerB);

        if (!existingTeam.isPresent()) {
            Team newTeam = new Team(playerA, playerB);
            teamRepository.save(newTeam);

            return newTeam;
        }

        return existingTeam.get();
    }

}
