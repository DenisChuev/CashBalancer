package dc.cashbalancer.view.history

data class Operation(
    var cardID: Int,
    var type: OperationType,
    var category: String,
    var sum: Double
)