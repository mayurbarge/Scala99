
def nth(index: Int, list:List[Int]): Int = {
  list match {
    case h :: tail if index == 0 => h
    case h :: tail => nth(index-1, tail)
    case Nil => throw new Exception("Could not find element")
  }

}
nth(2, List(1, 1, 2, 3, 5, 8))