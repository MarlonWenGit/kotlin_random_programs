// Extension methods can be used to add/alter methods in existing classes

fun String.count(c: Char): Int = this.count { it == c }

fun main() {
    println("fjodsfoidsf".count('f'))
}

// They can also be used with operator overloading

operator fun Int.times(repeated: String): String
        = repeated.repeat(this)

operator fun String.times(n: Int): String
        = this.repeat(n)

fun main() {
    println(3*"fd")
    println("fd"*3)
}

//• Equip Int with an isPowerOfTwo() method – returns true if and only if the
//receiving integer is a power of two

fun Int.isPowerOfTwo(): Boolean {
    if (this == 1) return true
    if (this < 1 || this % 2 != 0) return false
    return (this / 2).isPowerOfTwo()
}

fun main() {
    println(8.isPowerOfTwo())
    println(9.isPowerOfTwo())
    println((-1).isPowerOfTwo())
}

//• Equip String with an isPalindrome() method – returns true if and only if
//the receiving string is a palindrome

fun String.isPalindrome(): Boolean =
    this == this.reversed()

fun main() {
    println("kotlin".isPalindrome())
    println("racecar".isPalindrome())
}

//• isPalindrome() should be case-sensitive by default, but it should also be
//possible to provide a boolean argument controlling case sensitivity

fun String.isPalindrome(caseSensitive: Boolean = true): Boolean {
    if (caseSensitive) return this == this.reversed()

    val ls = this.lowercase()
    return ls == ls.reversed()

}

fun main() {
    println("kotlin".isPalindrome())
    println("Racecar".isPalindrome())
    println("racecar".isPalindrome(caseSensitive=false))
}

// Equip Double (64-bit floating point) with a sameAsFloat() method – returns
//true if and only if the Double’s value can be represented as a Float (32-bit
//floating point) with no change in value due to rounding

fun Double.sameAsFloat(): Boolean
        = float.toFloat().toDouble() == this

fun main() {
    val d: Double = 4.34
    println(d.sameAsFloat())
}

//

fun <A, B> Pair<A, B>.equalComponents() =
    first == second

fun main() {
    println(Pair(4, 4).equalComponents())
    println(Pair(4, 5).equalComponents())
    println(Pair(4, "4").equalComponents())
}

// Write an extension method swap(). When invoked on a pair whose
// components have the same type, it should return a new pair with
// these components but in reverse order.

fun <A, B> Pair<A, B>.swap() =
    Pair(second, first)

fun main() {
    println(Pair(4, 8).swap())
}

