import scala.io.StdIn

trait Status
case object Up
case object Down
case object Stopped


trait Command
case class Up(currentFloor:Int)
case class Down(currentFloor:Int)
case class Internal(floorToGo:Int)

case class Lift(status: Status, commands: List[Command] )

object Main extends  App {
    println("Lift stopped..")
    println("Lift commands")
    println("Outside panel commands: floor-no U/D .. e.g. 4 U")
    println("Inside panel commands: floor-no I .. e.g. 5 I")
    println("X to Stop")

    while (true) {
        val input = StdIn.readLine()
        input.split(" ").toList match {
            case a :: Nil if a equals "X" => System.exit(0)
            case a :: b :: Nil if b equals "U" => {
                println(" Level " + a + " U")
            }
            case a :: b :: Nil if b equals "D" => {
                println(" Level " + a + " D")
            }
            case a :: b :: Nil if b equals "I" => {
                println(" Level " + a + " I")
            }
            case _ => throw new Exception("Not valid command")
        }
    }
    println("Lift stopped..")
}

