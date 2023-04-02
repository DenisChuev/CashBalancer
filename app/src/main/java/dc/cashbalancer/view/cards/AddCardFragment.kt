package dc.cashbalancer.view.cards

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
import dc.cashbalancer.databinding.FragmentAddCardBinding

class AddCardFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm = ViewModelProvider(requireActivity())[CardsViewModel::class.java]

        binding.cardSaveBtn.setOnClickListener {
            // TODO валидация данных

            vm.addCard(
                Card(
                    binding.cardNameInput.text.toString(),
                    binding.cardAmountInput.text.toString().toDouble()
                )
            )
            dismiss()
        }

        binding.colorPicker.setBackgroundColor(resources.getColor(R.color.default_card_color))
        binding.colorPicker.setOnClickListener {
            val colorPickerDialog = ColorPickerDialog(context)

            colorPickerDialog.setColors()
                .setColumns(4)
                .setDefaultSelectedColor(R.color.default_card_color)
                .setColorItemShape(ColorItemShape.CIRCLE)
                .setOnSelectColorListener(object : OnSelectColorListener {
                    override fun onColorSelected(color: Int, position: Int) {
                        binding.card.setCardBackgroundColor(color)
                        binding.colorPicker.setBackgroundColor(color)
                    }

                    override fun cancel() {
                        colorPickerDialog.dismissDialog()
                    }
                })
                .show()
        }

    }
}