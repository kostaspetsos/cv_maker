package com.kostaspetsopoulos.cv_maker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EducationScreen : Fragment() {
    private lateinit var addBtn: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var educationAdapter: EducationAdapter
    private lateinit var viewModel: ResumeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.education, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)


        val btnNxt = view.findViewById<ImageButton>(R.id.next_btn)
        btnNxt.setOnClickListener {
            // Navigate to the next fragment
            findNavController().navigate(R.id.action_fragment4_to_fragment5)
        }

        val btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            // Navigate to the previous fragment
            findNavController().navigate(R.id.action_fragment4_to_fragment3)
        }

        addBtn = view.findViewById(R.id.addBtn)
        recyclerView = view.findViewById(R.id.recyclerView_Degrees)

        val emptyMessage = view.findViewById<TextView>(R.id.emptyMessage)

        educationAdapter = EducationAdapter(viewModel, viewModel.degreeDataList.value ?: mutableListOf())

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = educationAdapter

        addBtn.setOnClickListener { addData() }

        viewModel.degreeDataList.observe(viewLifecycleOwner) { degreeDataList ->
            educationAdapter.updateData(degreeDataList)
            updateEmptyMessageVisibility(degreeDataList.isEmpty(), emptyMessage)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top Bar Navigation Inflater
        val topBarLayout = layoutInflater.inflate(R.layout.top_bar_navigation, null) as LinearLayout
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment4)
        frameLayout.addView(topBarLayout, 0) // Add the top bar at the top of the FrameLayout

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Set the appropriate drawables for circle1 and circle2
        val circle4 = topBarLayout.findViewById<ImageView>(R.id.circle4)

        circle4.setImageResource(R.drawable.tab_icon)
    }


    private fun addData() {
        viewModel.addDegreeData(DegreeData("","", "", ""))
    }

    private fun updateEmptyMessageVisibility(isEmpty: Boolean, emptyMessage: TextView) {
        if (isEmpty) {
            emptyMessage.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyMessage.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
}


data class DegreeData(
    var schoolUniTitle: String,
    var degreeTitle: String,
    var degreeGrade: String,
    var degreePeriod: String
)




