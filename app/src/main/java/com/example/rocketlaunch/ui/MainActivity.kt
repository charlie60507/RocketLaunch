package com.example.rocketlaunch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketlaunch.databinding.ActivityMainBinding
import com.example.rocketlaunch.viewmodel.RocketViewModel

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    private val rocketViewModel = RocketViewModel()
    var rocketAdapter = RocketAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup recyclerview
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rocketAdapter
        }

        // pull down to refresh
        binding.recyclerViewContainer.setOnRefreshListener {
            refreshData()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
        refreshData()
    }

    private fun refreshData() {
        binding.recyclerViewContainer.isRefreshing = true
        rocketViewModel.getRocketInfo().observe(this, {
            Log.d(TAG, "update rocketAdapter.info")
            binding.recyclerViewContainer.isRefreshing = false
            rocketAdapter.info = it
            rocketAdapter.notifyDataSetChanged()
        })
    }
}