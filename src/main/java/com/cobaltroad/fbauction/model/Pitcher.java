package com.cobaltroad.fbauction.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "pitcher")
public class Pitcher extends Player {
    public Pitcher(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
