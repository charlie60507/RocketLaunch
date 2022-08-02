package com.example.rocketlaunch.ui

import RocketInfoItem
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketlaunch.GlideUtils
import com.example.rocketlaunch.R
import com.example.rocketlaunch.data.RocketInfo
import com.example.rocketlaunch.databinding.RocketItemBinding

class RocketAdapter :
    RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {
    companion object {
        private const val TAG = "RocketAdapter"
    }

    var info: RocketInfo = RocketInfo()

    // ViewHolder
    class RocketViewHolder(private val binding: RocketItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rocketInfoItem: RocketInfoItem) {
            val flightNumText = "Flight ${rocketInfoItem.flightNumber}"
            binding.flightNumber.text = flightNumText
            binding.missionName.text = rocketInfoItem.missionName
            binding.launchDateUtc.text = rocketInfoItem.launchDateUtc
            GlideUtils.loadRocketIcon(
                binding.root.context,
                rocketInfoItem.links.missionPatchSmall,
                binding.icon
            )
            binding.root.setOnClickListener {
                val bitmap =
                    if (binding.icon.drawable != null && binding.icon.drawable.intrinsicHeight > 0) { // get icon if it is loaded
                        binding.icon.drawable.toBitmap()
                    } else {
                        null
                }
                Log.d(TAG, "rocketInfoItem=$rocketInfoItem, url=${rocketInfoItem.links.missionPatchSmall}")
                (binding.root.context as? AppCompatActivity)?.supportFragmentManager?.commit {
                    addToBackStack("detail")
                    replace(
                        R.id.fragment_container,
                        RocketDetailFragment.newInstance(bitmap, rocketInfoItem),
                        "detail"
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(
            RocketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder, pos=$position")
        holder.bind(info[position])
    }

    override fun getItemCount() = info.size

}
