package dc.cashbalancer.view.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mrudultora.colorpicker.ColorPickerDialog
import com.mrudultora.colorpicker.listeners.OnSelectColorListener
import com.mrudultora.colorpicker.util.ColorItemShape
import dc.cashbalancer.R
import kotlinx.android.synthetic.main.fragment_add_card.*

class AddCardFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        color_picker.setOnClickListener {
            val colorPickerDialog = ColorPickerDialog(context) // Pass the context.

            colorPickerDialog.setColors()
                .setColumns(4)
                .setDefaultSelectedColor(R.color.default_card)
                .setColorItemShape(ColorItemShape.CIRCLE)
                .setOnSelectColorListener(object : OnSelectColorListener {
                    override fun onColorSelected(color: Int, position: Int) {
                        // handle color or position
                    }

                    override fun cancel() {
                        colorPickerDialog.dismissDialog() // Dismiss the dialog.
                    }
                })
                .show()
        }

    }
}