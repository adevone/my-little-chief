package rusha.x

import org.jetbrains.annotations.TestOnly

class Recipe(
    val name: String,
    val portionsCount: Int,
    val ingredients: List<Ingredient>
) {
    data class Ingredient(
        val count: Double,
        val product: Product
    )
}

