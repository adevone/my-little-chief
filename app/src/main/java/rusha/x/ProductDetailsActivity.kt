package rusha.x

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details_activity.*

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details_activity)

        val product = Product(
            name = "Огурец",
            price = 50.0,
            unit = "шт."
        )

        nameView.text = product.name
        priceView.text = "${product.price}/${product.unit}"
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "С возвращением!", Toast.LENGTH_LONG).show()
    }
}