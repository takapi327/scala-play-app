--- !Ups

CREATE TABLE userPassword (
  user_id    bigint(20)   unsigned       NOT NULL,
  password   VARCHAR(255) CHARSET ascii  NOT NULL,
  PRIMARY KEY (user_id)
);

INSERT INTO userPassword (user_id, password) VALUES (1, "hogehogehuahuga");

--- !Downs
DROP TABLE userPassword
