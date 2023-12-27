import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kostaspetsopoulos.cv_maker.R
import com.kostaspetsopoulos.cv_maker.TemplateItem

class TemplateAdapter(private val templateList: List<TemplateItem>, private val onItemClick: (TemplateItem) -> Unit) :
    RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_item_view, parent, false)
        return TemplateViewHolder(view)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        // Bind the data for each item
        holder.bind(templateList[position])

        // Set a click listener for each item
        holder.itemView.setOnClickListener {
            onItemClick.invoke(templateList[position])
        }
    }

    override fun getItemCount(): Int {
        return templateList.size
    }

    class TemplateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val templateBackground: ImageView = itemView.findViewById(R.id.templateBackground)

        fun bind(templateItem: TemplateItem) {
            // Set the template background image
            templateBackground.setImageResource(templateItem.imageResourceId)
        }
    }
}


