package com.cobaltroad.fbauction.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HitterProjection {
    @Id
    @GeneratedValue
    private int id;

    private String source;
    private String league;  // helper column to make queries easier

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

    private double battingAverageRating;
    private double runsRating;
    private double rbiRating;
    private double homerunRating;
    private double stolenBaseRating;

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
