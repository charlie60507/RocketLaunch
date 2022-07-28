package com.example.rocketlaunch.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.rocketlaunch.data.RocketInfo
import com.example.rocketlaunch.data.RocketInfoItem
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
            val flightNumText = "Flight ${rocketInfoItem.flight_number}"
            val loadingIcon = CircularProgressDrawable(binding.root.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
            }
            loadingIcon.start()

            binding.flightNumber.text = flightNumText
            binding.missionName.text = rocketInfoItem.mission_name
            binding.launchDateUtc.text = rocketInfoItem.launch_date_utc
            (rocketInfoItem.links.mission_patch_small as? String)?.let {
                Glide
                    .with(binding.root.context)
                    .load(it)
                    .placeholder(loadingIcon)
                    .into(binding.icon)
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
