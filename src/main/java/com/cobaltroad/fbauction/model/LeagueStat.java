package com.cobaltroad.fbauction.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LeagueStat {
    @Id
    @GeneratedValue
    private int id;

    private String key;

    @Getter
    @Setter
    private double value;

    public LeagueStat(String key, double value) {
        this.key = key;
        this.value = value;
    }
}

// hits / atbats
// atbats - hits = atbats without a hit
// hits - atbats = 1 - 2 = -1