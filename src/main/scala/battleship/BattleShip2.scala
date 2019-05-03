package battleship

import scala.io.StdIn

trait Status
case object Occupied extends Status
case object Empty extends Status
case object Hit extends Status
case object Miss extends Status
case object Destroyed extends Status

trait ShipState
case object ShipLive extends ShipState
case object ShipDead extends ShipState
case class Position(row:Int, col:Int)
case class Ship(shipStatus: ShipState = ShipLive, positions: List[Position])

case class Board(rows:List[(Position,Status)]) {
  def update(position: Position, status: Status) = {
    val updatedRows = rows.foldLeft(List.empty[(Position,Status)])((acc, e) => {
      if(e._1 equals position) {
        e.copy(_2 = status) :: acc
      } else {
        e :: acc
      }
    })
    Board(updatedRows)
  }
}

object Board {
  def build(): Board = {
    val positions = (1 to 10).toList.map(i => (1 to 10).toList.map(j => (Position(i,j),Empty))).flatten
    Board(positions)
  }

  def shot(shotPos:Position, ships:List[Ship], board: Board) = {
    val hitShip = ships.find(ship => ship.positions.contains(shotPos))
    if(hitShip.isDefined) {
      board.update(shotPos,Hit)
    } else
      board.update(shotPos,Miss)
  }

  def markDead(ships:List[Ship], board: Board) = {
    val rowsMap = board.rows.toMap
    val shipsMaredDead = ships.foldLeft(List.empty[Ship])((acc,ship) => {
      val allHit = ship.positions.forall(rowsMap(_).equals(Hit))
      if(allHit) ship.copy(shipStatus = ShipDead) :: acc
      else ship :: acc
    })

    val deadShipPositions = shipsMaredDead.filter(_.shipStatus==ShipDead).map(_.positions).flatten

    val updatedBoardPositions = board.rows.foldLeft(List.empty[(Position,Status)])((acc,e) => {
      if(deadShipPositions.contains(e._1)) {
        e.copy(_2 = Destroyed) :: acc
      } else e :: acc
    })

    val updatedBoard = Board(updatedBoardPositions)
    (shipsMaredDead, updatedBoard)
  }

}

object Ships {
  def buildShips():List[Ship] = List(Ship(ShipLive,List(Position(1,1), Position(2,1))), Ship(ShipLive,List(Position(1,2))))
}

object BattleShip2 extends App {
    val board = Board.build()
    val ships = Ships.buildShips()

    play(ships, board)

  def play(ships: List[Ship], board: Board):(List[Ship], Board) = {
    val input = StdIn.readLine()
    val inputPositions = input.split(" ").map(_.toInt)
    val shot = Position(inputPositions.head, inputPositions.last)


    val boardShot = Board.shot(shot, ships, board)
    println(boardShot)
    val (newShips, newBoard) = Board.markDead(ships, boardShot)
    printBoard(newBoard)

    if(allDead(newShips)) System.exit(0)

    play(newShips, newBoard)
  }

  def allDead(ships: List[Ship]) = {
    ships.forall(ship => ship.shipStatus==ShipDead)
  }


  def printBoard(board: Board) = {
    println(board)
    for(i<-1 to 10) {
      for
        (j <- 1 to 10) {
        board.rows.find(e => e._1.row == i && e._1.col==j).get._2 match {
          case Hit => print("H")
          case Miss => print("M")
          case Destroyed => print("D")
          case _=> print("-")
        }
      }
      println()
    }

  }
}
