package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.model.PitcherProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PitcherProjectionRepository extends JpaRepository<PitcherProjection, Long> {

    @Query(value = "SELECT AVG(CAST(earned_runs AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllERs(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(earned_runs AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllERs(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(innings_pitched AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllIPs(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(innings_pitched AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllIPs(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(wins AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllWins(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(wins AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllWins(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(strikeouts AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllKs(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(strikeouts AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllKs(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(walks AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllWalks(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(walks AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllWalks(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(hits AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllHits(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(hits AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllHits(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(saves AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllSaves(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(saves AS FLOAT)) FROM pitcher_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllSaves(@Param("league") String league);


}
