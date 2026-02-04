fun sequentialDotProduct(
	array1: Array<Double>,
    array2: Array<Double>,
): Double = array1
    .zip(array2, Double::times)
    .sum()
    
private class DotProductWorker(
	private val array1: Array<Double>,
    private val array2: Array<Double>,
    private val startIndex: Int,
    private val numElements: Int,
) : Runnable {
    var result: Double = 0.0
    	private set
    
    override fun run() {
        val slice1 = array1.slice(startIndex..<startIndex+numElements)
        val slice2 = array2.slice(startIndex..<startIndex+numElements)
        result += slice1
           .zip(slice2, Double::times)
           .sum()
    }
}
    
fun parallelDotProduct(
	array1: Array<Double>,
    array2: Array<Double>,
): Double {
    val worker1 = DotProductWorker(
    	array1,
      array2,
      0,
      array1.size / 2
    )
    val worker2 = DotProductWorker(
    	array1,
      array2,
      array1.size / 2,
      array1.size / 2
    )
    
    val t1 = Thread(worker1)
    val t2 = Thread(worker2)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    
    return worker1.result + worker2.result
}
    
fun main() {
    val array1 = arrayOf(
    	3.0,
    	4.0,
      3.0,
    	4.0,
      3.0,
    	4.0,
    )
    
    val array2 = arrayOf(
    	5.0,
      -1.0,
      3.0,
    	4.0,
      3.0,
    	4.0,
    )
    
    println(sequentialDotProduct(array1, array2))
    println(parallelDotProduct(array1, array2))
}
