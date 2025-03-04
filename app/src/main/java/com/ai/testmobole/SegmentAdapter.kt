package com.ai.testmobole

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ai.testmobole.model.Segment

class SegmentAdapter : RecyclerView.Adapter<SegmentViewHolder>() {
    private var segments: List<Segment> = emptyList()

    fun setSegments(segments: List<Segment>) {
        this.segments = segments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SegmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_segment, parent, false)
        return SegmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: SegmentViewHolder, position: Int) {
        holder.bind(segments[position])
    }

    override fun getItemCount(): Int = segments.size
}

class SegmentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(segment: Segment) {
        val textView = view.findViewById<TextView>(R.id.segment_text)
        textView.text = "${segment.originAndDestinationPair.origin.displayName} to ${segment.originAndDestinationPair.destination.displayName}"
    }
}