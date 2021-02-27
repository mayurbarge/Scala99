
case class TravelZones(fromZone: Int, toZone: Int)

case class Pet(species: String, name: String, age: Int)

val pets = List(
  Pet("cat", "sassy", 2), Pet("cat", "bella", 3),
  Pet("dog", "poppy", 3), Pet("dog", "bodie", 4), Pet("dog", "poppy", 2),
  Pet("bird", "coco", 2), Pet("bird", "kiwi", 1)
)
// Example 3: groupMapReduce
pets.groupMapReduce(_.species)(_ => 1)(_ + _)

def subsets(array: Array[Int]) = {
  var init: Array[Array[Int]] = Array.empty[Array[Int]] :+ Array.empty[Int]
  for(i <- array.indices) {
      val newSubarrays: Array[Array[Int]] = init.map(x => i +: x)
      init ++= newSubarrays
  }
  init
}

subsets(Array(1,2))

def kway(array: Array[Array[Int]]) = {

}