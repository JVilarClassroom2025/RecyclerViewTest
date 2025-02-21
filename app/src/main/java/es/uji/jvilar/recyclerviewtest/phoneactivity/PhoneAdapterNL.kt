package es.uji.jvilar.recyclerviewtest.phoneactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.uji.jvilar.recyclerviewtest.databinding.PhoneRowBinding

class PhoneAdapterNL (val phones: List<Phone>):
    RecyclerView.Adapter<PhoneAdapterNL.ViewHolder>() {

    class ViewHolder(val binding: PhoneRowBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhoneRowBinding.inflate(
            inflater,
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        with (phones[position]) {
            binding.nameTextView.text = name
            binding.numberTextView.text = number
        }
    }

    override fun getItemCount(): Int {
        return phones.size
    }
}