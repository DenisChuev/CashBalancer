package dc.cashbalancer.view.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dc.cashbalancer.dao.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardsViewModel : ViewModel() {
    private val repo = CardsRepository()
    private val data: MutableLiveData<List<Card>> = MutableLiveData(emptyList())
    val cardList: LiveData<List<Card>>
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
        repo.updateCard(card)
    }

    fun deleteCard(card: Card) {
        repo.deleteCard(card)
    }
}