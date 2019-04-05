package Data


class Product(name:String, var typeProducts: Set[TypeProduct], var defaultTypeProduct: TypeProduct = null) {


  def setDefaultTypeProduct(typeProduct: TypeProduct) = {
    defaultTypeProduct = typeProduct
  }

  def addTypeProduct(newTypeProduct: TypeProduct) = {
      typeProducts = typeProducts + newTypeProduct
  }

  def addTypeProducts(newTypeProduct: Seq[TypeProduct]) ={
    typeProducts = typeProducts ++ newTypeProduct
  }


  override def toString: String = name + " :" + typeProducts.foldLeft(""){ (str, prod) =>  str + "\n\t" + prod.toString}

  def getname(): String ={
    return name
  }


}

/*object Produit {
  type TypeProduit = String

  val BIERE: TypeProduit = "bière"
  val CROISSANT: TypeProduit = "croissant"

}*/
