package com.cobaltroad.fbauction.model;

import com.cobaltroad.fbauction.enumeration.Position;
import com.cobaltroad.fbauction.enumeration.Team;
import lombok.*;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Optional;

@Entity
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "player_type")
@Getter
public abstract class Player {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "roster_id")
    @Setter
    private Roster roster;

    @Enumerated(EnumType.STRING)
    @Setter
    private Team team;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Player(HitterProjection projection) {
        String fullName = projection.getFullName();
        String[] names = fullName.split("\\s", 2);
        this.firstName = names[0];
        this.lastName = names[1];

        String teamName = projection.getTeamName();
        Team team = teamName.isEmpty() ? null : Team.valueOf(teamName.toUpperCase().replace(' ', '_'));
        this.team = team;
    }

    public Player(PitcherProjection projection) {
        String fullName = projection.getFullName();
        String[] names = fullName.split("\\s", 2);
        this.firstName = names[0];
        this.lastName = names[1];

        String teamName = projection.getTeamName();
        Team team = teamName.isEmpty() ? null : Team.valueOf(teamName.toUpperCase().replace(' ', '_'));
        this.team = team;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public static Comparator<Object> byTotalRating = Comparator.comparingDouble(player -> {
        if (player instanceof Hitter) {
            Hitter h = (Hitter) player;
            return h.getProjection().getTotalRating();
        } else if (player instanceof Pitcher) {
            Pitcher p = (Pitcher) player;
            return p.getProjection().getTotalRating();
        } else return 0.0;
    }).reversed();

    public abstract boolean isEligibleAt(String position);
}
