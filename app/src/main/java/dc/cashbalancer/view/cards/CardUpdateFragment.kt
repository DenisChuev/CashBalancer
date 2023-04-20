package dc.cashbalancer.view.cards

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mrudultora.colorpicker.ColorPickerDialog
import com.mrudultora.colorpicker.listeners.OnSelectColorListener
import com.mrudultora.colorpicker.util.ColorItemShape
import dc.cashbalancer.R
import dc.cashbalancer.dao.CardEntity
import dc.cashbalancer.dao.toModel
import dc.cashbalancer.databinding.FragmentCardUpdateBinding

class CardUpdateFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCardUpdateBinding
    private lateinit var card: CardEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun newInstance(card: CardEntity): CardUpdateFragment {
        this.card = card
        return this
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm = ViewModelProvider(requireActivity())[CardsViewModel::class.java]

        binding.cardNameInput.setText(card.name)
        binding.cardAmountInput.setText("${card.amount} ${card.currency}")
        binding.colorPicker.setBackgroundColor(card.color)
        binding.card.setBackgroundColor(card.color)

        binding.colorPicker.setOnClickListener {
            val colorPickerDialog = ColorPickerDialog(context)

            colorPickerDialog.setColors()
                .setColumns(4)
                .setDefaultSelectedColor(R.color.default_card_color)
                .setColorItemShape(ColorItemShape.CIRCLE)
                .setOnSelectColorListener(object : OnSelectColorListener {
                    override fun onColorSelected(color: Int, position: Int) {
                        card.color = color
                        binding.card.setBackgroundColor(color)
                        binding.colorPicker.setBackgroundColor(color)
                    }

                    override fun cancel() {
                        colorPickerDialog.dismissDialog()
                    }
                })
                .show()
        }

        binding.cardSaveBtn.setOnClickListener {
            card.amount = binding.cardAmountInput.text.toString().substringBefore(" ").toDouble()
            card.name = binding.cardNameInput.text.toString()
            vm.updateCard(card)
            dismiss()
        }

        binding.cardDeleteBtn.setOnClickListener {
            vm.deleteCard(card)
            dismiss()
        }
    }
}