package rusha.x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.put
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.android.synthetic.main.edit_recipe_activity.*
import kotlinx.android.synthetic.main.edit_recipe_ingredient.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class EditRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_recipe_activity)

        val json = Json {
            ignoreUnknownKeys = true
        }
        val httpClient = HttpClient(OkHttp)
        save.setOnClickListener {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch(block = {
                val createOrEditRecipe = CreateOrEditRecipe(
                    name = nameRecipeEdit.text.toString(),
                    portionsCount = 1,
                    ingredient = listOf()
                )
                val createOrEditRecipeJson = json.stringify(
                    CreateOrEditRecipe.serializer(),
                    createOrEditRecipe
                )
                httpClient.put<Unit>("http://10.0.2.2:9999/recipe") {
                    contentType(ContentType.Application.Json)
                    body = createOrEditRecipeJson
                }
                finish()
            })
        }

        val ingredients: List<CreateOrEditRecipe.Ingredient> = listOf(
            CreateOrEditRecipe.Ingredient(
                countInRecipe = 3.0,
                product = CreateOrEditProductByName(
                    name = "trupDevstvenici",
                    price = 5.0,
                    unit = "u."
                )
            ),
            CreateOrEditRecipe.Ingredient(
                countInRecipe = 5.0,
                product = CreateOrEditProductByName(
                    name = "nozgik",
                    price = 1.0,
                    unit = "u."
                )
            )
        )

        val ingredientViewAdapter = EditRecipeIngredientsAdapter()
        ingredientViewAdapter.ingredientToAdopt = ingredients
        editRecipeIngredientsView.adapter = ingredientViewAdapter
    }
}

class EditRecipeIngredientsAdapter : RecyclerView.Adapter<EditRecipeIngredientsAdapter.IngredientViewHolder>() {

    var ingredientToAdopt: List<CreateOrEditRecipe.Ingredient> = emptyList()
    override fun getItemCount(): Int {
        return ingredientToAdopt.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.edit_recipe_ingredient,
            parent,
            false
        )
        return IngredientViewHolder(containerView = view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredientOnPosition = ingredientToAdopt.get(index = position)
        holder.bind(ingredient = ingredientOnPosition)
    }

    class IngredientViewHolder(
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {
        fun bind(ingredient: CreateOrEditRecipe.Ingredient) {
            containerView.addIngredient.setText(ingredient.product.name)
            containerView.ingredientCount.setText(ingredient.countInRecipe.toString())
        }
    }
}
