package dc.cashbalancer.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardEntity::class, OperationEntity::class], exportSchema = false, version = 2)
abstract class CashDatabase : RoomDatabase() {
    abstract fun getCardDao(): CardDao
    abstract fun getOperationDao(): OperationDao
}