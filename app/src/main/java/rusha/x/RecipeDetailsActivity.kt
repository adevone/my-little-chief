package rusha.x

// импортировали класс АппКомпатАктивити, чтобы его использовать здесь (в этом файле)

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.recipe_details_activity.*
import kotlinx.android.synthetic.main.recipe_details_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

// класс реципеДетАкт наследует все свойства аппКомпатАктивити т.е все его переменные и функции
// TODO для каждого RecipeDetailsActivity, являющегося AppCompatActivity
class RecipeDetailsActivity : AppCompatActivity() {
    // мы переопределяем onCreate, чтобы сделать доп. действия при создании активити
    // TODO определяем, что в отличие от других AppCompatActivity, RecipeDetailsActivity onCreate
    override fun onCreate(
        // TODO , принимая savedInstanceState, являющееся необязательным Bundle,
        savedInstanceState: Bundle?
    ) {
        // TODO сначала делает onCreate как AppCompatActivity с savedInstanceState
        super.onCreate(savedInstanceState)

        // задаем верстку при помощи функции setContentView
        // TODO после делает setContentView, передавая ему в качестве
        // TODO layoutResID recipe_details_activity из layout из R
        setContentView(R.layout.recipe_details_activity)

        val json = Json {
            ignoreUnknownKeys = true
        }

        val httpClient = HttpClient(OkHttp)

        //создание адаптера-ingredientsViewAdapter, кладем обьект(который ведет себя так как мы описали
        // в классе IngredientsListAdapter   типа IngredientsListAdapter в эту переменную
        //правила работы которого описаны в IngredientsListAdapter
        //TODO ingredientsViewAdapter будет получен в результате создания IngredientsListAdapter
        val ingredientsViewAdapter = IngredientsListAdapter()

        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch(block = {
            val recipeJson = httpClient.get<String>(
                "https://gist.githubusercontent.com/adevone/" +
                        "440aeb6101f17075c79282a3f054a6ed/raw/" +
                        "ca849ec514eea9f5a2b2a3db8239c04f97df0b96/funnyLifeRecipe.json"
            )
            val funnyLifeRecipe = json.parse(
                deserializer = Recipe.serializer(),
                string = recipeJson
            )

            //говорим какой список ингр. будет отображать тот адаптер который описали выше. А в ingredientsToAdopt
            //лежит потенциальный список ингр.
            //TODO ingredientsToAdopt ingredientsViewAdapter(-a) это теперь тоже самое что и funnyLifeIngredients
            ingredientsViewAdapter.ingredientsToAdopt = funnyLifeRecipe.ingredients

            //ingredientsView- это название и обращение к списку ингриндиентов в xml
            //это переменная,в которой лежит описание того как отображаются ячейки списка
            //в переменную ingredientsView мы записываем тот адаптер который создали выше
            //TODO adapter ingredientsView тоже самое ingredientsViewAdapter
            ingredientsView.adapter = ingredientsViewAdapter
        })
    }
}

/**
 * Преобразует список [ingredientsToAdopt] в отображение списка в [ingredientsView]
 */
//класс наследуется от класса Adapter
// в скобочках говрится "класс IngredientViewHolder это ячейка для класса IngredientsListAdapter
//IngredientsListAdapter это адаптер для того внутри <...>
// TODO определяем что все IngredientsListAdapter являются RecyclerView.Adapter-ами для
// TODO которых VH (ViewHolder) - это IngredientViewHolder
class IngredientsListAdapter : RecyclerView.Adapter<IngredientViewHolder>() {

    /**
     * Список который адаптирует [IngredientsListAdapter]
     */
    //ingredientsToAdopt это список который отображает IngredientsListAdapter
    //"в ingredientsToAdopt изначально лежит пустой список(listOf)
    // TODO определяем что ingredientsToAdopt является списком из Recipe.Ingredient-ов
    // TODO который является пустым списком
    var ingredientsToAdopt: List<Recipe.Ingredient> = listOf()

    /**
     * Создать ячейку для RecyclerView, лежащего в [parent]
     */
    // TODO в отличие от других RecyclerView.Adapter, IngredientsListAdapter делает onCreateViewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup, // TODO для parent, который является ViewGroup
        viewType: Int // TODO и viewType, который является Int
    ): IngredientViewHolder { // TODO в результате отдавая что-то являющееся IngredientViewHolder-ом

        /**
        Получить [LayoutInflater] из текущего контекста
        который можно получить из любого отображения в этом контексте
         */
        // inflater переменная содержит в себе LayoutInflater, который мы получили из context,
        // который находится в parent
        // TODO определяем что inflater это LayoutInflater из context-а parent
        val inflater = LayoutInflater.from(parent.context)

        /**
        "Надуть" [R.layout.recipe_details_item] так чтобы потом поместить его в [parent]
        Не нужно привязывать его к [parent] сразу. Поэтому 3 аргумет = false
         */
        // обьявляем переменную вью которая является результатом вызова функции inflate
        // TODO определяем что view мы получим в резульате inflate inflater-а
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
            // TODO которому мы даём R.layout.recipe_details_item в качестве resource
            R.layout.recipe_details_item,
            /**
             * В какое отображение будет помещено отображение, полученное в результате
             * надувания вёрстки
             */
            // TODO parent в качесте root
            parent,
            /**
             * Нужно ли сразу прикрепить надутую вёрстку к parent
             */
            // TODO false в качестве attackToRoot
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
        // TODO отдаём то, что получим в результате создания IngredientViewHolder
        // TODO для которого cellView - это view
        return IngredientViewHolder(cellView = view)
    }

    /**
     * Попросить держатель отображения ячейки [holder] заполнить отображение внутри него
     * элементом из списка [ingredientsToAdopt] по позиции [position]
     */
    // TODO в отличие от других RecyclerView.Adapter-ов, IngredientsListAdapter делает onBindViewHolder
    override fun onBindViewHolder(
        holder: IngredientViewHolder, // TODO с holder, который является IngredientViewHolder
        position: Int // TODO и с position, который является Int
    ) {
        /**
         * Получить ингридиент по позиции [position]
         */
        // TODO определяем что ingredientOnPosition мы получим в результате получения элемента
        // TODO из списка ingredientsToAdopt по индексу, равному position
        val ingredientOnPosition = ingredientsToAdopt.get(index = position)

        /**
         * Попросить ячейку заполниться этим игридиентом
         */
        // TODO делаем bind у holder-а где ingredient - это то же, что и ingredientOnPosition
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
 * Держатель отображения ячейки
 */
class IngredientViewHolder(
    /**
     * Отображение ячейки (ConstraintLayout из recipe_details_item.xml)
     */
    val cellView: View
) : RecyclerView.ViewHolder(cellView) {

    /**
     * Держатель ячейки заполняет отображение ячейки данными
     */
    fun bind(ingredient: Recipe.Ingredient) {
        cellView.nameItemView.text = ingredient.product.name
        cellView.countView.text = ingredient.countInRecipe.toString()
    }
}