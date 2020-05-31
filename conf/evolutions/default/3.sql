--- !Ups

CREATE TABLE userAuthToken (
  user_id    bigint(20)   unsigned       NOT NULL,
  token      VARCHAR(255) CHARSET ascii  NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE KEY (token)
);

--- !Downs
DROP TABLE userPassword
