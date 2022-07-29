package com.example.rocketlaunch.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketlaunch.data.FlightOrder
import com.example.rocketlaunch.databinding.ActivityMainBinding
import com.example.rocketlaunch.persistence.DataRepository
import com.example.rocketlaunch.viewmodel.RocketViewModel

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    private val rocketViewModel = RocketViewModel(DataRepository)
    private var rocketAdapter = RocketAdapter()

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

        rocketViewModel.into.observe(this) {
            Log.d(TAG, "update rocketAdapter.info")
            binding.recyclerViewContainer.isRefreshing = false
            rocketAdapter.info = it
            binding.switchOrderBtn.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.VISIBLE
            rocketAdapter.notifyDataSetChanged()
        }

        // change ordering
        binding.switchOrderBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val items = arrayOf("Flight Number: oldest", "Flight Number: newest")
            builder.setTitle("Select Order")
            builder.setItems(items) { dialog, which ->
                rocketViewModel.reorder(FlightOrder.fromInt(which))
                dialog.cancel()
                binding.switchOrderBtn.text = items[which]
            }
            builder.create().show()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
        refreshData()
    }

    private fun refreshData() {
        binding.recyclerViewContainer.isRefreshing = true
        binding.switchOrderBtn.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        rocketViewModel.getRocketInfo()
    }
}