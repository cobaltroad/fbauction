package com.cobaltroad.fbauction.model;

import com.cobaltroad.fbauction.enumeration.Team;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "player_type")
public abstract class Player {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "roster_id")
    @Getter
    @Setter
    private Roster roster;

    @Enumerated(EnumType.STRING)
    @Getter
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

    public boolean isAvailable() {
        return roster == null;
    }
}
