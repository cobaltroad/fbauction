package com.cobaltroad.fbauction.model;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Roster {
    @Id
    @GeneratedValue
    private int id;

    private String owner;
    private String name;
    private String league;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "roster_id")
    List<Player> players;

    public void addPlayer(Player player) {
        if (null == this.players)
            this.players = Arrays.asList(player);
        else
            this.players.add(player);
    }
}
