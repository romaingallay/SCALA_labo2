package Data

import scala.collection.mutable

object UsersInfo {

  // Will contain the name of the currently active user; default value is null.
  private var _activeUser: String = _
  private val defaultSolde = 30

  private var accounts: mutable.HashMap[String, Double] = mutable.HashMap()

  /**
    * Update an account by decreasing its balance.
    * @param user the user whose account will be updated
    * @param amount the amount to decrease
    * @return the new balance
    */
  def purchase(user: String, amount: Double)= {
    val solde = accounts(user) - amount
    accounts += (user -> solde)
    solde
  }

  def addUser(user: String) = {
    accounts += (user -> 30)
    setActiveUser(user)
  }

  def setActiveUser(user: String) = {
    accounts.getOrElse(user, accounts + (user -> defaultSolde))
    _activeUser = user
  }

  def getActivateUser():String = _activeUser

  def solde(user: String) = accounts.getOrElse(user, throw new Error("It seems like this user does not exist..."))
}
