package com.kostaspetsopoulos.cv_maker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkExperienceScreen : Fragment() {
    private lateinit var addBtn: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var workExperienceAdapter: WorkExperienceAdapter
    private lateinit var viewModel: ResumeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.work_experience, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)


        val btnNxt = view.findViewById<ImageButton>(R.id.next_btn)
        btnNxt.setOnClickListener {
            // Navigate to the next fragment
            findNavController().navigate(R.id.action_fragment5_to_fragment6)
        }

        val btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            // Navigate to the previous fragment
            findNavController().navigate(R.id.action_fragment5_to_fragment4)
        }

        addBtn = view.findViewById(R.id.addWorkBtn)
        recyclerView = view.findViewById(R.id.recyclerView_WorkExperience)

        val emptyMessage = view.findViewById<TextView>(R.id.emptyMessage)

        workExperienceAdapter = WorkExperienceAdapter(viewModel, viewModel.workExperienceList.value ?: mutableListOf())

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = workExperienceAdapter

        addBtn.setOnClickListener { addData() }

        viewModel.workExperienceList.observe(viewLifecycleOwner) { workExperienceDataList ->
            workExperienceAdapter.updateData(workExperienceDataList)
            updateEmptyMessageVisibility(workExperienceDataList.isEmpty(), emptyMessage)
        }

        Log.d("WorkExperiecneScreen", "Data saved: First Name: ${viewModel.firstName}, Last Name: ${viewModel.lastName}")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top Bar Navigation Inflater
        val topBarLayout = layoutInflater.inflate(R.layout.top_bar_navigation, null) as LinearLayout
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment5)
        frameLayout.addView(topBarLayout, 0) // Add the top bar at the top of the FrameLayout

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Set the appropriate drawables for circle1 and circle2
        val circle4 = topBarLayout.findViewById<ImageView>(R.id.circle5)

        circle4.setImageResource(R.drawable.tab_icon)
    }

    private fun addData() {
        viewModel.addWorkExperience(WorkExperienceData("", "", "", ""))
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


