# --- !Ups

CREATE TABLE userPassword (
  user_id    bigint(20)   unsigned       NOT NULL,
  password   VARCHAR(255) CHARSET ascii  NOT NULL,
  -- updatedAt  TIMESTAMP             NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  -- createdAt  TIMESTAMP             NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--INSERT INTO userPassword(password,repassword) values('pass1425', 'pass1425');

# --- !Downs
DROP TABLE userPassword
