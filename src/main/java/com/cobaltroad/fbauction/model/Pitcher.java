package com.cobaltroad.fbauction.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue(value = "pitcher")
public class Pitcher extends Player {

    @OneToOne(mappedBy = "pitcher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PitcherProjection projection;

    public Pitcher() { super(); } // fixes bytecode enhancement failure
    public Pitcher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Pitcher(PitcherProjection projection) {
        super(projection);
        projection.setPitcher(this);
        this.projection = projection;
    }
}
