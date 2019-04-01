package com.cobaltroad.fbauction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PitcherProjection {
    @Id
    private int id; // not used by database, but used in bean creation

    private String league;  // helper column to make queries easier

    private String fullName; // helper column to make CSV import easier
    private String teamName; // helper column to make CSV import easier

    private int wins;
    private int losses;
    private int gamesStarted;
    private int gamesPlayed;
    private double inningsPitched;
    private int hits;
    private int earnedRuns;
    private int homeruns;
    private int strikeouts;
    private int walks;
    private int saves;

    private double earnedRunAverageRating;
    private double winsRating;
    private double strikeoutsRating;
    private double whipRating;
    private double savesRating;

    private double totalRating;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Pitcher pitcher;
}
