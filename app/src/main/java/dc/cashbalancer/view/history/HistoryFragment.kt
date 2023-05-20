package dc.cashbalancer.view.history

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dc.cashbalancer.R
import dc.cashbalancer.databinding.FragmentHistoryBinding

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

        val items = resources.getStringArray(R.array.account_array)
        val spinnerAdapter = object : ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            items
        ) {

            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView =
                    super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    view.setTextColor(Color.GRAY)
                }
                return view
            }

        }
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.accountSpinner.adapter = spinnerAdapter

        binding.accountSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val value = parent!!.getItemAtPosition(position).toString()
                if(value == items[0]){
                    (view as TextView).setTextColor(Color.GRAY)
                }
            }

        }
    }
}