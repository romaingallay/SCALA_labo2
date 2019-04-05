package Data

import Data.Products.Product.TypeProduit

object Products {

  // TODO: step 2 - here your will have an attribute that will contain the products (e.g. "bière"), their types (e.g. "Boxer"), and their prices (e.g. 2.0).
  // TODO: step 2 - You will also have to find a way to store the default type/brand of a product.

  object TypeProduit extends Enumeration {
    type TypeProduit = Value
    val Biere = Value("bière")
    val Croissant = Value("croissant")
  }

  object Product {
    type TypeProduit = String

    val BIERE: TypeProduit = "bière"
    val CROISSANT: TypeProduit = "croissant"

  }


  private def getPriceFromProductName(s: String): Int =
    s match {
      case "boxer" => 1
      case "farmer" => 1
      case "wittekop" => 2
      case "punkIpa" => 3
      case "Jackhammer" => 3
      case "Ténébreuse" => 4
      case "Maison" => 2
      case "Cailler" => 2
      case _ => 1

    }

  val allProducts = List((TypeProduit.Biere,"boxer",1) , (TypeProduit.Biere,"farmer",1) , (TypeProduit.Biere,"wittekop",2) ,
    (TypeProduit.Biere,"punkIpa",3) , (TypeProduit.Biere,"Jackhammer",3) , (TypeProduit.Biere,"Ténébreuse",4) ,
    (TypeProduit.Croissant,"Maison",2) , (TypeProduit.Croissant,"Cailler",2))

  //val products:List[List[List[String]]] = List(List(Product.BIERE).map(x => bieres),
  //                                List(Product.CROISSANT).map( x => croissants))

  def productPriceForBeer(name:String): Unit = {
    val value = allProducts.foreach( x => if (x._2 == name){
       return (x._3)
    })
  }

  val bieres:List[String] = List("boxer","farmer","wittekop","punkIpa","Jackhammer")
  val croissants:List[String] = List("Maison","Cailler")

  print (productPriceForBeer("boxer"))


  object Main extends App{
    print (productPriceForBeer("boxer"))

  }
}
