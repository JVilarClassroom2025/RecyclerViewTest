package es.uji.jvilar.recyclerviewtest.phoneactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.uji.jvilar.recyclerviewtest.databinding.PhoneRowBinding


data class Phone (val name: String, val number: String)

class PhoneAdapter (val phones: List<Phone>,
                    val onClickListener: (Phone)-> Unit):
    RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {
    class ViewHolder(val binding: PhoneRowBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhoneRowBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        phones[position].let { entry ->
            with(holder.binding) {
                nameTextView.text = entry.name
                numberTextView.text = entry.number
                root.setOnClickListener {
                    onClickListener(entry)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return phones.size
    }
}
