package es.uji.jvilar.recyclerviewtest.phoneactivity

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import es.uji.jvilar.recyclerviewtest.R
import es.uji.jvilar.recyclerviewtest.databinding.DialogPhoneBinding

class PhoneDialog: DialogFragment() {
    private val viewModel: PhoneViewModel by activityViewModels()
    private lateinit var binding: DialogPhoneBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogPhoneBinding.inflate(layoutInflater, null, false)
        with(binding) {
            nameTextView.text = viewModel.currentName
            numberTextView.text = viewModel.currentNumber
        }
        return AlertDialog.Builder(requireContext()).run {
            setView(binding.root)
            setPositiveButton(android.R.string.ok, null)
            create()
        }
    }
}
