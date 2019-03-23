CREATE TABLE IF NOT EXISTS hitter_projection(
  id                INTEGER NOT NULL PRIMARY KEY,
  at_bats           INTEGER,
  caught_stealing   INTEGER,
  doubles           INTEGER,
  games_played      INTEGER,
  hit_by_pitch      INTEGER,
  hits              INTEGER,
  homeruns          INTEGER,
  plate_appearances INTEGER,
  runs              INTEGER,
  runs_batted_in    INTEGER,
  source            VARCHAR(255),
  stolen_bases      INTEGER,
  strikeouts        INTEGER,
  triples           INTEGER,
  walks             INTEGER,
  hitter_id         INTEGER REFERENCES player
);