# --- !Ups

ALTER TABLE user ADD COLUMN passId bigint(20) unsigned NOT NULL AFTER id;

CREATE TABLE userPassword (
  id         bigint(20)   unsigned NOT NULL  AUTO_INCREMENT,
  password   VARCHAR(255)          NOT NULL,
  repassword VARCHAR(255)          NOT NULL,
  updated_at TIMESTAMP             NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  created_at TIMESTAMP             NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

INSERT INTO user(passId,name,mail) values(1, 'Test', 'test@test');
INSERT INTO userPassword(password,repassword) values('pass1425', 'pass1425');

# --- !Downs
DROP TABLE userPassword
