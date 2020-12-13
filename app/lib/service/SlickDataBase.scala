package lib.service

import slick.jdbc.JdbcProfile
import slick.basic.DatabaseConfig

trait SlickDatabaseConfig {
  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("slick.dbs.default")

  val db: JdbcProfile#Backend#Database = dbConfig.db

  val profile: JdbcProfile = dbConfig.profile
}

object SlickDatabaseConfig extends SlickDatabaseConfig
