package com.myproject.foosball.domain;

import java.util.UUID;

public class Player {

    public final UUID id;
    public final String name;

    public Player(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player team = (Player) o;

        return id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
