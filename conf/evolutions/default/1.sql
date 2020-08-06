--- !Ups

CREATE TABLE user (
  id         bigint(20)   unsigned       NOT NULL  AUTO_INCREMENT,
  first_name VARCHAR(255)                NOT NULL,
  last_name  VARCHAR(255)                NOT NULL,
  email      VARCHAR(255) CHARSET ascii  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
);

INSERT INTO user (id, first_name, last_name, email) VALUES (1, "TEST", "test", "test@111");

--- !Downs
DROP TABLE user
