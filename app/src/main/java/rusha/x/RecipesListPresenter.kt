package rusha.x

interface RecipesListView {
    fun displayRecipes(recipes: List<Recipe>)
    fun toRecipeDetails(recipe: Recipe)
    fun toAddRecipe()
}

class RecipesListPresenter(
    val view: RecipesListView
) {
    fun onEnter() {

        val funnyLife = Recipe(
            name = "recipeFunnyLife",
            portionsCount = 1,
            ingredients = listOf(
                Recipe.Ingredient(
                    count = 4.0,
                    product = Product(
                        name = "crazyAction",
                        price = 21.0,
                        unit = "stad."
                    )
                ),
                Recipe.Ingredient(
                    count = 1.0,
                    product = Product(
                        name = "drinkAndSmoke",
                        price = 00.0,
                        unit = "l."
                    )
                ),
                Recipe.Ingredient(
                    count = 00.0,
                    product = Product(
                        name = "manymanySleep",
                        price = 50.0,
                        unit = "time."
                    )
                )
            )
        )

        val dillDevil = Recipe(
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

        view.displayRecipes(listOf(dillDevil, funnyLife))
    }

    fun onRecipeClick(recipe: Recipe) {
        view.toRecipeDetails(recipe)
    }

    fun onAddRecipeClick() {
        view.toAddRecipe()
    }

    fun onRemoveRecipeClick() {

    }
}