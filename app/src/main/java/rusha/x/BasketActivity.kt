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
import kotlinx.android.synthetic.main.basket_activity.*
import kotlinx.android.synthetic.main.basket_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.intellij.lang.annotations.Language

class BasketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_activity)

        toRecipeButton.setOnClickListener {
            val startRecipeDetailsIntent = Intent(this, RecipeDetailsActivity::class.java)
            startActivity(startRecipeDetailsIntent)
        }

        /**
         * Создаём сервис сериализации и десериализации JSON,
         * сконфигурированный стабильной конфигурацией
         *
         * Стабильная конфигуация - это то, что в библиотеке kotlinx.serialization
         * уже стабильно работает
         *
         * Объект такого типа можно использовать для сколько угодно многих JSON-ов
         */
        val json = Json {
            ignoreUnknownKeys = true
        }

        val httpClient = HttpClient(OkHttp)
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch(block = {
            val basketJson = httpClient.get<String>(
                "https://gist.githubusercontent.com/adevone/" +
                        "440aeb6101f17075c79282a3f054a6ed/raw/" +
                        "ca849ec514eea9f5a2b2a3db8239c04f97df0b96/basket.json"
            )
            /**
             * Получаем корзину из JSON, написанного в basketJson
             */
            val basket: Basket = json.parse(
                /**
                 * Для каждого типа, на котором стоит аннотация @Serializable,
                 * генерируется функция serializer()
                 */
                deserializer = Basket.serializer(),
                string = basketJson
            )

            val itemViewAdapter = BasketListAdapter()
            itemViewAdapter.itemsToAdopt = basket.items
            basketRecyclerView.adapter = itemViewAdapter
        })

    }
}

class BasketListAdapter : RecyclerView.Adapter<BasketListAdapter.ItemViewHolder>() {

    var itemsToAdopt: List<Basket.Item> = listOf()
    override fun getItemCount(): Int {
        return itemsToAdopt.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BasketListAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context) // была опечатка. LayoutInfla-y-ter
        val view = inflater.inflate(
            R.layout.basket_item,
            parent,
            false
        )
        return ItemViewHolder(containerView = view)
    }

    override fun onBindViewHolder(holder: BasketListAdapter.ItemViewHolder, position: Int) {
        val itemOnPosition = itemsToAdopt.get(index = position)
        holder.bind(item = itemOnPosition)
    }

    class ItemViewHolder(
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {
        fun bind(item: Basket.Item) {
            containerView.basketItemView.text = item.product.name
            containerView.basketCountView.text = item.count.toString()
        }
    }
}


























