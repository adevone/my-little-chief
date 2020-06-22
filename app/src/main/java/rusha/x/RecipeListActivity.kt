package rusha.x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_list_activity.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class RecipeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_list_activity)

        val recipes = listOf(
            Recipe(
                id = "1",
                name = "heartChinesMan",
                portionsCount = 200,
                ingredients = listOf()
            ),
            Recipe(
                id = "1",
                name = "killFamilyChinesManStupid",
                portionsCount = 200,
                ingredients = listOf()
            )
        )

        val recipesViewAdapter = RecipesListAdapter()
        recipesViewAdapter.recipesToAdopt = recipes
        recipeView.adapter = recipesViewAdapter
    }
}

class RecipesListAdapter : RecyclerView.Adapter<RecipesViewHolder>() {
    var recipesToAdopt: List<Recipe> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.basket_activity,
            parent,
            false
        )
        return RecipesViewHolder(cellView = view)

    }

    override fun getItemCount(): Int {
        return recipesToAdopt.size
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipeOnPosition = recipesToAdopt.get(index = position)
        holder.bind(recipes = recipeOnPosition)
    }
}

class RecipesViewHolder(
    val cellView: View
) : RecyclerView.ViewHolder(cellView) {
    fun bind(recipes: Recipe) {
        cellView.recipesItem.text = recipes.toString()
    }
}
