// Operator overloading
// Infix functions
// Function overloading
// Extension methods

data class Point(var first: Int, var second: Int) {
    infix operator fun times(k: Int): Point {
        return Point(first*k, second*k)
    }

    infix operator fun times(other: Point): Point {
        return Point(first*other.first, second*other.second)
    }

    operator fun get(index: Int): Int {
        return when (index) {
            0 -> first
            1 -> second
            else -> throw IndexOutOfBoundsException()
        }
    }

    operator fun set(index: Int, item: Int) {
        when (index) {
            0 -> first = item
            1 -> second = item
            else -> throw IndexOutOfBoundsException()
        }
    }

    operator fun plus(k: Int): Point {
        return Point(first + k, second + k)
    }
}

operator fun Int.times(point: Point)
        = point * this

fun main() {
    val p1 = Point(5, 7)
    val p2 = Point(4, 2)

    println(p1*7)
    println(7*p1)
    println(p1 times 7)
    println(p1*p2)

    p2[1] = 434
    println(p2 + 1)
    println(p2[1])
}
