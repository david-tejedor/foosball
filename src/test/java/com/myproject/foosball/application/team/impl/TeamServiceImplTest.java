package com.myproject.foosball.application.team.impl;

import com.myproject.foosball.application.team.TeamService;
import com.myproject.foosball.domain.player.Player;
import com.myproject.foosball.domain.team.Team;
import com.myproject.foosball.domain.team.TeamRepositoryInMemory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TeamServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    TeamService teamService;

    private Player player1 = new Player("player1");
    private Player player2 = new Player("player2");

    @Before
    public void createTeamService() {
        teamService = new TeamServiceImpl(new TeamRepositoryInMemory());
    }

    @Test
    public void getOrCreate_samePlayers_sameTeam() {
        Team teamA = teamService.getOrCreate(player1, player2);
        Team teamB = teamService.getOrCreate(player2, player1);

        assertThat(Arrays.asList(teamA.playerA, teamA.playerB), containsInAnyOrder(player1, player2));
        assertEquals(teamA, teamB);
    }

    @Test
    public void getOrCreate_duplicatedPlayer_exception() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("A team cannot contain duplicated players");

        teamService.getOrCreate(player1, player1);
    }

}