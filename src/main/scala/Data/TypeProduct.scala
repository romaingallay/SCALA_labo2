package Data

class TypeProduct( var name:String, var price:Int){
  // on redefini l'affichage du produit pour correspondre a celui attendu -> nom du produit suivit du prix
  override def toString: String = name + " : " + price.toString
}


/*
object TypeProduit extends Enumeration {
    type TypeProduit = Value
    val Biere = Value("bière")
    val Croissant = Value("croissant")

}*/