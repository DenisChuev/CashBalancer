package dc.cashbalancer.dao

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    var category: String,
    var sum: Double,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}