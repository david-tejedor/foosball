package com.myproject.foosball.application.match.impl;

import com.myproject.foosball.application.match.MatchScoresSpecification;
import com.myproject.foosball.application.match.MatchService;
import com.myproject.foosball.application.match.MatchUniquePlayersSpecification;
import com.myproject.foosball.application.team.TeamService;
import com.myproject.foosball.domain.Match;
import com.myproject.foosball.domain.Player;
import com.myproject.foosball.domain.Team;
import com.myproject.foosball.domain.TeamScore;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MatchServiceImpl implements MatchService {

    private TeamService teamService;

    List<Match> matchRepository = new ArrayList<>();

    @Autowired
    public MatchServiceImpl(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public Match createMatch(Pair<Player, Player> playersA, int scoreA,
                             Pair<Player, Player> playersB, int scoreB) {
        Team teamA = teamService.getOrCreate(playersA.getLeft(), playersA.getRight());
        Team teamB = teamService.getOrCreate(playersB.getLeft(), playersB.getRight());

        Match newMatch = new Match(TeamScore.of(teamA, scoreA), TeamScore.of(teamB, scoreB));
        validateUniquePlayers(newMatch);
        validateScores(newMatch);

        matchRepository.add(newMatch);

        return newMatch;
    }

    private void validateUniquePlayers(Match match) {
        if (!new MatchUniquePlayersSpecification().isSatisfiedBy(match)) {
            throw new IllegalArgumentException("Both teams cannot contain same player");
        }
    }

    private void validateScores(Match match) {
        if (!new MatchScoresSpecification().isSatisfiedBy(match)) {
            throw new IllegalArgumentException("Invalid match scores");
        }
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository;
    }
}
