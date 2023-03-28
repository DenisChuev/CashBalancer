package dc.cashbalancer.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardEntity::class], exportSchema = false, version = 1)
abstract class CashDatabase : RoomDatabase() {

    abstract fun getCardDao(): CardDao
}