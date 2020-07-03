package rusha.x

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrEditRecipe(
    val id: Int? = null,
    val name: String,
    val portionsCount: Int,
    val ingredient: List<Ingredient>
) {
    @Serializable
    data class Ingredient(
        val countInRecipe: Double,
        val product: CreateOrEditProductByName
    )
}

@Serializable
data class CreateOrEditProductByName(
    val id: Int? = null,
    val name: String,
    val price: Double,
    val unit: String
)