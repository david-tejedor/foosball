package com.myproject.foosball.application.match;

import com.myproject.foosball.domain.Match;
import com.myproject.foosball.domain.TeamScore;

public class MatchScoresSpecification extends MatchSpecification {

    @Override
    public boolean isSatisfiedBy(Match match) {
        if (isWinner(match.teamScoreA)) {
            return isLooser(match.teamScoreB);

        } else if (isWinner(match.teamScoreB)) {
            return isLooser(match.teamScoreA);

        } else {
            return isDraw(match);
        }
    }

    private boolean isWinner(TeamScore teamScore) {
        return teamScore.score == 2;
    }

    private boolean isLooser(TeamScore teamScore) {
        return teamScore.score >= 0 && teamScore.score < 2;
    }

    private boolean isDraw(Match match) {
        return match.teamScoreA.score == 1 && match.teamScoreB.score == 1;
    }

}
