package com.cobaltroad.fbauction.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
