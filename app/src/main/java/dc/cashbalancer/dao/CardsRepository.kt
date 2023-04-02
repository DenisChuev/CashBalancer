package dc.cashbalancer.dao

import dc.cashbalancer.CashApp
import dc.cashbalancer.view.cards.Card

class CardsRepository {
    private val db by lazy { CashApp.db }
    private val cardDao = db.getCardDao()

    fun addCard(card: Card) {
        cardDao.addCard(card.toEntity())
    }

    fun updateCard(card: Card) {
        cardDao.updateCard(card.toEntity())
    }

    fun deleteCard(card: Card) {
        cardDao.deleteCard(card.toEntity())
    }

    fun getAllCards(): List<Card> {
        return cardDao.getAllCards()
    }
}

public fun Card.toEntity(): CardEntity = CardEntity(name, amount, color)

public fun CardEntity.toModel(): Card = Card(name, amount, color)
