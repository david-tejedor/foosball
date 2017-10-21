package com.myproject.foosball.application.match;

import com.myproject.foosball.domain.Match;

public abstract class MatchSpecification {

    public abstract boolean isSatisfiedBy(Match match);
}
