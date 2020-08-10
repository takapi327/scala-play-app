--- !Ups

CREATE TABLE userAuthToken (
  id      VARCHAR(255) CHARSET ascii  NOT NULL,
  user_id bigint(20)   unsigned       NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE KEY (id)
);

--- !Downs
DROP TABLE userPassword
