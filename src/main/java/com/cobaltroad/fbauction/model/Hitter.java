package com.cobaltroad.fbauction.model;

import com.cobaltroad.fbauction.enumeration.Position;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Stream;

@Entity
@DiscriminatorValue(value = "hitter")
@Getter
@Setter
public class Hitter extends Player {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Position.class)
    @CollectionTable(name = "hitter_position")
    @Column(name = "position")
    @OrderColumn(name = "position_order")
    private Position[] positions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hitter_id")
    private List<HitterProjection> projections;

    public Hitter(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public boolean isA(Position position) {
        switch (position) {
            case CORNER_INFIELDER:
                return Stream.of(positions).anyMatch(p -> p == Position.FIRST_BASEMAN || p == Position.THIRD_BASEMAN);
            case MIDDLE_INFIELDER:
                return Stream.of(positions).anyMatch(p -> p == Position.SECOND_BASEMAN || p == Position.SHORTSTOP);
            default:
                return Stream.of(positions).anyMatch(p -> p == position);
        }
    }
}
