package com.example.rocketlaunch.ui

import DetailsAdapter.DetailsAdapter
import RocketInfoItem
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rocketlaunch.GlideUtils
import com.example.rocketlaunch.databinding.FragmentRocketDetailBinding







private const val ICON_BITMAP = "icon_bitmap"
private const val INFO_ITEM = "info_item"

class RocketDetailFragment : Fragment() {
    private var iconBitmap: Bitmap? = null
    private var infoItem: RocketInfoItem? = null
    private var _binding: FragmentRocketDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            iconBitmap = it.getParcelable(ICON_BITMAP)
            infoItem = it.getParcelable(INFO_ITEM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoItem?.let {
            if (iconBitmap != null) {
                binding.detailIcon.setImageBitmap(iconBitmap)
            } else {
                Log.d(TAG, "loading, url=${infoItem?.links?.missionPatchSmall}")
                GlideUtils.loadRocketIcon(
                    binding.root.context,
                    it.links.missionPatchSmall,
                    binding.detailIcon
                )
            }
            binding.flightNumber.text = it.flightNumber.toString()
            binding.launchDate.text = it.launchDateUtc
            binding.launchSite.text = it.launchSite.siteName
            binding.missionName.text = it.missionName

            val groupNameList = arrayListOf("cores", "payloads", "links")
            val itemNameList = ArrayList<List<Pair<String, String>>>()

            val core = it.rocket.firstStage.cores[0]
            val payload = it.rocket.secondStage.payloads[0]
            val links = it.links

            val coreList: List<Pair<String, String>> = listOf(
                Pair("core_serial", core.coreSerial),
                Pair("flight", core.flight.toString()),
                Pair("block", core.block.toString()),
                Pair("gridfins", core.gridfins.toString())
            )
            val payloadList: List<Pair<String, String>> = listOf(
                Pair("payload_id", payload.payloadId),
                Pair("payload_type", payload.payloadType),
                Pair("manufacturer", payload.manufacturer),
                Pair("orbit", payload.orbit)
            )
            val linksList: List<Pair<String, String>> = listOf(
                Pair("article_link", links.articleLink),
                Pair("wikipedia", links.wikipedia),
                Pair("video_link", links.videoLink)
            )
            itemNameList.add(coreList)
            itemNameList.add(payloadList)
            itemNameList.add(linksList)
            binding.coresGroup.setAdapter(DetailsAdapter(groupNameList, itemNameList))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "RocketDetailFragment"

        @JvmStatic
        fun newInstance(
            icon: Bitmap?, item: RocketInfoItem
        ) =
            RocketDetailFragment().apply {
                arguments = Bundle().apply {
                    Log.d(TAG, "apply, item=$item")
                    putParcelable(ICON_BITMAP, icon)
                    putParcelable(INFO_ITEM, item)
                }
            }
    }
}