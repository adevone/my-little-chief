package rusha.x

interface ProductDetailsView {
    fun displayProduct(product: Product)
}

class ProductDetailsPresenter(
    val view: ProductDetailsView
) {
    fun onEnter() {
        val product = Product(
            name = "Огурец",
            price = 50.0,
            unit = "шт."
        )
        view.displayProduct(product)
    }
}