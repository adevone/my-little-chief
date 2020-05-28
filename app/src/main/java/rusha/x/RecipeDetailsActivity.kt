package rusha.x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// импортировали класс АппКомпатАктивити, чтобы его использовать здесь (в этом файле)
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.recipe_details_activity.*
import kotlinx.android.synthetic.main.recipe_details_item.view.*

// класс реципеДетАкт наследует все свойства аппКомпатАктивити т.е все его переменные и функции
class RecipeDetailsActivity : AppCompatActivity() {
    //мы переопределяем onCreate, чтобы сделать доп. дейсвия при создании активити
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // задаем верстку при помощи функции setContentView
        setContentView(R.layout.recipe_details_activity)

        // создаем список ингридиентов c помощью listOf
        // и кладём его в переменную funnyLifeIngredients
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

        //создание адаптера-ingredientsViewAdapter, кладем обьект(который ведет себя так как мы описали
        // в классе RecipeListAdapter   типа RecipeListAdapter в эту переменную
        //правила работы которого описаны в RecipeListAdapter
        val ingredientsViewAdapter = RecipeListAdapter()

        //говорим какой список ингр. будет отображать тот адаптер который описали выше. А в ingredientsToAdopt
        //лежит потенциальный список ингр.
        ingredientsViewAdapter.ingredientsToAdopt = funnyLifeIngredients

        //ingredientsView- это название и обращение к списку ингриндиентов в xml
        //это переменная,в которой лежит описание того как отображаются ячейки списка
        //в переменную ingredientsView мы записываем тот адаптер который создали выше
        ingredientsView.adapter = ingredientsViewAdapter
    }
}

/**
 * Преобразует список [ingredientsToAdopt] в отображение списка в [ingredientsView]
 */
//класс наследуется от класса RecyclerView
// в скобочках говрится "класс IngredientViewHolder это ячейка для класса RecipeListAdapter
//RecipeListAdapter это адаптер для того внутри <...>
class RecipeListAdapter : Adapter<IngredientViewHolder>() {

    /**
     * Список который адаптирует [RecipeListAdapter]
     */
    //ingredientsToAdopt это список который отображает RecipeListAdapter
    //"в ingredientsToAdopt изначально лежит пустой список(listOf)
    var ingredientsToAdopt: List<Recipe.Ingredient> = listOf()

    /**
     * Создать ячейку для RecyclerView, лежащего в [parent]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {

        /**
        Получить [LayoutInflater] из текущего контекста
        который можно получить из любого отображения в этом контексте
         */
        // inflater переменная содержит в себе LayoutInflater, который мы получили из context,
        // который находится в parent
        val inflater = LayoutInflater.from(parent.context)

        /**
        "Надуть" [R.layout.recipe_details_item] так чтобы потом поместить его в [parent]
        Не нужно привязывать его к [parent] сразу. Поэтому 3 аргумет = false
         */
        // обьявляем переменную вью которая является результатом вызова функции inflate
        val view = inflater.inflate(
            /**
             * R - ресурсы (это то, что может меняться в зависимости от того, на каком
             * устройсте запущено приложение. Например, мы можем загрузить в приложение
             * иконки разного качества. В зависимости от плотности точек экрана, будут
             * использоваться разные иконки)
             *
             * layout - папка с файлами разметки. Можно использовать разные разметки для
             * разных экранов или отдельную для вертикального и горизонтального положений
             * экрана
             *
             * recipe_details_item - название файла надуваемой разметки (верстки)
             */
            R.layout.recipe_details_item,
            /**
             * В какое отображение будет помещено отображение, полученное в результате
             * надувания вёрстки
             */
            parent,
            /**
             * Нужно ли сразу прикрепить надутую вёрстку к parent
             */
            false
        )

        /**
         * Создать держатель отображения у которого в качестве отображения
         * будет значение переменной [view]
         * Держатель отображения будет результатом вызова функции [onCreateViewHolder].
         * RecyclerView вызовет эту функцию и положит ячейку, полученную в результате
         * в пул ячеек, некоторые из которых некоторые сразу заполнит и отобразит пользователю,
         * а некоторые - будет держать "на изготове"
         */
        return IngredientViewHolder(cellView = view)
    }

    /**
     * Попросить держатель отображения ячейки [holder] заполнить отображение внутри него
     * элементом из списка [ingredientsToAdopt] по позиции [position]
     */
    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        /**
         * Получить ингридиент по позиции [position]
         */
        val ingredientOnPosition = ingredientsToAdopt.get(index = position)

        /**
         * Попросить ячейку заполниться этим игридиентом
         */
        holder.bind(ingredient = ingredientOnPosition)
    }

    /**
     * Сделать так чтобы [onBindViewHolder] не вызвалась с позицией (position)
     * которой нет в списке
     */
    override fun getItemCount(): Int {
        return ingredientsToAdopt.size
    }
}

/**
 * Ячейка списка
 */
class IngredientViewHolder(
    /**
     * Отображение ячейки (ConstraintLayout из recipe_details_item.xml)
     */
    val cellView: View
) : RecyclerView.ViewHolder(cellView) {

    /**
     * Ячейка умеет сама себя заполнять
     */
    fun bind(ingredient: Recipe.Ingredient) {
        cellView.nameItemView.text = ingredient.product.name
        cellView.countView.text = ingredient.count.toString()
    }
}