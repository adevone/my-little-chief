package rusha.x

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