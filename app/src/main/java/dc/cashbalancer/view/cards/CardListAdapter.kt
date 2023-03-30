package dc.cashbalancer.view.cards

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import dc.cashbalancer.R
import dc.cashbalancer.dao.CardEntity
import dc.cashbalancer.dao.toModel

class CardListAdapter() :
    RecyclerView.Adapter<CardListAdapter.CardItemViewHolder>() {
    private lateinit var cardsList: List<Card>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    fun updateCards(cards: List<Card>) {
        cardsList = cards
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        holder.bind(cardsList[position])
    }

    class CardItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val cardName: TextView = view.findViewById(R.id.card_name)
        private val cardAmount: TextView = view.findViewById(R.id.card_amount)

        fun bind(card: Card) {
            cardName.text = card.name
            cardAmount.text = card.amount.toString()
            (view as CardView).setCardBackgroundColor(Color.parseColor(card.color))
        }

    }

}

