CREATE TABLE roster
(
  id     INTEGER      NOT NULL PRIMARY KEY,
  owner  VARCHAR(255) NOT NULL,
  league VARCHAR(255) NOT NULL,
  name   VARCHAR(255)
);