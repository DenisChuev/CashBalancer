package dc.cashbalancer.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dc.cashbalancer.R
import dc.cashbalancer.dao.CardEntity
import dc.cashbalancer.databinding.FragmentNewOperationBinding
import kotlinx.coroutines.launch

class NewOperationFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewOperationBinding
    private lateinit var historyVM: HistoryViewModel

    private lateinit var selectedCard: CardEntity
    private lateinit var selectedCategory: String

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewOperationBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyVM = ViewModelProvider(requireActivity())[HistoryViewModel::class.java]
        historyVM.loadCards()

        lifecycleScope.launch {
            historyVM.loadCards()
            historyVM.cardList.observe(requireActivity()) {
                val cards: ArrayList<String> = ArrayList()
                it.forEach { card ->
                    cards.add(card.name)
                }

                binding.cardsSpinner.item = cards as List<Any>?
                binding.cardsSpinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            adapterView: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                        ) {
                            selectedCard = it[position]
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }
                    }
            }
        }

        binding.categoriesSpinner.item = historyVM.categories as List<Any>?
        binding.categoriesSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    selectedCategory = historyVM.categories[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        binding.saveOperationBtn.setOnClickListener {
            // TODO валидация ввода
            historyVM.addOperation(
                Operation(
                    selectedCard.id,
                    OperationType.WITHDRAWAL,
                    selectedCategory,
                    binding.operationSumInput.text.toString().toDouble()
                ),
                selectedCard
            )
        }
    }
}