package com.example.fercstroy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fercstroy.R

data class Feature(val title: String, val description: String, val imageResId: Int)

class FeatureAdapter(private val features: List<Feature>) : RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feature, parent, false)
        return FeatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val feature = features[position]
        holder.titleTextView.text = feature.title
        holder.descriptionTextView.text = feature.description
        holder.imageView.setImageResource(feature.imageResId)
    }

    override fun getItemCount(): Int = features.size

    class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.featureImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.featureTitleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.featureDescriptionTextView)
    }
}
