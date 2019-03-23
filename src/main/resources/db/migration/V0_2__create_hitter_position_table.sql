CREATE TABLE IF NOT EXISTS hitter_position (
  hitter_id INT REFERENCES player,
  positions TEXT,
  positions_order INT
);