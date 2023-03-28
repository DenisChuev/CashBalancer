package dc.cashbalancer.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardDao {
    @Query("Select * from cards")
    fun getAllCards(): LiveData<List<CardEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCard(card: CardEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCard(card: CardEntity)

    @Delete
    fun deleteCard(card: CardEntity)
}