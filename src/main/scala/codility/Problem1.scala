package codility


object Problem1 {
  val cache = (0 to 9).map(e => {
    if(e equals 0) 0
    else
      Math.pow(10,e).toInt
  }).toParArray


  def solution(n: Int): Int = {
    def recurse(number:Int, digits: Int): Int = {
      if( number > 0 ) recurse(number/10 , digits+1)
      else digits
    }
    val digits = recurse(n, 0)

    cache(digits-1)
  }


}

object MainDemo extends App {
  override def main(args: Array[String]): Unit = {

    println(Problem1.solution(1))
    println(Problem1.solution(13))
    println(Problem1.solution(134))
    println(Problem1.solution(1344))
    println(Problem1.solution(13444))
  }

}