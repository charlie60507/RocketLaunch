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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RocketListFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRocketListBinding? = null
    private val binding get() = _binding!!

    private val rocketViewModel = RocketViewModel(DataRepository)
    private var rocketAdapter = RocketAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        super.onResume()
        refreshData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshData() {
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