package rusha.x

interface BasketView {
    fun displayItems(items: List<Basket.Item>)
    fun toProductDetails(product: Product)
}

class BasketPresenter(
    val view: BasketView
) {
    fun onEnter() {
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
        view.displayItems(basket.items)
    }

    fun onItemClick(item: Basket.Item) {
        view.toProductDetails(item.product)
    }
}