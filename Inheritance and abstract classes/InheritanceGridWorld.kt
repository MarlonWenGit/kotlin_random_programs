import kotlin.math.*

enum class Terrain {
    WATER, FOREST, SWAMP, ROCKS
}

class DeadPlayerException(message: String) : Exception(message)

open class GridWorld(
    protected val width: Int,
    protected val height: Int,
) {
    protected val grid: Array<Array<Terrain>> = randomTerrain()
    protected var position: Pair<Int, Int> = randomPosition()

    override fun toString(): String =
        grid.joinToString(
            separator = "\n",
            transform = { row -> row.joinToString(
                separator = ", "
            )}
        )

    fun randomTerrain(): Array<Array<Terrain>> =
        Array(height) {
            Array(width) {
                Terrain.entries.random()
            }
        }

    fun randomPosition(): Pair<Int, Int> =
        (0..<width).random() to (0..<height).random()

    fun up() = updatePosition(position.copy(second = position.second - 1))

    fun down() = updatePosition(position.copy(second = position.second + 1))

    fun left() = updatePosition(position.copy(first = position.first - 1))

    fun right() = updatePosition(position.copy(first = position.first + 1))

    private fun updatePosition(newPosition: Pair<Int, Int>) {
        if (newPosition.first in 0..<width && newPosition.second in 0..<height) {
            position = newPosition
            return
        }
        position = handleOverrun(newPosition)
    }

    protected open fun handleOverrun(newPosition: Pair<Int, Int>): Pair<Int, Int> =
        throw NotImplementedError("This method should be provided by subclasses")
}

class BoundedGridWorld(
    width: Int,
    height: Int,
) : GridWorld(width, height) {
    override fun handleOverrun(newPosition: Pair<Int, Int>): Pair<Int, Int> =
        Pair(
            first = max(0, min(newPosition.first, width - 1)),
            second = max(0, min(newPosition.second, height - 1)),
        )
}

class DeadlyGridWorld(
    width: Int,
    height: Int,
) : GridWorld(width, height) {
    override fun handleOverrun(newPosition: Pair<Int, Int>): Nothing =
        throw DeadPlayerException("Fell off world!")
}

class RandomGridWorld(
    width: Int,
    height: Int,
) : GridWorld(width, height) {
    override fun handleOverrun(newPosition: Pair<Int, Int>): Pair<Int, Int> =
        randomPosition()
}

class TorusGridWorld(
    width: Int,
    height: Int,
) : GridWorld(width, height) {
    override fun handleOverrun(newPosition: Pair<Int, Int>): Pair<Int, Int> =
        Pair(
            first = (newPosition.first + width) % width,
            second = (newPosition.second + height) % height
        )
}

fun main() {
    val rgw = RandomGridWorld(4, 6)
    println(rgw)
}
