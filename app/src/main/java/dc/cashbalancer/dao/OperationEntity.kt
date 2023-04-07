package dc.cashbalancer.dao

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dc.cashbalancer.view.history.OperationType
import java.util.Date

@Entity(
    tableName = "operations",
    foreignKeys = [ForeignKey(
        entity = CardEntity::class,
        childColumns = ["cardID"],
        parentColumns = ["id"]
    )]
)
data class OperationEntity(
    var cardID: Int,
    var type: OperationType = OperationType.WITHDRAWAL,
    var category: String,
    var sum: Double,
    @TypeConverters(TimestampConverter::class)
    var date: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}