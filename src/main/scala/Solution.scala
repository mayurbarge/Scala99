
import scala.collection.mutable
import scala.io.StdIn


object Result {

 /* /*
   * Complete the 'balancedSum' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY arr as parameter.
   */

  def balancedSum(arr: Array[Int]): Int = {
    def go(subLists: (Array[Int], Array[Int]) ): Option[Int] = {
      val (left, right) = subLists
      println("Left " + left.toList)
      println("Right " + right.toList)
      if(left.sum == right.tail.sum) {
        Some(right.head)
      } else {
        None
      }

    }
    val pivotes = scala.collection.immutable.List.empty[Int]
    for (i <- 0 until arr.length ) {
      println("i : " + i)
      go(arr.splitAt(i)).map(_ :: pivotes)
    }

    val allPivotes = (0 until arr.length).map(i => {
      go(arr.splitAt(i)).getOrElse(Integer.MAX_VALUE) :: pivotes
    }).flatten

    allPivotes.min

  }*/


    /*
     * Complete the 'countDuplicate' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY numbers as parameter.
     */

    def countDuplicate(numbers: Array[Int]): Int = {
      Tuple1
      val counter: mutable.Map[Int, Int] = scala.collection.mutable.Map[Int, Int]()
      ( 0 until numbers.length).map(i => {

        counter.put(numbers(i), counter.get(numbers(i)).getOrElse(0)+1)
      })

      counter.filter(e => e._2 > 1).keys.toList.length
    }

}

object Solution extends App {
  //def main(args: Array[String]) {
   // val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val arrCount = StdIn.readLine.trim.toInt

    val arr = Array.ofDim[Int](arrCount)

    for (i <- 0 until arrCount) {
      val arrItem = StdIn.readLine.trim.toInt
      arr(i) = arrItem
    }

    val result = Result.countDuplicate(arr)
  println(result)

    //printWriter.println(result)

   // printWriter.close()
  //}
}