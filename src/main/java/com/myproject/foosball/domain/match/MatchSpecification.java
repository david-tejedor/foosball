package com.myproject.foosball.domain.match;

import com.myproject.foosball.domain.match.Match;

public abstract class MatchSpecification {

    public abstract boolean isSatisfiedBy(Match match);
}
