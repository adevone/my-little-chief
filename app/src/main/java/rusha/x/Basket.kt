package rusha.x

class Basket(
    val items: List<Item>
) {
    data class Item(
        override val count: Double,
        override val product: Product
    ) : CountableWithProduct
}

interface CountableWithProduct : Countable {
    val product: Product
}

interface Countable {
    val count: Double
}