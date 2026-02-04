class Chatterbox(
	private val id: Int,
    private val words: List<String>,
): Runnable {
    override fun run() {
        for (word in words) {
            println("$id: $word")
        }
    }
}

fun main() {
    val words = listOf("Hello", "World!", "I!", "am!", "a!", "thread!")
    val cb1 = Chatterbox(1, words)
    val cb2 = Chatterbox(2, words)
    
    val t1 = Thread(cb1)
    val t2 = Thread(cb2)
    
    t1.start()
    t2.start()
    t1.join()
    t2.join()
}
