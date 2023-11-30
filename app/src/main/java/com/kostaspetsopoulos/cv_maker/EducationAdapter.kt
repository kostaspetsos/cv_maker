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

class EducationAdapter(private val viewModel: ResumeViewModel, private var degrees: List<DegreeData>) :
    RecyclerView.Adapter<EducationAdapter.DegreesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DegreesViewHolder {
        Log.d("EducationAdapter", "onCreateViewHolder called")
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.degree_view, parent, false)
        return DegreesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DegreesViewHolder, position: Int) {
        Log.d("EducationAdapter", "onBindViewHolder called for position $position")
        holder.bind(degrees[position])
    }

    override fun getItemCount(): Int {
        Log.d("EducationAdapter", "getItemCount called")
        return degrees.size
    }

    fun updateData(newData: List<DegreeData>) {
        degrees = newData
        notifyDataSetChanged()
    }

    inner class DegreesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val schoolUniTitle: EditText = itemView.findViewById(R.id.editTextSchoolUniversity_input)
        private val degreeTitle: EditText = itemView.findViewById(R.id.editTextDegreeCourse_input)
        private val degreeGrade: EditText = itemView.findViewById(R.id.editTextGrade_input)
        private val timePeriod: EditText = itemView.findViewById(R.id.editTextTime_input)
        private val degreeDelete: ImageButton = itemView.findViewById(R.id.btn_remove)

        init {
            degreeDelete.setOnClickListener {
                Log.d("EducationAdapter", "Delete button clicked at position $adapterPosition")
                viewModel.removeDegreeData(degrees[adapterPosition])
            }
        }

        fun bind(degreeData: DegreeData) {
            Log.d("EducationAdapter", "Binding data for position $adapterPosition")
            schoolUniTitle.setText(degreeData.schoolUniTitle)
            degreeTitle.setText(degreeData.degreeTitle)
            degreeGrade.setText(degreeData.degreeGrade)
            timePeriod.setText(degreeData.degreePeriod)

            schoolUniTitle.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    degreeData.schoolUniTitle = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            degreeTitle.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    degreeData.degreeTitle = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            degreeGrade.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    degreeData.degreeGrade = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            timePeriod.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    degreeData.degreePeriod = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

}







