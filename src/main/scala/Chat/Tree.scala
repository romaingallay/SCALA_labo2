package Chat

import Data.UsersInfo
import Data.TypeProduct
import Data.Product

// TODO - step 3
object Tree {

  /**
    * This sealed trait represents a node of the tree and contains methods to compute it and write its output text in console.
    */
  sealed trait ExprTree {
    /**
      * Compute the price of the current node, then returns it. If the node is not a computational node, the method
      * returns 0.0.
      * For example if we had a "+" node, we would add the values of its two children, then return the result.
      * @return the result of the computation
      */
    def computePrice: Double = ???

    /**
      * Return the output text of the current node, in order to write it in console.
      * @return the output text of the current node
      */
    def reply: String = this match {
      // Example cases
      case Thirsty() => "Eh bien, la chance est de votre côté, car nous offrons les meilleures bières de la région !"
      case Hungry() => "Pas de soucis, nous pouvons notamment vous offrir des croissants faits maisons !"
      case Identification(user) => {
        UsersInfo.addUser(user)
        "Bonjour, " + user
      }
      case Price(items) => "Cela coûte CHF " + items.computePrice
      case other =>
        if(UsersInfo.getActivateUser() == null)
          "Veuillez d'abord vous identifier"
        else other match {
          case Solde() => "Le montant actuel de votre solde est de CHF " +
            UsersInfo.solde(UsersInfo.getActivateUser())
          case Command(basket) => {
            // Compute the total price of the command
            val totalPrice = basket.computePrice
            // Check if there is enough money on user's account
            // If there is not enough money, prevent the user, execute the command otherwise
            val curBalance = UsersInfo.solde(UsersInfo.getActivateUser())
            val newBalance =  UsersInfo.purchase(UsersInfo.getActivateUser(), totalPrice)
            if(curBalance == newBalance)
              "Solde insuffisante"
            else
              "Voici donc " + basket.toString + " ! Cela coûte CHF " + totalPrice.toString +
                " et votre nouveau solde est de CHF " + newBalance
          }
          case Price(basket) => "Cela coûte " + basket.computePrice.toString
        }
    }
  }

  /**
    * Declarations of the nodes' types.
    */
  // Example cases
  case class Thirsty() extends ExprTree
  case class Hungry() extends ExprTree
  case class Identification(name: String) extends ExprTree
  case class Command(items: ExprTree) extends ExprTree
  case class Price(items: ExprTree) extends ExprTree
  case class And(first: ExprTree, second: ExprTree) extends ExprTree {
    override def toString: String = first.toString + " et " + second.toString
  }
  case class Or(first: ExprTree, second: ExprTree) extends ExprTree {
    override def toString: String = first.toString + " ou " + second.toString
  }
  case class Articles(article: Article, amount: Int) extends ExprTree
  case class Article(product: Product, var typeProduct: TypeProduct = null) extends ExprTree {
    // set the typeProduct to the product's default one in case it is not specified
    if(typeProduct == null)
      if(product.defaultTypeProduct == null)
        throw new Error("No typeProduct specified for item " + product.getname())
      else
        typeProduct = product.defaultTypeProduct
    // check if the typeProduct exists in the products typeProducts set
    if(!product.typeProducts(typeProduct))
      throw new Error("Brand " + typeProduct.name + " does not correspond to product " + product.getname())

    override def toString: String = {
      product.getname() + " " + typeProduct.name
    }
  }
  case class Solde() extends ExprTree
}
