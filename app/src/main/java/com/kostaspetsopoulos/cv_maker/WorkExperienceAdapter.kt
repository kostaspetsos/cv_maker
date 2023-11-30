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

class WorkExperienceAdapter(private val viewModel: ResumeViewModel, private var workExperiences: List<WorkExperienceData>) :
    RecyclerView.Adapter<WorkExperienceAdapter.WorkExperienceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkExperienceViewHolder {
        Log.d("WorkExperienceAdapter", "onCreateViewHolder called")
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.work_view, parent, false)
        return WorkExperienceViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkExperienceViewHolder, position: Int) {
        Log.d("WorkExperienceAdapter", "onBindViewHolder called for position $position")
        holder.bind(workExperiences[position])
    }

    override fun getItemCount(): Int {
        Log.d("WorkExperienceAdapter", "getItemCount called")
        return workExperiences.size
    }

    fun updateData(newData: List<WorkExperienceData>) {
        workExperiences = newData
        notifyDataSetChanged()
    }

    inner class WorkExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyName: EditText = itemView.findViewById(R.id.editTextCompanyName_input)
        private val jobTitle: EditText = itemView.findViewById(R.id.editTextJobTitle_input)
        private val timePeriod: EditText = itemView.findViewById(R.id.editTextTimePeriod_input)
        private val details: EditText = itemView.findViewById(R.id.editTextDetails_input)
        private val removeButton: ImageButton = itemView.findViewById(R.id.btn_remove)

        init {
            removeButton.setOnClickListener {
                Log.d("WorkExperienceAdapter", "Delete button clicked at position $adapterPosition")
                viewModel.removeWorkExperience(workExperiences[adapterPosition])
            }
        }

        fun bind(workExperienceData: WorkExperienceData) {
            Log.d("WorkExperienceAdapter", "Binding data for position $adapterPosition")
            companyName.setText(workExperienceData.companyName)
            jobTitle.setText(workExperienceData.jobTitle)
            timePeriod.setText(workExperienceData.timePeriod)
            details.setText(workExperienceData.details)

            companyName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    workExperienceData.companyName = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            jobTitle.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    workExperienceData.jobTitle = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            timePeriod.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    workExperienceData.timePeriod = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            details.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    workExperienceData.details = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }
}

data class WorkExperienceData(
    var companyName: String,
    var jobTitle: String,
    var timePeriod: String,
    var details: String
)
