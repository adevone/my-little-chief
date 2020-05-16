package rusha.x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_details_activity.*
import kotlinx.android.synthetic.main.recipe_details_item.view.*

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_details_activity)

        val funnyLifeIngredients = listOf(
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

        val ingredientsViewAdapter = RecipeListAdapter()
        ingredientsViewAdapter.ingredientsToAdopt = funnyLifeIngredients

        ingredientsView.adapter = ingredientsViewAdapter
    }
}

class RecipeListAdapter : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    var ingredientsToAdopt: List<Recipe.Ingredient> = listOf()

    override fun getItemCount(): Int {
        return ingredientsToAdopt.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_details_item,
            parent,
            false
        )
        return ViewHolder(containerView = view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredientOnPosition = ingredientsToAdopt.get(index = position)
        holder.bind(ingredient = ingredientOnPosition)
    }

    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(ingredient: Recipe.Ingredient) {
            containerView.nameItemView.text = ingredient.product.name
            containerView.countView.text = ingredient.count.toString()
        }
    }
}