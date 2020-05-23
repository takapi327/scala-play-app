# --- !Ups

CREATE TABLE user (
  id         bigint(20)   unsigned       NOT NULL  AUTO_INCREMENT,
  name       VARCHAR(255)                NOT NULL,
  mail       VARCHAR(255) CHARSET ascii  NOT NULL,
  -- updatedAt  TIMESTAMP              NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  -- createdAt  TIMESTAMP              NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (mail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# --- !Downs
DROP TABLE user
