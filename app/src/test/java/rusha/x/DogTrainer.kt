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