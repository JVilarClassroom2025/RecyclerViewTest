package es.uji.jvilar.recyclerviewtest.phoneactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import es.uji.jvilar.recyclerviewtest.databinding.ActivityPhoneBinding

class PhoneActivity : AppCompatActivity(), PhoneView {
    private val viewModel: PhoneViewModel by viewModels()
    private lateinit var binding: ActivityPhoneBinding

    private val phones = createEntries()

    private fun createEntries(): List<Phone> {
        val phones = ArrayList<Phone>()
        for (name in listOf("Juan", "Marta", "Luis", "Rosa", "Antonio", "Alberto", "Ana"))
            for (surname in listOf("García", "Pérez", "González", "Ruiz", "Sánchez", "Fernández")) {
                val number = "${600+(0..99).random()} ${(10..99).random()} ${(10..99).random()} ${(10..99).random()}"
                phones.add(Phone("$name $surname", number))
            }
        phones.sortBy {it.number}
        return phones
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(binding.root)

        binding.toolbar.title = "Phone Numbers"

        //parte: adapterBinding
        binding.phoneRecyclerView.run {
            adapter = PhoneAdapter(phones) {
                viewModel.onShowPhoneRequested(it)
            }
            layoutManager = LinearLayoutManager(this@PhoneActivity)
        }
        //finparte
    }

    override fun onResume() {
        super.onResume()
        viewModel.view = this
    }

    override fun onPause() {
        super.onPause()
        viewModel.view = null
    }

    override fun showPhone(phone: Phone) {
        Toast.makeText(this@PhoneActivity, phone.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showPhoneDialog() {
        PhoneDialog().show(supportFragmentManager, "Phone Dialog")
    }

    private fun fakeBinding() {
        fun PhoneAdapter(a: Int): PhoneAdapter {
            return PhoneAdapter(phones) {}
        }

        val phones = 4
        //parte: bindingNoListener
        binding.phoneRecyclerView.run {
            adapter = PhoneAdapter(phones)
            layoutManager = LinearLayoutManager(this@PhoneActivity)
        }
        //finparte

    }
}
