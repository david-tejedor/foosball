package com.myproject.foosball.domain.match;

import com.myproject.foosball.domain.player.Player;
import com.myproject.foosball.domain.team.Team;
import com.myproject.foosball.domain.team.TeamScore;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatchUniquePlayersSpecificationTest {

    MatchUniquePlayersSpecification matchUniquePlayersSpecification = new MatchUniquePlayersSpecification();

    private Player p1 = new Player("p1");
    private Player p2 = new Player("p2");
    private Player p3 = new Player("p3");
    private Player p4 = new Player("p4");

    @Test
    public void isSatisfiedBy_uniquePlayers_true() throws Exception {
        Pair<Player, Player> playersA = Pair.of(p1, p2);
        Pair<Player, Player> playersB = Pair.of(p3, p4);

        assertTrue(matchUniquePlayersSpecification.test(matchWithPlayers(playersA, playersB)));
    }

    @Test
    public void isSatisfiedBy_playerInTwoTeams_false() throws Exception {
        Pair<Player, Player> playersA = Pair.of(p1, p4);
        Pair<Player, Player> playersB = Pair.of(p3, p4);

        assertFalse(matchUniquePlayersSpecification.test(matchWithPlayers(playersA, playersB)));
    }

    private Match matchWithPlayers(Pair<Player, Player> playersA,
                                   Pair<Player, Player> playersB) {
        return new Match(
                TeamScore.of(new Team(playersA.getLeft(), playersA.getRight()), 2),
                TeamScore.of(new Team(playersB.getLeft(), playersB.getRight()), 0));
    }

}