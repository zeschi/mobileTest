import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ai.testmobole.R
import com.ai.testmobole.model.Segment

class SegmentAdapter(private var segments: List<Segment> = emptyList()) :
    RecyclerView.Adapter<SegmentAdapter.SegmentViewHolder>() {

    fun setSegments(newSegments: List<Segment>) {
        segments = newSegments
        notifyDataSetChanged() // Update the UI with new data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SegmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_segment, parent, false)
        return SegmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: SegmentViewHolder, position: Int) {
        holder.bind(segments[position])
    }

    override fun getItemCount(): Int = segments.size

    class SegmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val segmentId: TextView = itemView.findViewById(R.id.segment_id)
        private val originDisplayName: TextView = itemView.findViewById(R.id.origin_display_name)
        private val originCity: TextView = itemView.findViewById(R.id.origin_city)
        private val destinationDisplayName: TextView = itemView.findViewById(R.id.destination_display_name)
        private val destinationCity: TextView = itemView.findViewById(R.id.destination_city)

        fun bind(segment: Segment) {
            val pair = segment.originAndDestinationPair
            segmentId.text = "Segment ${segment.id}"
            originDisplayName.text = "Origin: ${pair.origin.displayName}"
            originCity.text = "Origin City: ${pair.originCity}"
            destinationDisplayName.text = "Destination: ${pair.destination.displayName}"
            destinationCity.text = "Destination City: ${pair.destinationCity}"
        }
    }
}