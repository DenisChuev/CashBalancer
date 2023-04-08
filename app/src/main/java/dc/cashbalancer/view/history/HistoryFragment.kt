package dc.cashbalancer.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dc.cashbalancer.databinding.FragmentCardBinding
import dc.cashbalancer.databinding.FragmentHistoryBinding
import dc.cashbalancer.view.cards.CardsViewModel

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm = ViewModelProvider(requireActivity())[HistoryViewModel::class.java]

        binding.addOperationBtn.setOnClickListener {
            NewOperationFragment().show(parentFragmentManager, "NewOperationFragment")
        }
        binding.historyList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = HistoryListAdapter()
        }

        vm.operationsList.observe(requireActivity()) {
            (binding.historyList.adapter as HistoryListAdapter).updateAdapter(it)
        }
    }
}