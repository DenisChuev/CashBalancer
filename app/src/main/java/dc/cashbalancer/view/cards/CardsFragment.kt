package dc.cashbalancer.view.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dc.cashbalancer.R
import kotlinx.android.synthetic.main.fragment_card.*

class CardsFragment : Fragment() {
    val cards: ArrayList<Card> = arrayListOf(Card("test1", 1000.0), Card("test2", 50000.0))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cards_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CardListAdapter(cards)
        }
        add_card_btn.setOnClickListener {
            val addCardFragment = AddCardFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.card_fragment_layout, addCardFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}