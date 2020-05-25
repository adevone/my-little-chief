package rusha.x

class Basket(
    val items: List<Item>
) {
    class Item(
        val count: Double,
        val product: Product
    )
}