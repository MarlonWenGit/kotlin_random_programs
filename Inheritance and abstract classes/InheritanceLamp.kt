val MINIMUM_BRIGHTNESS = 1
val MAXIMUM_BRIGHTNESS = 10

open class Lamp(isOn: Boolean) {
    protected var isOn = isOn
        private set

    open fun pressSwitch() {
        isOn = !isOn
    }

    override fun toString(): String =
        if (isOn) {
            "LIGHT"
        } else {
            "(darkness)"
        }
}

class DimmingLamp(
    isOn: Boolean,
) : Lamp(isOn) {
    private var brightness: Int =
        if (isOn) { MAXIMUM_BRIGHTNESS } else { 0 }

    override fun pressSwitch() {
        super.pressSwitch()
        if (isOn) {
            brightness = MAXIMUM_BRIGHTNESS
        } else {
            brightness = 0
        }
    }

    fun up(): DimmingLamp {
        if (isOn && brightness < MAXIMUM_BRIGHTNESS) {
            brightness++
        }
        return this
    }

    fun down(): DimmingLamp {
        if (isOn && brightness > MINIMUM_BRIGHTNESS) {
            brightness--
        }
        return this
    }

    override fun toString(): String =
        super.toString() +
                if (isOn) {
                    ": " + "*".repeat(brightness)
                } else {
                    ""
                }
}

fun main() {
    val lamp = Lamp(false)
    println(lamp)
    lamp.pressSwitch()
    println(lamp)

    val dimmingLamp = DimmingLamp(true)
    println(dimmingLamp)
    dimmingLamp.down().down().down().down().down().down().down()
    println(dimmingLamp)
    dimmingLamp.down().down().down().down().down().down().down()
    println(dimmingLamp)
    dimmingLamp.up().up().up()
    println(dimmingLamp)
    dimmingLamp.pressSwitch()
    println(dimmingLamp)
    dimmingLamp.pressSwitch()
    println(dimmingLamp)
}
