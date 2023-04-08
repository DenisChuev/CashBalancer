package dc.cashbalancer.view.history

import dc.cashbalancer.dao.OperationEntity

data class HistoryItem(
    val operationDate: String,
    val operationSum: Double,
    val operations: List<OperationEntity>
)