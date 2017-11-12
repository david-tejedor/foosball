package com.myproject.foosball.domain.team;

import com.myproject.foosball.domain.player.Player;

import java.util.Optional;

public interface TeamRepository {

    Optional<Team> find(Player playerA, Player playerB);

    void save(Team newTeam);

}
