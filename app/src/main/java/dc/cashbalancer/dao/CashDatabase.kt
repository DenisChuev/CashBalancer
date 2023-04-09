package dc.cashbalancer.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CardEntity::class, OperationEntity::class], exportSchema = false, version = 5)
@TypeConverters(Converters::class)
abstract class CashDatabase : RoomDatabase() {
    abstract fun getCardDao(): CardDao
    abstract fun getOperationDao(): OperationDao
}