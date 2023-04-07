package dc.cashbalancer.dao

import dc.cashbalancer.CashApp
import dc.cashbalancer.view.cards.Card
import dc.cashbalancer.view.history.Operation
import dc.cashbalancer.view.history.OperationType

class CardsRepository {
    private val db by lazy { CashApp.db }
    private val cardDao = db.getCardDao()
    private val operationDao = db.getOperationDao()

    fun addCard(card: Card) {
        cardDao.addCard(card.toEntity())
    }

    fun updateCard(card: Card) {
        cardDao.updateCard(card.toEntity())
    }

    fun deleteCard(card: Card) {
        cardDao.deleteCard(card.toEntity())
    }

    fun getAllCards(): List<CardEntity> {
        return cardDao.getAllCards()
    }

    fun addOperation(operation: Operation, card: CardEntity) {
        operationDao.addOperation(operation.toEntity())
        when (operation.type) {
            OperationType.WITHDRAWAL -> card.amount -= operation.sum
            OperationType.REPLENISHMENT -> card.amount += operation.sum
        }
        cardDao.updateAmount(card.amount, card.id)
    }
}

public fun Operation.toEntity(): OperationEntity =
    OperationEntity(cardID, type, category, sum, date)

public fun Card.toEntity(): CardEntity = CardEntity(name, amount, color)

public fun CardEntity.toModel(): Card = Card(name, amount, color)
