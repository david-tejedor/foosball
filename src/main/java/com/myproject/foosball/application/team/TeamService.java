package com.myproject.foosball.application.team;

import com.myproject.foosball.domain.player.Player;
import com.myproject.foosball.domain.team.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    Team getOrCreate(Player playerA, Player playerB);
}
