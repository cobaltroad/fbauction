package com.cobaltroad.fbauction.model;

import lombok.AllArgsConstructor;

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

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
