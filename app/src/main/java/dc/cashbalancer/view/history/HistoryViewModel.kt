package dc.cashbalancer.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dc.cashbalancer.dao.CardEntity
import dc.cashbalancer.dao.CashRepository
import dc.cashbalancer.dao.OperationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    private val repo = CashRepository()
    private val data: MutableLiveData<List<CardEntity>> = MutableLiveData(emptyList())
    val cardList: LiveData<List<CardEntity>>
        get() = data

    private val operationsData: MutableLiveData<List<OperationEntity>> =
        MutableLiveData(emptyList())
    val operationsList: LiveData<List<OperationEntity>>
        get() = operationsData

    val categories: ArrayList<String> = arrayListOf(
        "Продукты",
        "Кафе",
        "Транспорт",
        "Покупки",
        "Дом",
        "Развлечения",
        "Сервис",
        "Подарки",
        "Здоровье"
    )

    init {
        loadCards()
        loadOperations()
    }

    private fun loadOperations() {
        viewModelScope.launch(Dispatchers.IO) {
            operationsData.postValue(repo.getOperations())
        }
    }

    fun loadCards() {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(repo.getAllCards())
        }
    }

    fun addOperation(operation: Operation, card: CardEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addOperation(operation, card)
        }
    }

    fun getCardName(cardId: Int): LiveData<String> {
        val cardName = MutableLiveData<String>()
        viewModelScope.launch(Dispatchers.IO) {
            cardName.value = repo.getCardName(cardId)
        }
        return cardName
    }


}