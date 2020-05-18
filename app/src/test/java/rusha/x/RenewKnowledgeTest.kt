package rusha.x

import org.junit.Test

class RenewKnowledgeTest {

    @Test
    fun test() {

        class Chihua {
            var angryLvl = 0
            fun givePaw() =
                if (angryLvl == 0) println(message = "GivsPaw")
                else println("kus`")

            fun makeSound(loud: Boolean) = when {
                angryLvl == 0 -> println(message = if (!loud) "rrr" else "RRR")
                angryLvl == 1 -> println(message = if (!loud) "grrr" else "GRRR")
                angryLvl == 2 -> println(message = if (!loud) " GAV!" else "killingGav!")
                else -> println(message = " AllahAkbar")
            }
        }

        val alisa = Chihua()
        val gosha = Chihua()
        alisa.makeSound(loud = false)
        alisa.givePaw()
        gosha.angryLvl = 2
        gosha.givePaw()
        gosha.makeSound(loud = true)




        class Pug {

            fun makeSound(loud: Boolean) {
                println(message = if (!loud) "khsss" else "KHSSSandSlaver")
            }

            fun givePaw() {
                println("givePaw")
            }

        }

        val mark = Pug()
        mark.makeSound(loud = true)
        mark.givePaw()

        class Gsd {
            var isJob = true
            var goodBoy = true
            fun makeSound(loud: Boolean) {
                if (isJob == true) println(if (!loud) "Gav" else "GAV")
                else println(if (!loud) "mrrrr" else "MRRR")
            }

            fun givePaw() {
                println("WhoTheGoodBoyThere?Yes.ItsMe")
            }

            fun pat() {
                if (goodBoy == true) println("Fawn")
                else println("!Fawn")
            }
        }

        val jeck = Gsd()
        jeck.makeSound(loud = true)
        jeck.givePaw()
        jeck.pat()

        class Gsd {
            var isJob = true
            var goodBoy = true
        }

        fun makeSound(u: Gsd, loud: Boolean) {
            if (u.isJob == true) println(if (!loud) "Gav" else "GAV")
            else println(if (!loud) "mrrrr" else "MRRR")
        }

        fun givePaw() {
            println("WhoTheGoodBoyThere?Yes.ItsMe")
        }

        fun pat(u: Gsd) {
            if (u.goodBoy == true) println("Fawn")
            else println("!Fawn")
        }

        val jeck = Gsd()
        makeSound(u = jeck, loud = true)
        givePaw()
        pat(u = jeck)


        class DogTrainer {

            fun train() {
                this.startTrain()
                dogs.forEach(fun (dog: Gsd) = dog.makeSound(loud = false))
            }

            /**
             * не должна использоваться извне
             */
            fun startTrain() {
                println("Voice!")
            }

            /**
             * не должна использоваться извне
             */
            val dogs = listOf(Gsd(), Gsd(), Gsd())
        }
        val viktor = DogTrainer()
        viktor.train()
        train(this = viktor)


//        Описать класс мопс (Pug)
//
//        Мопс может издавать звук - сопение. Звук он может издавать громко или не громко.
//        Мопс может дать лапу
//
//        Описать класс овчарка (Gsd)
//
//        Если овчарка сейчас на службе, то она издаёт звук "gav". Если не на службе,
//        то звук "mrrrr". Овчарка может издавать звуки громко или не громко. Может дать лапу
//        Если добрая- виляет хвостом, если злая то не виляет хвостом

        // Задание 1
        // Чихуахуа может быть в ярости или не в ярости
        // Когда чихуахуа в ярости, он издаёт звук "грррррр" иначе - звук "брррррр"
        // Определить объект для чихуахуа (Chihuahua) по имени Бобик
        // Изначально он должен быть НЕ в ярости
        // Попросить его издать звук
        // Сделать чтобы он пришёл в ярости
        // Снова попросить его издать звук

        // Задание 2
        // Определить класс для Чихуахуа. Сделать объекты для чихуаухуа по имени Бобик
        // и чихуаухуа по имени Шарик. Сначала звук должен издать Шарик. После этого Бобик должен
        // прийти в ярость и издать звук 2 раза

        // Задание 3
        // Описать код из задания 2 в программистских терминах. Для каждой строчки написать
        // пояснение в стиле: "Определение класса "Chihuahua", вызов функции "издать звук" и т.д."


    }
}