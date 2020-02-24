

val hello: String = "Hello!"
  println(hello)


  var helloThere: String = hello
  helloThere = hello + " There!"
  println(helloThere)

  val immutableHello: String = hello + " There!"
  println(immutableHello)
val moreStuff = immutableHello + " yeah"
  println(moreStuff)
val piSinglePrecision : Float = 3.14159265f
  //printf style
  println(f"pi is about $piSinglePrecision%.3f")

  val numberOne: Int =1

  println(f"Zero padding on the left: $numberOne%05d")

  println(s" I can use the s prefix to use variables like $numberOne $piSinglePrecision")

  println(s"The s prefix is not limited to variable; i can include any expression, like ${1+2}")

  //regular expression

  val theUltimateAnswer: String = "To life, the universe, and everything is 42."
  val pattern = """.* ([\d]+).*""".r

  val pattern(answerString) = theUltimateAnswer

  val answer = answerString.toInt
  println(answer)

  val isGreater = 1 > 2
  val isLesser = 1 < 2
  val impossible = isGreater & isLesser
  val anotherWay = isGreater && isLesser

  val picard: String = "Picard"
  val bestCaptain: String = "Picard"

  val isBest: Boolean = picard == bestCaptain
  // EXERCISE
  // Write some code that takes the value of pi, doubles it, and then prints it within a string with
  // three decimal places of precision to the right.
  // Just write your code below here; any time you save the file it will automatically display the results!
  val doublePi: Double = piSinglePrecision *2

  println(f"The doubled value of pi with 3 decimal places precision is $doublePi%.3f")

  //fibonacci for first 10 numbers
var f1 = 0
  var f2 = 1

  for (x <- 1 to 10) {
    println(f1)
    var next = f1 + f2
    f1 = f2
    f2 = next
  }

  def squareIt(x: Int) : Int ={x*x}
  println(squareIt(2))
  def cubeIt(x: Int): Int = {x*x*x}
  println(cubeIt(2))

  def transformIt(x: Int, f: Int => Int) : Int ={ f(x) }
  val result = transformIt(2,cubeIt)
  println(result)

  val sqrResult = transformIt(3, squareIt)

  println(sqrResult)

  //anonymous functions, lambdas or function literals

  println(transformIt(4,x=>x*x*x))

  println(transformIt(16,x=>x/2))
  println(transformIt(2,x=> { val y = x*2 ; y*y}))

  // EXERCISE
  // Strings have a built-in .toUpperCase method. For example, "foo".toUpperCase gives you back FOO.
  // Write a function that converts a string to upper-case, and use that function of a few test strings.
  // Then, do the same thing using a function literal instead of a separate, named function.

  def convertToUppercase(strVal: String) : String = {
      strVal.toUpperCase
  }
  println(convertToUppercase("foo"))
  def transformStr( strVal: String, f: String => String): String = { f(strVal)}

  println(transformStr("hello", strVal => strVal.toUpperCase))

  println(transformStr("HELLO", strVal => strVal.toLowerCase))

  val numberList = List(1,2,3,4,5)
  val increment = numberList.map((x: Int) => x+1)
  println(increment)
val sum = numberList.reduce((x: Int, y:Int) => x + y)
  println(sum)
val iHateFives = numberList.filter( (x: Int) => x != 5)
val iHateThrees = numberList.filter(_ != 3)

val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space Nine", "Janeway" -> "Voyager")
println(shipMap("Janeway"))

// Dealing with missing keys
println(shipMap.contains("Archer"))               //> false

val archersShip = util.Try(shipMap("Archer")) getOrElse "Unknown"
//> archersShip  : String = Unknown
println(archersShip)