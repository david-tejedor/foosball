package com.myproject.foosball.application.team.impl;

import com.myproject.foosball.application.team.TeamService;
import com.myproject.foosball.domain.Player;
import com.myproject.foosball.domain.Team;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TeamServiceImplTest {

    private Player player1 = new Player("player1");
    private Player player2 = new Player("player2");

    TeamService teamService = new TeamServiceImpl();

    @Test
    public void getOrCreate() {
        Team teamA = teamService.getOrCreate(player1, player2);
        Team teamB = teamService.getOrCreate(player2, player1);

        assertThat(Arrays.asList(teamA.playerA, teamA.playerB), containsInAnyOrder(player1, player2));
        assertEquals(teamA, teamB);
    }

}