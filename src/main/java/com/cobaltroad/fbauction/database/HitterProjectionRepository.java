package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.model.HitterProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HitterProjectionRepository extends JpaRepository<HitterProjection, Long> {

    @Query(value = "SELECT AVG(CAST(hits AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllHits(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(hits AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllHits(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(at_bats AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllABs(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(at_bats AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllABs(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(runs AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllRuns(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(runs AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllRuns(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(runs_batted_in AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllRBIs(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(runs_batted_in AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllRBIs(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(homeruns AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllHRs(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(homeruns AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllHRs(@Param("league") String league);

    @Query(value = "SELECT AVG(CAST(stolen_bases AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double averageOfAllSBs(@Param("league") String league);

    @Query(value = "SELECT STDDEV(CAST(stolen_bases AS FLOAT)) FROM hitter_projection WHERE league = :league", nativeQuery = true)
    Double stddevOfAllSBs(@Param("league") String league);

}
