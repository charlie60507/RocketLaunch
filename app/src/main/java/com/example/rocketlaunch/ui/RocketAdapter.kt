package com.example.rocketlaunch.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.rocketlaunch.R
import com.example.rocketlaunch.data.RocketInfo
import com.example.rocketlaunch.data.RocketInfoItem

class RocketAdapter :
    RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {
    companion object {
        private const val TAG = "RocketAdapter"
    }
    var info: RocketInfo = RocketInfo()

    // ViewHolder
    class RocketViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(rocketInfoItem: RocketInfoItem) {
            val flightNumText = "Flight ${rocketInfoItem.flight_number}"
            val iconImageView = itemView.findViewById<ImageView>(R.id.icon)
            val loadingIcon = CircularProgressDrawable(iconImageView.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
            }
            loadingIcon.start()

            itemView.findViewById<TextView>(R.id.flight_number).text = flightNumText
            itemView.findViewById<TextView>(R.id.mission_name).text = rocketInfoItem.mission_name
            itemView.findViewById<TextView>(R.id.launch_date_utc).text =
                rocketInfoItem.launch_date_utc
            (rocketInfoItem.links.mission_patch_small as? String)?.let {
                Glide
                    .with(iconImageView.context)
                    .load(it)
                    .placeholder(loadingIcon)
                    .into(iconImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rocket_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder, pos=$position")
        holder.bind(info[position])
    }

    override fun getItemCount() = info.size

}
