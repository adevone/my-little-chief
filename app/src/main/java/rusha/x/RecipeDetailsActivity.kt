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

/**
 * Преобразует список [ingredientsToAdopt] в отображение списка в [ingredientsView]
 */
class RecipeListAdapter : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    /**
     * Список который адаптирует [RecipeListAdapter]
     */
    var ingredientsToAdopt: List<Recipe.Ingredient> = listOf()

    /**
     * Сделать так чтобы [onBindViewHolder] не вызвалась с позицией (position)
     * которой нет в списке
     */
    override fun getItemCount(): Int {
        return ingredientsToAdopt.size
    }

    /**
     * Создать ячейку привязанную к RecyclerView ([parent])
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Получить [LayoutInflater] из текущего контекста
        // который можно получить из любого отображения в этом контексте
        val inflater = LayoutInflater.from(parent.context)

        // "Надуть" [R.layout.recipe_details_item] так чтобы потом поместить его в [parent]
        // Не нужно привязывать его к [parent] сразу. Поэтому 3 аргумет = false
        val view = inflater.inflate(
            R.layout.recipe_details_item,
            parent,
            false
        )

        // Создать ячейку у которой в качестве отображения будет значение переменной [view]
        return ViewHolder(containerView = view)
    }

    /**
     * Заполнить ячейку [holder] элементом из списка [ingredientsToAdopt]
     * по позиции [position]
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Получить ингридиент по позиции [position]
        val ingredientOnPosition = ingredientsToAdopt.get(index = position)

        // Попросить ячейку заполниться этим игридиентом
        holder.bind(ingredient = ingredientOnPosition)
    }

    /**
     * Ячейка списка
     */
    class ViewHolder(
        /**
         * Отображение ячейки (ConstraintLayout из recipe_details_item.xml)
         */
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        /**
         * Ячейка умеет сама себя заполнять
         */
        fun bind(ingredient: Recipe.Ingredient) {
            containerView.nameItemView.text = ingredient.product.name
            containerView.countView.text = ingredient.count.toString()
        }
    }
}