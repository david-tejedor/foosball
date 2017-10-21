package com.myproject.foosball.domain;

import java.util.UUID;

public class Team {

    public final UUID id;
    public final Player playerA;
    public final Player playerB;

    public Team(Player playerA, Player playerB) {
        this.id = UUID.randomUUID();
        this.playerA = playerA;
        this.playerB = playerB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
