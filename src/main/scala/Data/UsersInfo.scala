package Data

import scala.collection.mutable

object UsersInfo {

  // Will contain the name of the currently active user; default value is null.
  private var _activeUser: String = _
  private val defaultBalance = 30

  // TODO: step 2 - create an attribute that will contain each user and its current balance.
  private var accounts: mutable.HashMap[String, Int] = mutable.HashMap.empty[String, Int]

  /**
    * Update an account by decreasing its balance.
    * @param user the user whose account will be updated
    * @param amount the amount to decrease
    * @return the new balance
    */
  // TODO: step 2
  def purchase(user: String, amount: Double)= {
    val newBalance = accounts(user) - amount
    accounts + (user -> newBalance)
    newBalance
  }

  def addUser(user: String) = accounts += (user -> 30)

  def activeUser(user: String) = {
    accounts.getOrElse(user, accounts += (user -> defaultBalance))
    _activeUser = user
  }
}
