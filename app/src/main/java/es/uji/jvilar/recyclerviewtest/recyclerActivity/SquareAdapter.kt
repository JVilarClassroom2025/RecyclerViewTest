package es.uji.jvilar.recyclerviewtest.recyclerActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.jvilar.recyclerviewtest.R
import es.uji.jvilar.recyclerviewtest.databinding.RecyclerRowBinding

class SquareAdapter(private val size: Int, private val listener: (Int) -> Unit):
    RecyclerView.Adapter<SquareAdapter.ViewHolder>() {
    companion object {
        var counter = 0
    }

    init {
        counter = 0
    }

    class ViewHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowBinding.inflate(inflater, parent, false)
        binding.holderTextView.text = "${counter++}"
        listener(counter)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val n = position + 1
        with(holder.binding) {
            numberTextView.text = "$n"
            numberSquaredTextView.text = "${(n * n)}"
        }
    }

    override fun getItemCount() = size
}