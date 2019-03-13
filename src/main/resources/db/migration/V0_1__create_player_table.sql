CREATE TABLE IF NOT EXISTS player (
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  id INT NOT NULL PRIMARY KEY
)