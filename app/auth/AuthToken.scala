package auth

import java.security.SecureRandom
import scala.util.Random

case class TokenGenerator(
  protected val  table: String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890",
  protected val random: Random = new Random(new SecureRandom())
) {

  /**
   * Generate a new token as string
   */
  final def next(length: Int): String =
    Iterator.continually(
      random.nextInt(table.size)).map(table).take(length).mkString
}
