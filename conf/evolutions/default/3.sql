 --- !Ups

CREATE TABLE userAuthToken (
  user_id    bigint(20)   unsigned       NOT NULL,
  token      VARCHAR(255) CHARSET ascii  NOT NULL,
  -- updatedAt  TIMESTAMP             NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  -- createdAt  TIMESTAMP             NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY (user_id, token)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# --- !Downs
DROP TABLE userPassword
