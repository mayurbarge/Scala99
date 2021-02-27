/*
package codility

import scala.io.StdIn
import scala.util.Try


object Problem2 {


  def solution(a: Int, b: Int): Int = {
    // TODO Remove hashmap. it causes issue when defined at global level
    // it keeps old values in hashmap
    val squareCountsCache = collection.mutable.HashMap[Int,Int]()

    def maxCount: Int = squareCountsCache.values.max

    def squareInt(i: Int) = i*i

    def run(min:Int,max:Int, number:Int, square:Int, squareCounts: Int): Int = {
      println(number + " " + square, + squareCounts)
     // StdIn.readLine()
      // second condition stops when square exceeds max
      // only second condition could be kept as exit condition
      if(number > (max/2) || (square > max) && squareCounts == 1 )
        return maxCount

      if(square > max) {
        //println("7777777777777")
        val nextNumber = number + 1
        run(min,max,nextNumber,squareInt(nextNumber), 1)
      } else {
        if(square < min) {
          run(min,max,number,squareInt(square
          ), squareCounts+1)
        } else {
        //  println("------------")
          //println("putting " + number + " " +squareCounts)
          squareCountsCache.put(number, squareCounts)
          run(min,max,number,squareInt(square), squareCounts+1)
        }
      }

    }

    run(a,b,2, squareInt(2), 1)
  }
}

// TODO When given a number max looping should run  only for max/2 iterations as there is no possiblity of finding square of some number
// that falls in max/2 and max unless max/2 is 2
// second rule is for any given n if squareCount is k and square(k+1) goes beyond max then
// no other n+1 can be found which will have squareCount greater than k
// i.e. max is 20 and 2**2 => 4 ** 4 => 16 (squareCount 3) then there can not be any number greater than 2 which will have
// squareCount greater than 3
object MainApp extends App {

  override def main(args: Array[String]): Unit = {
    println("===" +Problem2.solution(2,7000))
    println("===" +Problem2.solution(10,20))
    println("===" +Problem2.solution(24,30))
    println("$$$" +Problem2.solution(5,100))



  }

}
*/
