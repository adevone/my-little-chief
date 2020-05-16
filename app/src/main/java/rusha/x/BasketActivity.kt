package rusha.x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.basket_item.view.*

class BasketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_activity)
    }
}


//class BasketAdapter() : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(
//            R.layout.basket_item,
//            parent,
//            false
//        )
//        return ViewHolder(containerView = view)
//    }
//
//    override fun getItemCount(): Int {
//        return adapterItem.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val itemOnPosition = adapterItem.get(index = position)
//        holder.bind(item = itemOnPosition)
//    }
//
//    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
//        fun bind(item: Basket.Item) {
//            containerView.basketItemView.text = item.product.name
//            containerView.basketCountView.text = item.count.toString()
//        }
//    }
//}