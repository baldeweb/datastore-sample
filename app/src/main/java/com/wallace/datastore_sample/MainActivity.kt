package com.wallace.datastore_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wallace.datastore_sample.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var nameDataStore: NameDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nameDataStore = NameDataStore(this)
        initListeners()
        getName()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            lifecycleScope.launch {
                nameDataStore.insertData("Wallace")
            }
        }

        binding.btnGet.setOnClickListener {
            getName()
        }

        binding.btnUpdate.setOnClickListener {
            lifecycleScope.launch {
                nameDataStore.updateData("Baldenebre")
            }
        }

        binding.btnRemove.setOnClickListener {
            lifecycleScope.launch {
                nameDataStore.removeData()
            }
        }
    }

    private fun getName() {
        lifecycleScope.launchWhenStarted {
            //  First way: request on demand, not a live data
            binding.tvwText.text = nameDataStore.getData().first() ?: "404 Not Found"

            //  Second way: live data, changes when it's updated
            /*
            nameDataStore.getData().collect { prefs ->
                binding.tvwText.text = prefs ?: "404 Not Found"
            }
            */
        }
    }
}