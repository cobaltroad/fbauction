package com.cobaltroad.fbauction.enumeration;

import java.util.EnumSet;

public enum Team {
    ANGELS,
    ASTROS,
    ATHLETICS,
    BLUE_JAYS,
    BRAVES,
    BREWERS,
    CARDINALS,
    CUBS,
    DIAMONDBACKS,
    DODGERS,
    GIANTS,
    INDIANS,
    MARINERS,
    MARLINS,
    METS,
    NATIONALS,
    ORIOLES,
    PADRES,
    PHILLIES,
    PIRATES,
    RANGERS,
    RAYS,
    RED_SOX,
    REDS,
    ROCKIES,
    ROYALS,
    TIGERS,
    TWINS,
    WHITE_SOX,
    YANKEES;


    public static EnumSet<Team> alTeams = EnumSet.of(
            ANGELS,
            ASTROS,
            ATHLETICS,
            BLUE_JAYS,
            INDIANS,
            MARINERS,
            ORIOLES,
            RANGERS,
            RAYS,
            RED_SOX,
            ROYALS,
            TIGERS,
            TWINS,
            WHITE_SOX,
            YANKEES
    );

    public static EnumSet<Team> nlTeams = EnumSet.of(
            BRAVES,
            BREWERS,
            CARDINALS,
            CUBS,
            DIAMONDBACKS,
            DODGERS,
            GIANTS,
            MARLINS,
            METS,
            NATIONALS,
            PADRES,
            PHILLIES,
            PIRATES,
            REDS,
            ROCKIES
    );
}
