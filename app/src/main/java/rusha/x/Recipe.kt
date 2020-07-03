package rusha.x

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val name: String,
    val portionsCount: Int,
    val ingredients: List<Ingredient>
) {
    @Serializable
    data class Ingredient(
        val countInRecipe: Double,
        val product: Product
    )
}