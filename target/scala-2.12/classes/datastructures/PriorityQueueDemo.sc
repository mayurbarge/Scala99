case class Order(name:String, quantity: Int)

implicit val ordering = Ordering.fromLessThan[Order](_.quantity > _.quantity)
val queue = collection.mutable.PriorityQueue(Order("pears",4),Order("ray",1),Order("dove",9))
println(queue.dequeue())
