package com.myproject.foosball.application.match.impl;

import com.myproject.foosball.application.match.MatchService;
import com.myproject.foosball.application.team.TeamService;
import com.myproject.foosball.domain.Match;
import com.myproject.foosball.domain.Player;
import com.myproject.foosball.domain.Team;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchServiceImplTest {

    private TeamService teamServiceMock;
    private MatchService matchService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        teamServiceMock = mock(TeamService.class);
        matchService = new MatchServiceImpl(teamServiceMock);
    }

    private Player player1 = new Player("player1");
    private Player player2 = new Player("player2");
    private Player player3 = new Player("player3");
    private Player player4 = new Player("player4");
    private Player player5 = new Player("player5");
    private Player player6 = new Player("player6");

    @Test
    public void createMatch() throws Exception {
        Pair<Player, Player> playersA = Pair.of(player1, player2);
        Pair<Player, Player> playersB = Pair.of(player3, player4);
        mockTeams(playersA, playersB);

        Match createdMatch = matchService.createMatch(playersA, 1, playersB, 2);

        assertMatch(createdMatch, playersA, 1, playersB, 2);
        assertThat(matchService.getMatches(), contains(createdMatch));
    }

    @Test
    public void createMatch_keepTeams() {
        Pair<Player, Player> playersA = Pair.of(player1, player2);
        Pair<Player, Player> playersB = Pair.of(player3, player4);
        Pair<Player, Player> playersC = Pair.of(player5, player6);
        mockTeams(playersA, playersB, playersC);

        Match firstMatch = matchService.createMatch(playersA, 2, playersB, 1);
        Match secondMatch = matchService.createMatch(playersB, 1, playersC, 2);

        assertThat(firstMatch.teamScoreB.team, is(secondMatch.teamScoreA.team));
    }

    @Test
    public void createMatch_duplicatedPlayer_exception() {
        Pair<Player, Player> playersA = Pair.of(player1, player2);
        Pair<Player, Player> playersB = Pair.of(player3, player1);
        mockTeams(playersA, playersB);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Both teams cannot contain same player");

        matchService.createMatch(playersA, 2, playersB, 0);
    }

    @Test
    public void createMatch_scoreBiggerThan2_exception() {
        Pair<Player, Player> playersA = Pair.of(player1, player2);
        Pair<Player, Player> playersB = Pair.of(player3, player4);
        mockTeams(playersA, playersB);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid match scores");

        matchService.createMatch(playersA, 3, playersB, 0);
    }

    @Test
    public void createMatch_drawBiggerThan1_exception() {
        Pair<Player, Player> playersA = Pair.of(player1, player2);
        Pair<Player, Player> playersB = Pair.of(player3, player4);
        mockTeams(playersA, playersB);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid match scores");

        matchService.createMatch(playersA, 2, playersB, 2);
    }

    @Test
    public void createMatch_drawLowerThan1_exception() {
        Pair<Player, Player> playersA = Pair.of(player1, player2);
        Pair<Player, Player> playersB = Pair.of(player3, player4);
        mockTeams(playersA, playersB);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid match scores");

        matchService.createMatch(playersA, 0, playersB, 0);
    }

    @Test
    public void createMatch_scoreLowerThan0_exception() {
        Pair<Player, Player> playersA = Pair.of(player1, player2);
        Pair<Player, Player> playersB = Pair.of(player3, player4);
        mockTeams(playersA, playersB);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid match scores");

        matchService.createMatch(playersA, 2, playersB, -1);
    }

    private void assertMatch(Match createdMatch,
                             Pair<Player, Player> playersA, int scoreA,
                             Pair<Player, Player> playersB, int scoreB) {
        assertThat(createdMatch.teamScoreA.team.playerA, is(playersA.getLeft()));
        assertThat(createdMatch.teamScoreA.team.playerB, is(playersA.getRight()));
        assertThat(createdMatch.teamScoreA.score, is(scoreA));

        assertThat(createdMatch.teamScoreB.team.playerA, is(playersB.getLeft()));
        assertThat(createdMatch.teamScoreB.team.playerB, is(playersB.getRight()));
        assertThat(createdMatch.teamScoreB.score, is(scoreB));
    }

    private void mockTeams(Pair<Player, Player>... playersPairs) {
        for (Pair<Player, Player> playersPair : playersPairs) {
            Player player1 = playersPair.getLeft();
            Player player2 = playersPair.getRight();

            Team team = new Team(player1, player2);
            when(teamServiceMock.getOrCreate(player1, player2))
                    .thenReturn(team);
        }
    }

}