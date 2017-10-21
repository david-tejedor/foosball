package com.myproject.foosball.domain.match;

import com.myproject.foosball.domain.player.Player;
import com.myproject.foosball.domain.team.Team;
import com.myproject.foosball.domain.team.TeamScore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchScoresSpecificationTest {

    private MatchScoresSpecification matchScoresSpecification = new MatchScoresSpecification();

    @Test
    public void isSatisfiedBy_2_0_true() throws Exception {
        assertTrue(matchScoresSpecification.isSatisfiedBy(matchWithScores(2, 0)));
    }

    @Test
    public void isSatisfiedBy_1_2_true() throws Exception {
        assertTrue(matchScoresSpecification.isSatisfiedBy(matchWithScores(1, 2)));
    }

    @Test
    public void isSatisfiedBy_1_1_true() throws Exception {
        assertTrue(matchScoresSpecification.isSatisfiedBy(matchWithScores(1, 1)));
    }

    @Test
    public void isSatisfiedBy_3_1_false() throws Exception {
        assertFalse(matchScoresSpecification.isSatisfiedBy(matchWithScores(3, 1)));
    }

    @Test
    public void isSatisfiedBy_0_0_false() throws Exception {
        assertFalse(matchScoresSpecification.isSatisfiedBy(matchWithScores(0, 0)));
    }

    @Test
    public void isSatisfiedBy_2_2_false() throws Exception {
        assertFalse(matchScoresSpecification.isSatisfiedBy(matchWithScores(2, 2)));
    }

    @Test
    public void isSatisfiedBy_negative_false() throws Exception {
        assertFalse(matchScoresSpecification.isSatisfiedBy(matchWithScores(2, -1)));
    }

    private Match matchWithScores(int scoreA, int scoreB) {
        return new Match(
                TeamScore.of(new Team(new Player("p1"), new Player("p2")), scoreA),
                TeamScore.of(new Team(new Player("p3"), new Player("p4")), scoreB));
    }

}