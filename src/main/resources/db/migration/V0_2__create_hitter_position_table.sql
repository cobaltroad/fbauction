CREATE TABLE IF NOT EXISTS hitter_position (
  hitter_id INT REFERENCES player,
  position TEXT
);