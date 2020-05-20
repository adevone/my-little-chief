package rusha.x

import org.junit.Test

class RenewKnowledgeTest {

    @Test
    fun dogs() {

        val alisa = Chihua()
        val gosha = Chihua()
        alisa.makeSound(loud = false)
        alisa.givePaw()
        gosha.angryLvl = 2
        gosha.givePaw()
        gosha.makeSound(loud = true)

        val mark = Pug()
        mark.makeSound(loud = true)
        mark.givePaw()

        val jeck = Gsd()
        jeck.makeSound(loud = true)
        jeck.givePaw()
        jeck.pat()
    }

    @Test
    fun people() {

        val viktor = DogTrainer(dogs = listOf(Gsd(), Gsd(), Gsd(), Pug(), Chihua()))
        viktor.train()

        val marta = HostessDog(dogs = listOf(Gsd(), Gsd(), Gsd(), Pug(), Chihua()))
        marta.helloDog()
    }


}