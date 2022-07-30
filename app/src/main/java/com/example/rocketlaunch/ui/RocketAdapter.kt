package com.example.rocketlaunch.ui

import RocketInfoItem
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
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
            val loadingIcon = CircularProgressDrawable(binding.root.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
            }
            loadingIcon.start()

            binding.flightNumber.text = flightNumText
            binding.missionName.text = rocketInfoItem.missionName
            binding.launchDateUtc.text = rocketInfoItem.launchDateUtc
            Glide
                .with(binding.root.context)
                .load(rocketInfoItem.links.missionPatchSmall)
                .placeholder(loadingIcon)
                .into(binding.icon)
            binding.root.setOnClickListener {
                (binding.root.context as? AppCompatActivity)?.supportFragmentManager?.commit {
                    addToBackStack("detail")
                    replace(R.id.fragment_container, RocketDetailFragment.newInstance(binding.icon.drawable.toBitmap()), "detail")
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
