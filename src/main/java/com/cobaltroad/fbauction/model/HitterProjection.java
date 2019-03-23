package com.cobaltroad.fbauction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HitterProjection {
    @Id
    @GeneratedValue
    private int id;

    private String source;
    private int gamesPlayed;
    private int plateAppearances;
    private int atBats;
    private int hits;
    private int doubles;
    private int triples;
    private int homeruns;
    private int runs;
    private int runsBattedIn;
    private int walks;
    private int strikeouts;
    private int hitByPitch;
    private int stolenBases;
    private int caughtStealing;

    public double battingAverage() {
        return hits * 1.0 / atBats;
    }

    public double onBaseAverage() {
        return (hits + walks + hitByPitch) * 1.0 / plateAppearances;
    }

    public double sluggingPercentage() {
        int singles = hits - doubles - triples - homeruns;
        return (singles + (2.0 * doubles) + (3.0 * triples) + (4.0 * homeruns)) / atBats;
    }

    public double onBasePlusSlugging() {
        return onBaseAverage() + sluggingPercentage();
    }

    public double weightedOnBaseAverage() {
        int singles = hits - doubles - triples - homeruns;
        int denominator = atBats + walks + hitByPitch; // missing IBB + SF data
        return ((0.716 * walks) + (0.744 * hitByPitch) + (0.894 * singles) + (1.240 * doubles) + (1.550 * triples) + (1.938 * homeruns)) / denominator;
    }
}
