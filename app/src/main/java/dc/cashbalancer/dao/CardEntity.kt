package dc.cashbalancer.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import dc.cashbalancer.R

@Entity(tableName = "cards")
data class CardEntity(
    var name: String,
    var amount: Double,
    var color: Int = R.color.default_card_color,
    var currency: String = "Руб"
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
