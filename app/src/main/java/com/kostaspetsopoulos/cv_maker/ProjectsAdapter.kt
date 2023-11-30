package com.kostaspetsopoulos.cv_maker

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class ProjectsAdapter(private val viewModel: ResumeViewModel, private var projects: List<ProjectData>) :
    RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        Log.d("ProjectsAdapter", "onCreateViewHolder called")
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.projects_view, parent, false)
        return ProjectsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        Log.d("ProjectsAdapter", "onBindViewHolder called for position $position")
        holder.bind(projects[position])
    }

    override fun getItemCount(): Int {
        Log.d("ProjectsAdapter", "getItemCount called")
        return projects.size
    }

    fun updateData(newData: List<ProjectData>) {
        projects = newData
        notifyDataSetChanged()
    }

    inner class ProjectsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val projectTitle: EditText = itemView.findViewById(R.id.editTextProjectTitle_input)
        private val projectDescription: EditText = itemView.findViewById(R.id.editTextProjectDescription_input)
        private val removeButton: ImageButton = itemView.findViewById(R.id.btn_remove)

        init {
            removeButton.setOnClickListener {
                Log.d("ProjectsAdapter", "Delete button clicked at position $adapterPosition")
                viewModel.removeProject(projects[adapterPosition])
            }
        }

        fun bind(projectData: ProjectData) {
            Log.d("ProjectsAdapter", "Binding data for position $adapterPosition")
            projectTitle.setText(projectData.projectTitle)
            projectDescription.setText(projectData.projectDescription)

            projectTitle.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    projectData.projectTitle = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            projectDescription.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    projectData.projectDescription = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

        }
    }
}

data class ProjectData(
    var projectTitle: String,
    var projectDescription: String
)
