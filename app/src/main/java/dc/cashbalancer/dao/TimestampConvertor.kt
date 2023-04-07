package dc.cashbalancer.dao

import android.icu.text.DateFormat
import android.icu.text.DateFormat.getDateTimeInstance
import androidx.room.TypeConverter
import java.util.*

object TimestampConverter {
    var df: DateFormat = getDateTimeInstance()

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value != null) {
            df.parse(value)
        } else {
            null
        }
    }

    @TypeConverter
    fun toTimestamp(value: Date?): String? {
        return if (value != null) {
            df.format(value)
        } else {
            null
        }
    }
}