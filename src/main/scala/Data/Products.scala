package Data

object Products {

  // TODO: step 2 - here your will have an attribute that will contain the products (e.g. "bière"), their types (e.g. "Boxer"), and their prices (e.g. 2.0).
  // TODO: step 2 - You will also have to find a way to store the default type/brand of a product.


  /*val allProducts = List((TypeProduit.Biere,"boxer",1) , (TypeProduit.Biere,"farmer",1) , (TypeProduit.Biere,"wittekop",2) ,
    (TypeProduit.Biere,"punkIpa",3) , (TypeProduit.Biere,"Jackhammer",3) , (TypeProduit.Biere,"Ténébreuse",4) ,
    (TypeProduit.Croissant,"Maison",2) , (TypeProduit.Croissant,"Cailler",2))*/

  // Here we chose a Set, mutable collection, so that we can add all our products
  var allProducts: Set[Product] = Set();


  def addProduct(product: Product) = {
    allProducts = allProducts + product
  }

  def addProductList(products: List[Product]) = allProducts = allProducts ++ products

  def getProduct(name: String): Product =
    allProducts.find(x => x.getname()  == name ) match {
      case None => {
        println(" No product with specified name retrieved !")
        throw new Error("Product with name " + name + " not retrieved !")
      }
      case Some(productFound) => productFound
    }

  override def toString: String = allProducts.foldLeft("") { (str, p) => str + p.toString + "\n" }



  // returns string of all elements from left to right (from first specified by user to the last one)

 /* private def getPriceFromProductName(s: String): Product =
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

*/


  //val products:List[List[List[String]]] = List(List(Product.BIERE).map(x => bieres),
  //                                List(Product.CROISSANT).map( x => croissants))

  /*def productPriceForBeer(name:String): Unit = {
    val value = allProducts.foreach( x => if (x._2 == name){
       return (x._3)
    })
  }*/

  val bieres:List[String] = List("boxer","farmer","wittekop","punkIpa","Jackhammer")
  val croissants:List[String] = List("Maison","Cailler")



}


