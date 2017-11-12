package com.myproject.foosball.domain.team;

import java.util.function.Predicate;

public class TeamUniquePlayersSpecification implements Predicate<Team> {

    @Override
    public boolean test(Team team) {
        return !team.playerA.equals(team.playerB);
    }
}
