package com.myproject.foosball.domain.match;

import com.myproject.foosball.domain.team.TeamScore;

import java.util.UUID;

public class Match {

    public final UUID id;
    public final TeamScore teamScoreA;
    public final TeamScore teamScoreB;

    public Match(TeamScore teamScoreA, TeamScore teamScoreB) {
        this.id = UUID.randomUUID();

        this.teamScoreA = teamScoreA;
        this.teamScoreB = teamScoreB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match team = (Match) o;

        return id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
