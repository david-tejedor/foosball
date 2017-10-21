package com.myproject.foosball.domain;

public class TeamScore {

    public final Team team;
    public final int score;

    private TeamScore(Team team, int score) {
        this.team = team;
        this.score = score;
    }

    public static TeamScore of(Team team, int score) {
        return new TeamScore(team, score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamScore teamScore = (TeamScore) o;

        if (score != teamScore.score) return false;
        return team != null ? team.equals(teamScore.team) : teamScore.team == null;
    }

    @Override
    public int hashCode() {
        int result = team != null ? team.hashCode() : 0;
        result = 31 * result + score;
        return result;
    }

}
