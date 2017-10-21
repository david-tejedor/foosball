package com.myproject.foosball.interfaces.web;

import com.myproject.foosball.application.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

//    @PostMapping
//    public Match createMatch(int aScore, int bScore) {
//        return matchService.createMatch(aScore, bScore);
//    }
}
