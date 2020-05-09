# --- !Ups

CREATE TABLE "user" (
  "id"         bigint(20)   unsigned  NOT NULL  AUTO_INCREMENT,
  "name"       VARCHAR(255)           NOT NULL,
  "mail"       VARCHAR(255)           NOT NULL,
  "updated_at" TIMESTAMP              NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "created_at" TIMESTAMP              NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY ("id")
);

INSERT INTO "user" VALUES (DEFAULT, 'Test', 'test@test')

# --- !Downs
DROP TABLE "user"
