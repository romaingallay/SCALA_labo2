package Chat

import Chat.Tokens._
import Data.{Products, TypeProduct,Product}
import Tree._

// TODO - step 4
class Parser(tokenizer: Tokenizer) {
  import tokenizer._

  var curTuple: (String, Token) = ("unknown", UNKNOWN)

  def curValue: String = curTuple._1
  def curToken: Token = curTuple._2

  /** Reads the next token and assigns it into the global variable curTuple */
  def readToken(): Unit = curTuple = nextToken()

  /** "Eats" the expected token, or terminates with an error. */
  private def eat(token: Token): Unit = if (token == curToken) readToken() else expected(token)

  /** Complains that what was found was not expected. The method accepts arbitrarily many arguments of type TokenClass */
  // TODO (BONUS): find a way to display the string value of the tokens (e.g. "BIERE") instead of their integer value (e.g. 6).
  private def expected(token: Token, more: Token*): Nothing =
    fatalError(" expected: " +
      (token :: more.toList).mkString(" or ") +
      ", found: " + curToken)

  def fatalError(msg: String): Nothing = {
    println("Fatal error", msg)
    new Exception().printStackTrace()
    sys.exit(1)
  }

  def extractClientName(clientName:String): String ={
    curValue.substring(1).toLowerCase()
  }

  /** the root method of the parser: parses an entry phrase */
  def parsePhrases() : ExprTree = {
    if (curToken == BONJOUR) eat(BONJOUR)
    if (curToken == JE ) {
      eat(JE)
      if(curToken == ETRE){
        eat(ETRE)
        if (curToken == ASSOIFFE) {
          // Here we do not "eat" the token, because we want to have a custom 2-parameters "expected" if the user gave a wrong token.
          readToken()
          Thirsty()
        } /** ex: je etre assoifé **/
        else if (curToken == AFFAME) {
          readToken()
          Hungry()
        } /** ex: je etre affamé **/
        else if (curToken == PSEUDO) {
          val clientName = extractClientName(curValue)
          readToken()
          Identification(clientName)
        } /** ex: je etre _PSEUDO **/
        else expected(ASSOIFFE, AFFAME, PSEUDO)
      } /** ex: je etre **/
      else if (curToken == ME){
        eat(ME)
        eat(APPELER)
        val clientName = extractClientName(curValue)
        Identification(clientName)
      } /** ex: je me appeler _PSEUDO **/
      else if (curToken == VOULOIR) {
        readToken()
        if(curToken == COMMANDER) {
          readToken()
          Command(parseCommande())
        } /** ex: je vouloir commander xxx **/
        else if (curToken == CONNAITRE) {
          readToken()
          eat(MON)
          eat(SOLDE)
          Solde()
        } /** ex: je vouloir connaitre mon solde **/
        else expected(COMMANDER, CONNAITRE)
      } /** ex: je vouloir **/
      else expected(ME, VOULOIR)
    } /** ex: je **/
    else if (curToken == COMBIEN) {
      eat(COMBIEN)
      eat(COUTER)
      Price(parseCommande())
    } /** ex: combien coute xxx **/
    else if (curToken == QUEL) {
      readToken()
      eat(ETRE)
      eat(LE)
      eat(PRIX)
      eat(DE)
      Price(parseCommande())
    }
    /** ex: prix de xxx **/
    else expected(BONJOUR, JE,QUEL,COMBIEN)
  }


  def parseCommande(): ExprTree = {

    var leftOp: ExprTree = parseArticle()
    /** Here we match all the cases where an OR or an AND token is detected by our parser**/
    curToken match {
      case OU => {
        readToken()
        Or(leftOp, parseCommande())
      }

      case ET => {
        readToken()
        And(leftOp, parseCommande())
      }
      case _ => leftOp
    }
  }


  def parseArticle(): ExprTree = {
    var numberProducts: Int = 0
    var orderedProduct: Product = null
    var typeProduct: TypeProduct = null

    // case where we have a number
    if (curToken == NUM) {
      // cast it into an integer
      numberProducts = curValue.toInt
    } else expected(NUM)
    readToken()

    // case where we have the product name
    if(Tokens.product(curToken)){
      orderedProduct = Products.getProduct(curValue)
    } else expected(BIERE,CROISSANT,CHIPS)
    readToken()
    // case where a type of product is specified
    typeProduct = orderedProduct.defaultTypeProduct
    if(Tokens.typeProduct(curToken)){
      typeProduct = orderedProduct.getTypeProduct(curValue)
      eat(curToken)
    }
    Articles(Article(orderedProduct,typeProduct),numberProducts)

  }
  // Start the process by reading the first token.
  readToken()
}
