package com.cobaltroad.fbauction.model;

import com.cobaltroad.fbauction.enumeration.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "player")
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "player_type")
public abstract class Player {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Team team;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
