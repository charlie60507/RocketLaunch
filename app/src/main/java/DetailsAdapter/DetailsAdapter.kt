package DetailsAdapter

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
            groupHolder.bind(groups[groupPosition])
            view.tag = groupHolder
        } else {
            view = convertView
        }
        return view
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {
        val view: View
        val itemHolder: ItemHolder?
        if (convertView == null) {
            val binding =
                ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = binding.root
            itemHolder = ItemHolder(binding)
            val child = getChild(groupPosition, childPosition)
            itemHolder.bind(child.first, child.second)
            view.tag = itemHolder
        } else {
            view = convertView
        }
        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean = false

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
