package com.myproject.foosball.domain.match;

import com.myproject.foosball.domain.player.Player;

import java.util.stream.Stream;

public class MatchUniquePlayersSpecification extends MatchSpecification {

    @Override
    public boolean isSatisfiedBy(Match match) {
        Player playerA1 = match.teamScoreA.team.playerA;
        Player playerA2 = match.teamScoreA.team.playerB;

        Player playerB1 = match.teamScoreB.team.playerA;
        Player playerB2 = match.teamScoreB.team.playerB;

        return Stream.of(playerA1, playerA2)
                .noneMatch(pA -> pA.equals(playerB1) || pA.equals(playerB2));
    }

}
