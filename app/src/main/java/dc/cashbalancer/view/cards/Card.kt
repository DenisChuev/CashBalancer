package dc.cashbalancer.view.cards

import dc.cashbalancer.R

data class Card(
    var name: String,
    var amount: Double,
    var color: Int = R.color.default_card_color,
    var currency: String = "Руб"
)
