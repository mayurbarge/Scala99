/*
package battleship

import battleship.Solution.printBoard

import scala.io.StdIn

case class Position(row:Int,column:Int, status: Status =  Unallocated)
case class Ship(state: ShipState, positions:List[Position])

trait ShipState
case object Live extends ShipState
case object Destroyed extends ShipState

trait Status
case object Occupied extends Status
case object Hit extends Status
case object Miss extends Status
case object D extends Status
case object Unallocated extends Status

case class GameBoard(positions: List[Position]) {
  def find(i:Int, j: Int) = positions.find(e => e.row == i && e.column == j)
}

case object Ship {
  def buildShips(board: GameBoard) = {
    val submarines = List(Ship(Live,List(Position(1,1, Occupied))), Ship(Live,List(Position(1,2,Occupied))))
    /*val destroyer = List(Ship(Live,List(Position(1,6),Position(1,7))), Ship(Live,List(Position(2,2),Position(2,3))))
    val cruiser = List(Ship(Live,List(Position(2,1),Position(3,1),Position(4,1))))
    val battleship = List(Ship(Live,List(Position(4,3),Position(4,4),Position(4,5),Position(4,6))))
    val carrier = List(Ship(Live,List(Position(6,2),Position(6,3),Position(6,4),Position(6,5),Position(6,6))))
*/
    val updatedBoard = GameBoard.updateBoard(Occupied,board, submarines.flatMap(_.positions))
    // val updatedShips = submarines.map(Ship.update(Occupied, updatedBoard.))
    //printBoard(updatedBoard)
    (updatedBoard, submarines)

  }

  def update(newStatus: Status, position: Position,ship: Ship) = {
    val newPositions =
      ship.positions.foldLeft(List.empty[Position])((acc, e) => {
        if(e.row==position.row && e.column==position.column) {
          e.copy(status = newStatus) :: acc
        } else {
          e :: acc
        }
      })
    ship.copy(positions = newPositions)
  }

}


case object GameBoard {
  def updateBoard(statusPassed: Status, board: GameBoard, positions: List[Position]) = {
     val updatedBoardPositions = board.positions.foldLeft(List.empty[Position])((acc, e) =>{
       if(positions.exists(p => p.row == e.row && p.column == e.column))
         e.copy(status = statusPassed) :: acc
       else
         e :: acc
     })

     val updatedBoard = board.copy(positions = updatedBoardPositions)
    updatedBoard
   }

  def updateHitsAndMisses( position: Position,positions: List[Position]) = {

    val newPositions = positions.foldLeft(List.empty[Position])((acc, e) => {
      if(e.row==position.row && e.column==position.column) {
        e.copy(status = Hit) :: acc
      } else {
        e.copy(status = Miss) :: acc
      }
    })

    GameBoard(newPositions)

  }


  def markDestroyed(board: GameBoard, ships: List[Ship]) = {
    val shipsUpdated = ships.map(ship => {
      val x: Seq[Status] = ship.positions.map(pos => {
        board.positions.find(p => p.row==pos.row&&p.column==pos.column).get.status
      })
      if(!x.contains(Live)) {
        ship.copy(state = Destroyed)
      } else ship
    })

    val destroyedShips = shipsUpdated.filter(_.state == Destroyed)

    val newPositions = board.positions.map(pos => {
      if(destroyedShips.exists(ship => {
        ship.positions.exists(p => p.row == pos.row && p.column == pos.column)
      })) {
        pos.copy(status = D)
      } else pos
    })

    (board.copy(newPositions), shipsUpdated)

  }


}

object Solution {

  def main(args: Array[String]) {
    val positions = (1 to 10).flatMap(i => (1 to 10).map(j => Position(i, j))).toList
    val board = GameBoard(positions)

    println("-----------")
    //println(board)

    val (updatedBoard, liveShips) = Ship.buildShips(board)
    //println(updatedBoard)
    //println(liveShips)
    play((updatedBoard, liveShips))
  }

  def play(tuple: (GameBoard, List[Ship])): (GameBoard, List[Ship]) = {
      val input = StdIn.readLine()
      val (board, ships) = tuple
    printBoard(board)

    if(!ships.exists(_.state == Live)) {
        System.exit(0)
      }

    val p = input.split(" ").toList.map(_.toInt)
    val position: Position = Position(p.head, p.last)

    if(board.positions.exists(e => e.row==position.row && e.column == position.column)) {
      val (updatedBoard, newShips) = markPosition(position, board, ships)


      play(updatedBoard, newShips)
    } else {
      play(tuple)
    }
  }

  def printBoard(board: GameBoard) = {
    for(i<-1 to 10) {
      for
      (j <- 1 to 10) {
        board.find(i,j).get.status match {
          case Hit => print("H")
          case Miss => print("M")
          case D => print("D")
          case Occupied => print("O")
          case _=> print("-")
        }
      }
      println()
    }

  }

  private def markPosition(boardPosition: Position, board:GameBoard, ships: List[Ship]): (GameBoard, List[Ship]) = {
    val shipPresentOnThisLocation = ships.flatMap(_.positions).exists(p => p.row == boardPosition.row && p.column == boardPosition.column)

    println("$$$$$$$$$$$$ "+ shipPresentOnThisLocation)
    if(shipPresentOnThisLocation) {
      val updatedBoard = GameBoard.updateHitsAndMisses(boardPosition, board.positions)
      val updatedShips = ships.map(Ship.update(Hit,boardPosition,_))

      GameBoard.markDestroyed(updatedBoard, updatedShips)
    } else {
      (board, ships)
    }
  }
}*/
