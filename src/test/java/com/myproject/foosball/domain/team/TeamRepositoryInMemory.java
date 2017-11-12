package com.myproject.foosball.domain.team;


import com.myproject.foosball.domain.player.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TeamRepositoryInMemory implements TeamRepository {

    Set<Team> teams = new HashSet<>();

    @Override
    public Optional<Team> find(Player playerA, Player playerB) {
        return teams.stream().filter(t -> t.playerA.equals(playerA) && t.playerB.equals(playerB)
                || t.playerA.equals(playerB) && t.playerB.equals(playerA)).findAny();
    }

    @Override
    public void save(Team newTeam) {
        teams.add(newTeam);
    }
}