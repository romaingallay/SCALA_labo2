package Chat

object Tokens {
  type Token = Int

  // Terms
  val BONJOUR: Token     = 0
  val JE: Token          = 1
  val LE: Token          = 25
  val MON: Token         = 26
  val ME: Token          = 27
  val SOLDE: Token       = 28
  val DE: Token          = 29
  val PRIX: Token        = 30
  val QUEL: Token        = 31
  val COMBIEN: Token     = 32

  // Actions
  val ETRE: Token        = 2
  val VOULOIR: Token     = 3
  val CONNAITRE: Token   = 15
  val COMMANDER: Token   = 16
  val COUTER: Token      = 34
  val APPELER: Token     = 33
  // Operators
  val ET: Token          = 4
  val OU: Token          = 5
  // Products
  val BIERE: Token       = 6
  val CROISSANT: Token   = 7
  val CHIPS: Token       = 8
  // Brands
  val BOXER: Token       = 17
  val FARMER: Token      = 18
  val WITTEKOP: Token    = 19
  val PUNKIPA: Token     = 20
  val JACKHAMMER: Token  = 21
  val TENEBREUSE: Token  = 22
  val MAISON: Token      = 23
  val CAILLER: Token     = 24
  // Utils
  val PSEUDO: Token      = 9
  val NUM: Token         = 10
  val UNKNOWN: Token     = 11
  val EOL: Token         = 12
  // Test
  val ASSOIFFE : Token = 13
  val AFFAME : Token = 14

  def product(token: Token) = token > 5 && token < 9

  def typeProduct(token: Token) = token > 16 && token < 25
}
