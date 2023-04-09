package dc.cashbalancer.view.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dc.cashbalancer.databinding.FragmentCardBinding

class CardsFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm = ViewModelProvider(requireActivity())[CardsViewModel::class.java]

        binding.cardsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CardListAdapter()
        }
        binding.addCardBtn.setOnClickListener {
            val addCardFragment = AddCardFragment()
            addCardFragment.show(parentFragmentManager, "AddCardFragment")
        }

        vm.cardList.observe(
            requireActivity()
        ) { cards -> (binding.cardsList.adapter as CardListAdapter).updateCards(cards) }
    }
}