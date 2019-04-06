package Data

object Products {

  // Here we chose a Set, mutable collection, so that we can add all our products
  var allProducts: Set[Product] = Set();

  val defaultBeer = new TypeProduct("boxer", 1)
  val beerTypes: Set[TypeProduct] = Set(
    new TypeProduct("farmer", 1),
    new TypeProduct("wittekop", 2),
    new TypeProduct("jackhammer", 3),
    new TypeProduct("punkipa", 3),
    new TypeProduct("tenebreuse", 4),
    defaultBeer)

  val defaultCroissant = new TypeProduct("maison", 2)
  val croissantTypes: Set[TypeProduct] = Set(
    new TypeProduct("cailler", 2),
    defaultCroissant
  )

  // Create products
  val beer = new Product("biere", beerTypes, defaultBeer)
  val croissant = new Product("croissant", croissantTypes, defaultCroissant)

  // Add products to the Products object
  Products.addProductList(Seq(beer, croissant))


  def addProduct(product: Product) = {
    allProducts = allProducts + product
  }

  def addProductList(products: Seq[Product]) = allProducts = allProducts ++ products

  def getProduct(name: String): Product = {
    allProducts.find(x => x.getname() == name) match {
      case Some(productFound) => productFound
      case None => {
        throw new Error("Product with name " + name + " not retrieved !")
      }
    }
  }

  override def toString: String = allProducts.foldLeft("") { (str, p) => str + p.toString + "\n" }
}


