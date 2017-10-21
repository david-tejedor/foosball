package com.myproject.foosball.application.team;

import com.myproject.foosball.domain.Player;
import com.myproject.foosball.domain.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    Team getOrCreate(Player playerA, Player playerB);
}
