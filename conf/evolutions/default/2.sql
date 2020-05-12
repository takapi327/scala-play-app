# --- !Ups

CREATE TABLE userPassword (
  id         bigint(20)   unsigned NOT NULL,
  password   VARCHAR(255)          NOT NULL,
  -- updatedAt  TIMESTAMP             NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  -- createdAt  TIMESTAMP             NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

--INSERT INTO userPassword(password,repassword) values('pass1425', 'pass1425');

# --- !Downs
DROP TABLE userPassword
