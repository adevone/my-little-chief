package rusha.x

class DogTrainer(
    private val dogs: List<SportDog>
) {
    fun train() {
        startTrain()
        dogs.forEach(fun(sportdog: SportDog) {
            sportdog.makeSound(loud = false)
        })
    }

    private fun startTrain() {
        println("Voice!")
    }
}

interface SportDog {
    fun makeSound(loud: Boolean)
}



fun main() {
    val a = Rectangle(width = 10, height = 20)
    makeSomethingWithRectangle(r = a)

    val b = Square(side = 20)
    makeSomethingWithRectangle(r = b)
}



open class Rectangle(
    private var width: Int,
    private var height: Int
) {
    fun setSize(width: Int, height: Int) {
        this.width = width
        this.height = height
    }
}

class Square(side: Int) : Rectangle(width = side, height = side)

/**
 * Нельзя использовать со [Square]
 */
fun makeSomethingWithRectangle(r: Rectangle) {
    r.setSize(width = 10, height = 30)
}