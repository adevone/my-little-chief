package rusha.x

import org.junit.Test

class RenewKnowledgeTest {

    fun sum(a: Int, b: Int) = a + b

    fun sum1(a: Int, b: Int): Int {
        return a + b
    }

    @Test
    fun test() {

        class Chihua {
            var angryLvl = 0
            fun givePaw()=
                if (angryLvl==0) println ("GivsApaw")
                else          println("kus`")
            fun makeSound(loud:Boolean) = when {
                angryLvl == 0 -> println(if (!loud)"rrr" else "RRR")
                angryLvl == 1 -> println(if (!loud)"grrr" else "GRRR")
                angryLvl == 2 -> println(if(!loud)" GAV!" else "killedGav!")
                else -> println( " AllahAkbar")
            }
        }

        val alisa = Chihua()
        val gosha = Chihua()
        alisa.makeSound(loud = false)
        alisa.givePaw()
        gosha.angryLvl = 2
        gosha.givePaw()
        gosha.makeSound(loud = true)

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