package dc.cashbalancer.view.cards

data class Card(
    var name: String,
    var amount: Double,
    var color: String = "#4f34eb",
    var currency: String = "Руб"
)
