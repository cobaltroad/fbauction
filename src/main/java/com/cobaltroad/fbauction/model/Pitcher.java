package com.cobaltroad.fbauction.model;

import com.cobaltroad.fbauction.enumeration.Position;
import lombok.Getter;

import javax.persistence.*;
import java.util.Comparator;

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

    // not yet used
    public static Comparator<Object> byPitcherRating = Comparator.comparingDouble(pitcher -> ((Pitcher) pitcher).getProjection().getTotalRating()).reversed();

    @Override
    public boolean isEligibleAt(String position) {
        return position.equalsIgnoreCase("pitcher");
    }

}
