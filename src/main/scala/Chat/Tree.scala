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
    def computePrice: Double = this match {
      case Article(product, typeProduct) => typeProduct.price
      case Articles(article, n) => n * article.computePrice
      case And(leftOp, rightOp) => leftOp.computePrice + rightOp.computePrice
      case Or(leftOp, rightOp) => Math.min(leftOp.computePrice, rightOp.computePrice)
      case _ => 0
    }
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
      case Price(articles) => "Cela coûte CHF " + articles.computePrice + "."
        // for the other commands, identification is needed
      case command =>
        if(UsersInfo.getActivateUser() != null) {
          command match {
            case Solde() => "Le montant actuel de votre solde est de CHF " +
              UsersInfo.solde(UsersInfo.getActivateUser()) + "."
            case Command(articles) => {
              val price = articles.computePrice
              val solde = UsersInfo.purchase(UsersInfo.getActivateUser(), price)
                "Voici donc " + articles.toString + " ! Cela coûte CHF " + price.toString +
                  " et votre nouveau solde est de CHF " + solde + "."
            }
          }
        }
        else {
          "Veuillez d'abord vous identifier."
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
  case class Articles(article: Article, amount: Int) extends ExprTree {
    override def toString: String = amount.toString + " " + article.toString
  }
  case class Article(product: Product, var typeProduct: TypeProduct = null) extends ExprTree {
    // set the default product type if needed
    if(typeProduct == null)
        typeProduct = product.defaultTypeProduct

    override def toString: String = {
      product.getname() + " " + typeProduct.name
    }
  }
  case class Solde() extends ExprTree
}
