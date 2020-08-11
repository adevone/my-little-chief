package rusha.x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.recipe_list_activity.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import org.kodein.di.instance
import summer.android.SummerFragment

interface RecipeListView {
    var recipes: List<Recipe>
    var isRefreshing: Boolean
    val goToEditRecipe: () -> Unit
    val goToRecipeDetails: (recipe: Recipe) -> Unit
}

class RecipeListPresenter : BasePresenter<RecipeListView>() {

    private val json by di.instance<Json>()
    private val httpClient by di.instance<HttpClient>()

    override val viewProxy = object : RecipeListView {
        override var recipes by state({ it::recipes }, initial = emptyList())
        override var isRefreshing by state({ it::isRefreshing }, initial = false)
        override val goToEditRecipe = event { it.goToEditRecipe }.doExactlyOnce()
        override val goToRecipeDetails = event { it.goToRecipeDetails }.doExactlyOnce()
    }

    fun onRefresh() {
        updateRecipes()
    }

    fun onResume() {
        updateRecipes()
    }

    private fun updateRecipes() {
        viewProxy.isRefreshing = true
        launch {
            val recipesJson = httpClient.get<String>(
                "http://10.0.2.2:9999/recipe/all"
            )
            val allRecipes = json.parse(
                deserializer = Recipe.serializer().list,
                string = recipesJson
            )
            viewProxy.recipes = allRecipes
            viewProxy.isRefreshing = false
        }
    }

    fun onAddRecipeClick() {
        viewProxy.goToEditRecipe()
    }

    fun onRecipeClick(recipe: Recipe) {
        viewProxy.goToRecipeDetails(recipe)
    }
}

class RecipeListFragment : SummerFragment(R.layout.recipe_list_activity), RecipeListView {

    private val presenter by bindPresenter { RecipeListPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addRecipeButton.setOnClickListener {
            presenter.onAddRecipeClick()
        }

        swipeRefreshLayout.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override var recipes: List<Recipe> by didSet {
        val recipesViewAdapter = RecipesListAdapter(presenter)
        recipesViewAdapter.recipesToAdopt = recipes
        recipesView.adapter = recipesViewAdapter
    }

    override var isRefreshing: Boolean by didSet {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }

    override val goToEditRecipe = {
        findNavController().navigate(
            RecipeListFragmentDirections.actionRecipeListFragmentToEditRecipeFragment()
        )
    }

    override val goToRecipeDetails = { recipe: Recipe ->
        findNavController().navigate(
            RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailsFragment(recipe)
        )
    }

}

class RecipesListAdapter(
    private val presenter: RecipeListPresenter
) : RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {
    var recipesToAdopt: List<Recipe> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.recipe_list_item,
            parent,
            false
        )
        return ViewHolder(cellView = view)
    }

    override fun getItemCount(): Int {
        return recipesToAdopt.size
    }

    override fun onBindViewHolder(holder: RecipesListAdapter.ViewHolder, position: Int) {
        val recipeOnPosition = recipesToAdopt.get(index = position)
        holder.bind(recipe = recipeOnPosition)
    }

    inner class ViewHolder(
        val cellView: View
    ) : RecyclerView.ViewHolder(cellView) {

        fun bind(recipe: Recipe) {
            cellView.recipesItem.text = recipe.name
            cellView.setOnClickListener {
                presenter.onRecipeClick(recipe)
            }
        }
    }
}
