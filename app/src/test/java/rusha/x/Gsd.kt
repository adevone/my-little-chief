package rusha.x

class Gsd : Dog {
    var isJob = true
    var goodBoy = true
    override fun makeSound(loud: Boolean) {
        if (isJob == true) println(if (!loud) "Gav" else "GAV")
        else println(if (!loud) "mrrrr" else "MRRR")
    }

    override fun givePaw() {
        println("WhoTheGoodBoyThere?Yes.ItsMe")
    }

    override fun pat() {
        if (goodBoy == true) println("Fawn")
        else println("!Fawn")
    }
}