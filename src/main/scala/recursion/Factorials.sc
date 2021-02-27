def tailRecFactorial(number:BigInt): BigInt = {
  def go(current: BigInt, acc: BigInt):BigInt = {
   // println(current + " " + acc + " = "+ acc * current)
    if(current == number)
      acc
    else go(current+1, acc * current)
  }
  go(1, number)
}

def tailRecFactorial2(n: BigInt): BigInt = {
  def go(x: BigInt, accumulator: BigInt): BigInt = {
    if (x <= 1) accumulator
    else go(x - 1, x * accumulator)
  }
  go(n, 1)
}
//tailRecFactorial(12)
//tailRecFactorial2(12)
//tailRecFactorial(13)
//tailRecFactorial2(13)



for (i <- 10 to 30) {
  println(i + " " + tailRecFactorial(i) + " " + tailRecFactorial2(i))
}


//reverse a list
//(1,2,3).foldRight(Nil[Int])((acc,e) => e :: acc)

