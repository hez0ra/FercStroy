package com.example.fercstroy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.fercstroy.R

class MyExpandableListAdapter(
    private val context: Context,
    private val groupList: List<String>,
    private val itemList: Map<String, List<Int>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return groupList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return itemList[groupList[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return itemList[groupList[groupPosition]]?.get(childPosition) ?: 0
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val groupTitle = getGroup(groupPosition) as String
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_group, parent, false)
        val textView = view.findViewById<TextView>(R.id.groupTextView) // Убедитесь, что ID соответствует вашему layout
        textView.text = groupTitle
        return view
    }


    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val imageResId = getChild(groupPosition, childPosition) as Int
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_expandable, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(imageResId)
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
