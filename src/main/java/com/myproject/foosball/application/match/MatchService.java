package com.myproject.foosball.application.match;

import com.myproject.foosball.domain.Match;
import com.myproject.foosball.domain.Player;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

    Match createMatch(Pair<Player, Player> playersA, int scoreA,
                      Pair<Player, Player> playersB, int scoreB);

    List<Match> getMatches();
}
