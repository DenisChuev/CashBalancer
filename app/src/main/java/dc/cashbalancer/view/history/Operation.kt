package dc.cashbalancer.view.history

data class Operation(
    var cardID: Int,
    var category: String,
    var sum: Double
)