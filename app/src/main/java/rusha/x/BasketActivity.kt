package rusha.x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.basket_activity.*
import kotlinx.android.synthetic.main.basket_item.view.*

class BasketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_activity)

        val basket = Basket(
            items = listOf(
                Basket.Item(
                    count = 2.0,
                    product = Product(
                        name = "cucumber",
                        price = 50.0,
                        unit = "u."
                    )
                ),
                Basket.Item(
                    count = 3.0,
                    product = Product(
                        name = "milk",
                        price = 30.0,
                        unit = "lt."
                    )
                ),
                Basket.Item(
                    count = 5.0,
                    product = Product(
                        name = "tvorog",
                        price = 15.0,
                        unit = "kg."
                    )
                )
            )
        )

        val itemViewAdapter = BasketListAdapter()
        itemViewAdapter.itemsToAdopt = basket.items
        basketRecyclerView.adapter = itemViewAdapter
    }

}

class BasketListAdapter : RecyclerView.Adapter<BasketListAdapter.ViewHolder>() {
    var itemsToAdopt: List<Basket.Item> = listOf()
    override fun getItemCount(): Int {
        return itemsToAdopt.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasketListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.basket_item,
            parent,
            false
        )
        return ViewHolder(containerView = view)
    }

    override fun onBindViewHolder(holder: BasketListAdapter.ViewHolder, position: Int) {
        val itemOnPosition = itemsToAdopt.get(index = position)
        holder.bind(item = itemOnPosition)
    }

    class ViewHolder(
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {
        fun bind(item: Basket.Item) {
            containerView.basketItemView.text = item.product.name
            containerView.basketCountView.text = item.product.price.toString()
        }
    }
}