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


        class Meykun {
            var inLite: Boolean = true
            fun clean() {
                inLite = false
                println("hsss")
            }

            fun Food() {
                inLite = true
                println("nyam")
            }

            fun pat(inLite: Boolean) {
                println(if (!inLite) "czarap" else "mrrr")
            }
        }

        class Sphinx {
            var happyLvl = 3
            fun pat() {
                if (happyLvl < 7) {
                    happyLvl = happyLvl + 1
                }
                println("mrrr")
            }

            fun giveFood() {
                if (happyLvl< 6){
                happyLvl = happyLvl + 2
                println(" nyam")
            }

            fun clean()
            happyLvl = happyLvl - 3
            println ("ksss")

        }

        class UndegraundKissy {

        }
    }


}
//мейкун
//помыть, тогда он приходит в ярость и поет дориме,
// его можно покормить тогда он успокаивается
// можно погладить если он в этот момент в ярости, то он царапает, если спокоен мурлыкает

//Сфинкс
// Можно погладить. Каждый раз когда его гладят уровень счастья увеличивается на 1(<7)
//Кормить, уровень счастья на 2(<7)
// Купать, уровень счастья на 3( не ниже 0)

//ДВОРОВЫЙ
//Можно покормить ест всегда.
//Можно помыть. Он промолчит,но запомнит
//Погладить. Если его только что покормили, то мурлычет. Если не кормили давно то нейтрален.
// Если помыли то шипит

//Хозяин может иметь несколько кошек.Утром кормит кошек. Днем он их кормит и гладит, вечером - кормит.
//Когда наступает пятница 13е - он их моет.

//Кормилица может кормить всех кошек, которые у нее есть.