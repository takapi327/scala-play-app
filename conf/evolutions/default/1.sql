--- !Ups

CREATE TABLE user (
  id         bigint(20)   unsigned       NOT NULL  AUTO_INCREMENT,
  name       VARCHAR(255)                NOT NULL,
  mail       VARCHAR(255) CHARSET ascii  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (mail)
);

INSERT INTO user (id, name, mail) VALUES (1, "TEST", "test@111");

--- !Downs
DROP TABLE user
