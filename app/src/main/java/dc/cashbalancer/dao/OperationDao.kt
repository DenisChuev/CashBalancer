package dc.cashbalancer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OperationDao {
    @Query("Select * from operations")
    fun getAllOperations(): List<OperationEntity>

    @Insert
    fun addOperation(operation: OperationEntity)
}