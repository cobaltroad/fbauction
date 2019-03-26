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

    @Query(value = "SELECT SUM(hits) FROM HitterProjection WHERE league = :league")
    Integer sumOfAllHits(@Param("league") String league);

    @Query(value = "SELECT SUM(atBats) FROM HitterProjection WHERE league = :league")
    Integer sumOfAllAtBats(@Param("league") String league);

    default double battingAverage(String league) {
        int hits = null == sumOfAllHits(league) ? 0 : sumOfAllHits(league);
        int atBats = null == sumOfAllAtBats(league) ? 0 : sumOfAllAtBats(league);

        return atBats == 0 ? 0.0 : (1.0 * hits / atBats);
    }
}
