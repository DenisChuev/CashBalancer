package dc.cashbalancer.dao

import androidx.room.*

@Dao
interface CardDao {
    @Query("Select * from cards")
    fun getAllCards(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCard(card: CardEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCard(card: CardEntity)

    @Delete
    fun deleteCard(card: CardEntity)

    @Query("update cards set amount = :cardAmount where id = :cardId")
    fun updateAmount(cardAmount: Double, cardId: Int)

    @Query("select name from cards where id = :cardId")
    fun getName(cardId: Int): String
}