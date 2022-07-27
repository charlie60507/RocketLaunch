package com.example.rocketlaunch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.rocketlaunch.OkHttpSingleton
import com.example.rocketlaunch.R
import com.example.rocketlaunch.viewmodel.RocketViewModel

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerContainer: SwipeRefreshLayout

    private val rocketViewModel = RocketViewModel()
    var rocketAdapter = RocketAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(R.layout.activity_main)

        // init views
        recyclerView = findViewById(R.id.recycler_view)
        recyclerContainer = findViewById(R.id.recycler_view_container)

        // setup recyclerview
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rocketAdapter
        }

        // pull down to refresh
        recyclerContainer.setOnRefreshListener {
            refreshData()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
        refreshData()
    }

    private fun refreshData() {
        recyclerContainer.isRefreshing = true
        rocketViewModel.getRocketInfo().observe(this, {
            Log.d(TAG, "update rocketAdapter.info")
            recyclerContainer.isRefreshing = false
            rocketAdapter.info = it
            rocketAdapter.notifyDataSetChanged()
        })
    }
}