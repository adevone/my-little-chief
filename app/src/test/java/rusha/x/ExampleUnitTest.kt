package rusha.x

import org.junit.Test

class ExampleUnitTest {

    @Test
    fun test() {

        val recipe = Recipe(
            name = "DillDevil",
            portionsCount = 1,
            ingredients = listOf(
                Recipe.Ingredient(
                    count = 3.0,
                    product = Product(
                        name = "trupDevstvenici",
                        price = 5.0,
                        unit = "u."
                    )
                ),
                Recipe.Ingredient(
                    count = 5.0,
                    product = Product(
                        name = "nozgik",
                        price = 1.0,
                        unit = "u."
                    )
                )
            )
        )

        val basket = Basket(
            items = listOf(
                Basket.Item(
                    count = 2.0,
                    product = Product(
                        name = "cucumber",
                        price = 50.0,
                        unit = "u."
                    )
                ),
                Basket.Item(
                    count = 3.0,
                    product = Product(
                        name = "milk",
                        price = 30.0,
                        unit = "lt."
                    )
                ),
                Basket.Item(
                    count = 5.0,
                    product = Product(
                        name = "tvorog",
                        price = 15.0,
                        unit = "kg."
                    )
                )
            )
        )

        val basketItems: List<Basket.Item> = basket.items

        val firstItem = basketItems.get(0)
        println(firstItem.product.name)

        // Cucumber (2.0 shtuk) - 100.0
        basketItems.forEach { item ->
            println("${item.product.name} (${item.count} ${item.product.unit}) - ${item.count * item.product.price}")
        }

        println("${recipe.name} (${recipe.portionsCount} portions)")
        // Need 2.0 cucumber for 50.0
        recipe.ingredients.forEach { ingrendient ->
            println("""Need ${ingrendient.count} ${ingrendient.product.name} for ${ingrendient.product.price}""")
        }
    }
}





class Nursery {

    var animalInCell: Animal? = null
    fun put(animal: Animal) {
        animalInCell = animal
    }

    fun give(): Animal? {
        val animalInHands = animalInCell
        animalInCell = null
        return animalInHands
    }
}


class DogsNursery {

    var animalInCell: Dog? = null
    fun put(animal: Dog) {
        animalInCell = animal
    }

    fun give(): Dog? {
        val animalInHands = animalInCell
        animalInCell = null
        return animalInHands
    }
}

class CatsNursery {

    var animalInCell: Cat? = null
    fun put(animal: Cat) {
        animalInCell = animal
    }

    fun give(): Cat? {
        val animalInHands = animalInCell
        animalInCell = null
        return animalInHands
    }
}

abstract class Animal {
    fun eat() {
        println("hrum")
    }
}

class Dog : Animal() {
    fun bark() {
        println("gav")
    }
}

class Cat : Animal() {
    fun meow() {
        println("nya")
    }
}

fun giveAnimalFromNursery() {

    val dogsNursery = Nursery()
    dogsNursery.put(Dog())

    val dog = dogsNursery.give()!!
    dog.eat()
//    dog.bark()


    val catsNursery = CatsNursery()
    catsNursery.put(Cat())
}


