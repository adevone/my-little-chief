package rusha.x

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.product_details_fragment.*

class ProductDetailsViewModel : ViewModel() {

    val nameLiveData = MutableLiveData<String>("")
    val formattedPriceLiveData = MutableLiveData<String>("")

    data class ShowWelcomeParams(val text: String)

    val showWelcomeLiveData = MutableLiveData<ShowWelcomeParams?>(null)

    data class GoToBasketParams(val basket: Basket)

    val goToBasketLiveEvent = SingleLiveEvent<GoToBasketParams>()

    init {
        val product = Product(
            id = 0,
            name = "Огурец",
            price = 50.0,
            unit = "шт."
        )
        nameLiveData.value = product.name
        formattedPriceLiveData.value = "${product.price}/${product.unit}"
    }

    fun onResume() {
        showWelcomeLiveData.value = ShowWelcomeParams(text = "Привет!")
        showWelcomeLiveData.value = null
    }

    fun onGoToBasketClick() {
        goToBasketLiveEvent.value = GoToBasketParams(
            basket = Basket(
                id = "1",
                items = emptyList()
            )
        )
    }
}

class ProductDetailsFragment : Fragment(R.layout.product_details_fragment) {
    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basketView.setOnClickListener {
            viewModel.onGoToBasketClick()
        }

        viewModel.nameLiveData.observe(viewLifecycleOwner, Observer { name ->
            nameView.text = name
        })

        viewModel.formattedPriceLiveData.observe(viewLifecycleOwner, Observer { formattedPrice ->
            priceView.text = formattedPrice
        })

        viewModel.showWelcomeLiveData.observe(viewLifecycleOwner, Observer { params ->
            if (params != null) {
                Toast.makeText(requireContext(), params.text, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.goToBasketLiveEvent.observe(viewLifecycleOwner, Observer { basket ->
            println(basket)
            TODO() // startActivity (Intent(requireContext(), BasketFragment::class.java))
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}