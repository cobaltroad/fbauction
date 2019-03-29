package com.cobaltroad.fbauction.model;

import com.cobaltroad.fbauction.enumeration.Position;
import com.cobaltroad.fbauction.enumeration.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.List;

import static com.cobaltroad.fbauction.enumeration.Team.alTeams;
import static com.cobaltroad.fbauction.enumeration.Team.nlTeams;

@Entity
@DiscriminatorValue(value = "hitter")
@Getter
@Setter
public class Hitter extends Player {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Position.class, fetch = FetchType.EAGER)
    @Column(name = "position")
    @CollectionTable(name = "hitter_position", joinColumns = @JoinColumn(name = "hitter_id"))
    private List<Position> positions;

    @OneToOne(mappedBy = "hitter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HitterProjection projection;

    public Hitter() {
        super();
    }
    public Hitter(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Hitter(HitterProjection projection) {
        super(projection);
        projection.setHitter(this);
        this.projection = projection;
    }

    public boolean isA(Position position) {
        switch (position) {
            case CORNER_INFIELDER:
                return positions.stream().anyMatch(p -> p == Position.FIRST_BASEMAN || p == Position.THIRD_BASEMAN);
            case MIDDLE_INFIELDER:
                return positions.stream().anyMatch(p -> p == Position.SECOND_BASEMAN || p == Position.SHORTSTOP);
            default:
                return positions.stream().anyMatch(p -> p == position);
        }
    }
}
