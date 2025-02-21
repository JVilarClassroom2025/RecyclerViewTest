package es.uji.jvilar.recyclerviewtest.recyclerActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uji.jvilar.recyclerviewtest.R
import es.uji.jvilar.recyclerviewtest.databinding.ActivityRecyclerTestBinding

class RecyclerTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val squareAdapter = SquareAdapter(1_000_000_000) {
            binding.holderCounterTextView.text = "Holders created: $it"
        }
        with (binding.recyclerView) {
            adapter = squareAdapter
            layoutManager = LinearLayoutManager(this@RecyclerTestActivity)
        }
    }
}