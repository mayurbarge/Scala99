/*
package datastructures

import sun.security.provider.certpath.Vertex

import scala.collection.mutable

case class Vertex(val name: String, val edges: Set[Vertex])

object Graphs extends App {


  def dfsMutableIterative(start: Vertex): Set[Vertex] = {
    var current = start

    val processed = mutable.Set[Vertex]()
    val inProcess = mutable.Stack[Vertex]()

    inProcess.push(current)
    while(inProcess.nonEmpty) {
      current = inProcess.pop()
      if(!processed.contains(current)) {
        println(" processing " + current.name)
        processed.add(current)
        for (next <- current.edges) {
          inProcess.push(next)
        }
      }
    }

    processed.toSet
  }

  override def main(args: Array[String]): Unit = {
    val vertexF = Vertex("F", Set.empty)
    val vertexE = Vertex("E", Set(vertexF))
    val vertexC = Vertex("C", Set(vertexE,vertexF))
    val vertexD = Vertex("D", Set(vertexC))
    val vertexB = Vertex("B", Set(vertexD))
    val vertexA = Vertex("A", Set(vertexB,vertexC))

    val start = vertexA

    dfsMutableIterative(start)


  }

}
*/
