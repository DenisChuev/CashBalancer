package dc.cashbalancer.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    var name: String,
    var amount: Double,
    var color: String = "#4f34eb",
    var currency: String = "Руб"
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
