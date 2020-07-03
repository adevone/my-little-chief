package rusha.x

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.recipe_list_activity.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json

class RecipeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_list_activity)

        addRecipeButton.setOnClickListener {
            startActivity(Intent(this, EditRecipeActivity::class.java))
        }

        swipeRefreshLayout.setOnRefreshListener {
            refreshOnRecipeList()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshOnRecipeList()
    }

    fun refreshOnRecipeList() {
        swipeRefreshLayout.isRefreshing = true

        val json = Json {
            ignoreUnknownKeys = true
        }
        val httpClient = HttpClient(OkHttp)
        val recipesViewAdapter = RecipesListAdapter()

        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch(block = {
            val recipesJson = httpClient.get<String>(
                "http://10.0.2.2:9999/recipe/all"
            )
            val allRecipes = json.parse(
                deserializer = Recipe.serializer().list,
                string = recipesJson
            )
            recipesViewAdapter.recipesToAdopt = allRecipes
            recipesView.adapter = recipesViewAdapter

            swipeRefreshLayout.isRefreshing = false
        })
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
            R.layout.recipe_list_item,
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
        holder.bind(recipe = recipeOnPosition)
    }
}

class RecipesViewHolder(
    val cellView: View
) : RecyclerView.ViewHolder(cellView) {
    fun bind(recipe: Recipe) {
        cellView.recipesItem.text = recipe.name
    }
}
