package com.example.rocketlaunch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.rocketlaunch.databinding.ItemsBinding
import com.example.rocketlaunch.databinding.ListGroupBinding


class DetailsAdapter(
    private val groups: ArrayList<String>,
    private val items: ArrayList<List<Pair<String, String>>>
) :
    BaseExpandableListAdapter() {
    override fun getGroupCount(): Int = groups.size

    override fun getChildrenCount(itemPos: Int): Int = items[itemPos].size

    override fun getGroup(groupPos: Int): String = groups[groupPos]

    override fun getChild(groupPos: Int, itemPos: Int): Pair<String, String> =
        items[groupPos][itemPos]

    override fun getGroupId(groupPos: Int): Long = groupPos.toLong()

    override fun getChildId(groupPos: Int, itemPos: Int): Long = itemPos.toLong()

    override fun hasStableIds(): Boolean = true

    override fun isChildSelectable(p0: Int, p1: Int): Boolean = false

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean, convertView: View?,
        parent: ViewGroup
    ): View {
        val view: View
        val groupHolder: GroupHolder?
        if (convertView == null) {
            val binding =
                ListGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = binding.root
            groupHolder = GroupHolder(binding)
            view.tag = groupHolder
        } else {
            view = convertView
            groupHolder = view.tag as GroupHolder
        }
        groupHolder.bind(groups[groupPosition])

        return view
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {
        val view: View
        val itemHolder: ItemHolder?
        val child = getChild(groupPosition, childPosition)

        if (convertView == null) {
            val binding =
                ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = binding.root
            itemHolder = ItemHolder(binding)
            view.tag = itemHolder
        } else {
            view = convertView
            itemHolder = view.tag as ItemHolder
        }
        itemHolder.bind(child.first, child.second)
        return view
    }

    internal class GroupHolder(private val binding: ListGroupBinding) {
        fun bind(title: String) {
            binding.title.text = title
        }
    }

    internal class ItemHolder(private val binding: ItemsBinding) {
        fun bind(title: String?, value: String?) {
            binding.title.text = title
            binding.value.text = value
        }
    }
}
