--- !Ups

AlTER TABLE user (
  CHANGE COLUMN name first_name VARCHAR(255) NOT NULL,
  CHANGE COLUMN mail email VARCHAR(255) CHARSET ascii NOT NULL,
  ADD COLUMN last_name  VARCHAR(255) NOT NULL AFTER first_name,
);

--- !Downs
DROP TABLE user
