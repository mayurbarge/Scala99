import scala.collection.immutable._
import scala.io._


object Result2 {

  /*
   * Complete the 'minMoves' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. INTEGER startRow
   *  3. INTEGER startCol
   *  4. INTEGER endRow
   *  5. INTEGER endCol
   */

  case class Position(x: Int, y: Int)

  def getAllPositions(position: Position): scala.collection.immutable.List[Position] = {
     List[Position](
       Position(position.x+2, position.y-1),
       Position(position.x+2, position.y+1),
       Position(position.x-2, position.y+1),
       Position(position.x-2, position.y-1),
       Position(position.x+1, position.y+2),
       Position(position.x+1, position.y-2),
       Position(position.x-1, position.y+2),
       Position(position.x-1, position.y-2))
  }

  def isValid(position: Position, n: Int) = {
    if(position.x < 0 || position.x > n || position.y < 0 || position.y > n)
    false
    else true
  }

  def minMoves(n: Int, startRow: Int, startCol: Int, endRow: Int, endCol: Int): Int = {

    var visited = Array.ofDim[Int](n,n)
    def ifVisited(i:Int,j:Int) = visited(i)(j) == 1

    def go(m: Int, stack: List[Position], end: Position): Int = {
      if(stack.nonEmpty) {

        val current = stack.head
        val allPositions: scala.collection.immutable.List[Position] = getAllPositions(current).filter(isValid(_, n)).filter(e => ifVisited(e.x,e.y) !=1)
        if(allPositions.isEmpty || m > n*n)
          return -1


        val newStack =
        allPositions.flatMap(p => {
          if (p.x == end.x && p.y == end.y)
            return m
          else p :: stack
        })
        visited(current.x)(current.y) = 1

        go(m + 1, newStack, end)
      }
      else -1
    }

    go(1, List(Position(startRow, startCol)), Position(endRow, endCol))
  }

}
object Solution2 extends App {
  //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

  /*val n = StdIn.readLine.trim.toInt

  val startRow = StdIn.readLine.trim.toInt

  val startCol = StdIn.readLine.trim.toInt

  val endRow = StdIn.readLine.trim.toInt

  val endCol = StdIn.readLine.trim.toInt*/

  val result = Result2.minMoves(10, 0, 0, 0, 2)

  println(result)
  //printWriter.println(result)

  //printWriter.close()
}