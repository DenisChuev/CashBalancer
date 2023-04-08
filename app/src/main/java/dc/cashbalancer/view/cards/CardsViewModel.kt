package dc.cashbalancer.view.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dc.cashbalancer.dao.CardEntity
import dc.cashbalancer.dao.CashRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardsViewModel : ViewModel() {
    private val repo = CashRepository()
    private val data: MutableLiveData<List<CardEntity>> = MutableLiveData(emptyList())
    val cardList: LiveData<List<CardEntity>>
        get() = data

    init {
        loadCards()
    }

    fun loadCards() {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(repo.getAllCards())
        }
    }

    fun addCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addCard(card)
            loadCards()
        }
    }

    fun updateCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateCard(card)
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteCard(card)
        }
    }
}