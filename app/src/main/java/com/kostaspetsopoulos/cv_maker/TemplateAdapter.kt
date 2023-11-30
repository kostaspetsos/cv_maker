package com.kostaspetsopoulos.cv_maker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TemplateAdapter(private val templateList: List<TemplateItem>, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_item_view, parent, false)
        return TemplateViewHolder(view)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        holder.bind()

        // Set a click listener for each item
        holder.itemView.setOnClickListener {
            onItemClick.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return templateList.size
    }

    class TemplateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            // You can perform any binding or customization here
            // As per your request, I'm leaving it empty for now
        }
    }
}

