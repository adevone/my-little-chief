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

    @Test
    fun cats() {
        val barbara = MistressKat(cats = listOf(Maykun(), UndergroundKitty()))
        barbara.observeTheRegime()

        val lola = Nurse(cats = listOf(Maykun(), UndergroundKitty()))
        lola.giveFood()

        val kleo = Sphinx()
        kleo.pat()
        kleo.clean()
        kleo.pat()

        val vaska = UndergroundKitty()
        vaska.clean()
        vaska.eat()
        vaska.pat()

        val king = Maykun()
        king.clean()
        king.eat()
        king.pat()
    }
}

interface Cat : PrettyKitty, GluttonyCat


class Maykun : Cat {
    var inLite: Boolean = true
    override fun clean() {
        inLite = false
        println("hsss")
    }

    override fun eat() {
        inLite = true
        println("nya")
    }

    override fun pat() {
        println(if (!inLite) "czarap" else "mrrr")
    }
}


class Sphinx : Cat {
    var happyLvl = 3
    override fun pat() {
        if (happyLvl <= 6) {
            happyLvl = happyLvl + 1
        }
        println("mrrr")
    }


    override fun eat() {
        if (happyLvl <= 5) {
            happyLvl = happyLvl + 2
        } else if (happyLvl <= 6) {
            happyLvl = happyLvl + 1
        }
        println(" nya")
    }

    override fun clean() {
        when {
            happyLvl >= 2 -> {
                happyLvl = happyLvl - 3
            }
            happyLvl > 1 -> {
                happyLvl = happyLvl - 2
            }
            happyLvl == 0 -> {
                happyLvl = happyLvl - 1
            }
        }
    }
}


class UndergroundKitty : Cat {
    override fun eat() {
        println("nya")
    }

    override fun clean() {
        println("IRememberThisBitch")
    }

    override fun pat() {
        println("mrrr")
    }
}

class Nurse(
    private val cats: List<GluttonyCat>
) {
    fun giveFood() {
        cats.forEach(fun(gluttonyCat: GluttonyCat) {
            gluttonyCat.eat()
        })
        println("GluttonyCat")
    }
}

class MistressKat(
    private val cats: List<PrettyKitty>
) {
    private var morning = true
    private var day = false
    private var evening = false

    fun setMorning() {
        morning = true
        day = false
        evening = false
    }

    fun setDay() {
        morning = false
        day = true
        evening = false
    }

    fun setEvening() {
        morning = false
        day = false
        evening = true
    }

    private fun giveFood() {
        startGiveFood()
        cats.forEach(fun(prettyKitty: PrettyKitty) {
            prettyKitty.eat()
        })
    }

    private fun startGiveFood() {
        println("ks ks ks ks ska")
    }

    private fun pat() {
        println("prettyKitty")
    }

    private fun clean() {
        cats.forEach(fun(prettyKitty: PrettyKitty) {
            prettyKitty.clean()
        })
    }

    fun observeTheRegime() {
        when {
            morning -> giveFood()
            day -> {
                giveFood()
                pat()
            }
            evening -> {
                giveFood()
                clean()
            }
        }

    }
}


//Хозяин может иметь несколько кошек.Утром кормит кошек. Днем он их кормит и гладит, вечером - кормит.
//Когда наступает пятница 13е - он их моет.

//Кормилица может кормить всех кошек, которые у нее есть.

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