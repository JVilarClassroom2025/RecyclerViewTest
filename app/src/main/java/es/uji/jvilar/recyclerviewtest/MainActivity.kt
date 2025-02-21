package es.uji.jvilar.recyclerviewtest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.uji.jvilar.recyclerviewtest.databinding.ActivityMainBinding
import es.uji.jvilar.recyclerviewtest.phoneactivity.PhoneActivity
import es.uji.jvilar.recyclerviewtest.recyclerActivity.RecyclerTestActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //parte: setOptions
        val options = arrayOf(PHONE, RECYCLER)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            options)
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        binding.activitySpinner.adapter = adapter
        //finparte

        binding.activitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?,
                                            position: Int,
                                            id: Long) {
                    fixItemSelected()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        //parte: goButton
        binding.goButton.setOnClickListener {
            changeActivity(binding.activitySpinner.selectedItem as String)
        }
        //finparte
    }

    fun fixItemSelected() {
        //parte: onItemSelectedListener
        binding.activitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?,
                                            position: Int,
                                            id: Long) {
                    val spinner = binding.activitySpinner
                    changeActivity(spinner.selectedItem as String)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        //finparte
    }

    fun changeActivity(name: String) {
        val activity = when (name) {
            PHONE -> PhoneActivity::class.java
            RECYCLER -> RecyclerTestActivity::class.java
            else -> throw IllegalArgumentException("Invalid activity name")
        }
        startActivity(Intent(this, activity))
    }

    companion object {
        const val PHONE = "PhoneActivity"
        const val RECYCLER = "RecyclerTestActivity"
    }
}