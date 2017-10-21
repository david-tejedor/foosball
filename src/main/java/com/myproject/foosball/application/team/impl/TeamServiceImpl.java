package com.myproject.foosball.application.team.impl;

import com.myproject.foosball.application.team.TeamService;
import com.myproject.foosball.domain.Player;
import com.myproject.foosball.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamServiceImpl implements TeamService {

    List<Team> teams = new ArrayList<>();

    @Override
    public Team getOrCreate(Player playerA, Player playerB) {
        Optional<Team> existingTeam = teams.stream().filter(t -> t.playerA.equals(playerA) && t.playerB.equals(playerB)
                || t.playerA.equals(playerB) && t.playerB.equals(playerA)).findAny();

        if (!existingTeam.isPresent()) {
            Team newTeam = new Team(playerA, playerB);
            teams.add(newTeam);

            return newTeam;
        }

        return existingTeam.get();
    }
}
