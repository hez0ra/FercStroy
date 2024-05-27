package com.example.fercstroy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fercstroy.R

data class TimelineItem(val year: String, val title: String, val description: String)

class TimelineAdapter(private val timelineItems: List<TimelineItem>) :
    RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

    inner class TimelineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val yearTextView: TextView = itemView.findViewById(R.id.timeline_year)
        val titleTextView: TextView = itemView.findViewById(R.id.timeline_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.timeline_description)
        val line: View = itemView.findViewById(R.id.timeline_line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timeline, parent, false)
        return TimelineViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        val currentItem = timelineItems[position]
        holder.yearTextView.text = currentItem.year
        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.description
    }

    override fun getItemCount() = timelineItems.size
}
