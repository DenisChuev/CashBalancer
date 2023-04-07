package dc.cashbalancer.view.history

import java.util.Date

data class Operation(
    var cardID: Int,
    var type: OperationType,
    var category: String,
    var sum: Double,
    var date: Date
)