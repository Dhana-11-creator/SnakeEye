import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.snakeeye.R
import com.example.snakeeye.SnakeActivity

class RecentActivityAdapter(
    private val activities: List<SnakeActivity>,
    private val onItemClick: (SnakeActivity) -> Unit = {}
) : RecyclerView.Adapter<RecentActivityAdapter.ActivityViewHolder>() {

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val activityIcon: TextView = itemView.findViewById(R.id.activityIcon)
        private val activityTitle: TextView = itemView.findViewById(R.id.activityTitle)
        private val activityType: TextView = itemView.findViewById(R.id.activityType)
        private val venomousStatus: TextView = itemView.findViewById(R.id.venomousStatus)
        private val activityLocation: TextView = itemView.findViewById(R.id.activityLocation)
        private val activityReporter: TextView = itemView.findViewById(R.id.activityReporter)
        private val activityTime: TextView = itemView.findViewById(R.id.activityTime)

        fun bind(activity: SnakeActivity) {
            activityIcon.text = activity.icon
            activityTitle.text = activity.title
            activityType.text = activity.activityType.displayName
            venomousStatus.text = activity.venomousCategory.displayName
            activityLocation.text = activity.location
            activityReporter.text = "Reported by ${activity.reporter}"
            activityTime.text = activity.timeAgo

            // Set activity type background color
            val activityTypeBackground = GradientDrawable()
            activityTypeBackground.shape = GradientDrawable.RECTANGLE
            activityTypeBackground.cornerRadius = 12f
            activityTypeBackground.setColor(Color.parseColor("#E0E0E0"))
            activityType.background = activityTypeBackground

            // Set venomous status background color
            val venomousBackground = GradientDrawable()
            venomousBackground.shape = GradientDrawable.RECTANGLE
            venomousBackground.cornerRadius = 12f
            venomousBackground.setColor(Color.parseColor(activity.venomousCategory.color))
            venomousStatus.background = venomousBackground

            itemView.setOnClickListener {
                onItemClick(activity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recent_activity, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(activities[position])
    }

    override fun getItemCount(): Int = activities.size
}