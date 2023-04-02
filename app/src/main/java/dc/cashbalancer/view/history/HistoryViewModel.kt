package dc.cashbalancer.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dc.cashbalancer.dao.CardEntity
import dc.cashbalancer.dao.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    private val repo = CardsRepository()
    private val data: MutableLiveData<List<CardEntity>> = MutableLiveData(emptyList())
    val cardList: LiveData<List<CardEntity>>
        get() = data

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
    }

    fun loadCards() {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(repo.getAllCards())
        }
    }


}