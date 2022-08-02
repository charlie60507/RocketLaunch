package com.example.rocketlaunch.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketlaunch.data.FlightOrder
import com.example.rocketlaunch.databinding.FragmentRocketListBinding
import com.example.rocketlaunch.persistence.DataRepository
import com.example.rocketlaunch.viewmodel.RocketViewModel

class RocketListFragment : Fragment() {
    private var _binding: FragmentRocketListBinding? = null
    private val binding get() = _binding!!

    private val rocketViewModel = RocketViewModel(DataRepository)
    private var rocketAdapter = RocketAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Log.i(TAG, "onCreate")
        super.onStart()

        // Refresh data
        refreshData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView")
        _binding = FragmentRocketListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")

        // setup recyclerview
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rocketAdapter
        }

        // pull down to refresh
        binding.recyclerViewContainer.setOnRefreshListener {
            refreshData()
        }

        rocketViewModel.into.observe(viewLifecycleOwner) {
            Log.d(TAG, "update rocketAdapter.info")
            binding.recyclerViewContainer.isRefreshing = false
            rocketAdapter.info = it
            binding.switchOrderBtn.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.VISIBLE
            rocketAdapter.notifyDataSetChanged()
        }

        // change ordering
        binding.switchOrderBtn.setOnClickListener {
            context?.let { context ->
                val builder = AlertDialog.Builder(context)
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
    }

    override fun onResume() {
        Log.i(TAG, "onResume")
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshData() {
        Log.d(TAG, "refreshData")
        binding.recyclerViewContainer.isRefreshing = true
        binding.switchOrderBtn.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        rocketViewModel.getRocketInfo()
    }

    companion object {
        private const val TAG = "RocketListFragment"
        @JvmStatic
        fun newInstance() = RocketListFragment()
    }
}