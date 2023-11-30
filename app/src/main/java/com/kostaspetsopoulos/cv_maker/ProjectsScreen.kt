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

class ProjectsScreen : Fragment() {
    private lateinit var addBtn: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var projectsAdapter: ProjectsAdapter
    private lateinit var viewModel: ResumeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.projects, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        val btnNxt = view.findViewById<ImageButton>(R.id.next_btn)
        btnNxt.setOnClickListener {
            findNavController().navigate(R.id.action_fragment6_to_fragment7)
        }

        val btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            findNavController().navigate(R.id.action_fragment6_to_fragment5)
        }

        addBtn = view.findViewById(R.id.addProjectBtn)
        recyclerView = view.findViewById(R.id.recyclerView_Projects)

        val emptyMessage = view.findViewById<TextView>(R.id.emptyMessage)

        projectsAdapter = ProjectsAdapter(viewModel, viewModel.projectList.value ?: mutableListOf())

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = projectsAdapter

        addBtn.setOnClickListener { addData() }

        viewModel.projectList.observe(viewLifecycleOwner) { projectsDataList ->
            projectsAdapter.updateData(projectsDataList)
            updateEmptyMessageVisibility(projectsDataList.isEmpty(), emptyMessage)
        }

        Log.d("ProjectsScreen", "Data saved: First Name: ${viewModel.firstName}, Last Name: ${viewModel.lastName}")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top Bar Navigation Inflater
        val topBarLayout = layoutInflater.inflate(R.layout.top_bar_navigation, null) as LinearLayout
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment6)
        frameLayout.addView(topBarLayout, 0) // Add the top bar at the top of the FrameLayout

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Set the appropriate drawables for circle1 and circle2
        val circle6 = topBarLayout.findViewById<ImageView>(R.id.circle6)

        circle6.setImageResource(R.drawable.tab_icon)
    }

    private fun addData() {
        viewModel.addProject(ProjectData("",""))
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

