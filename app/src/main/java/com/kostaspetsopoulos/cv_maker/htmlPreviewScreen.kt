package com.kostaspetsopoulos.cv_maker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class htmlPreviewScreen : Fragment() {
    private lateinit var viewModel: ResumeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.html_preview, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        var btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            findNavController().navigate(R.id.action_fragment9_to_fragment8)
        }

        logAllUserData()

        // Preview HTML on Screen
        val webView = view.findViewById<WebView>(R.id.preview_WebView)
        val htmlGenerator = HtmlGenerator(requireContext(), viewModel, webView)
        htmlGenerator.generateHtml()

        return view

    }

   private fun logAllUserData() {
        val viewModel: ResumeViewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Using reflection to get all properties and values
        val properties = viewModel.javaClass.declaredFields
        for (property in properties) {
            property.isAccessible = true
            val value = property.get(viewModel)
            println("${property.name}: $value")
        }

        //Log gia workExperienceList
        val workExperienceList = viewModel.workExperienceList.value

        if (workExperienceList != null) {
            for (item in workExperienceList) {
                Log.d("ViewModel", "_workExperienceList item: $item")
            }
        } else {
            Log.d("ViewModel", "_workExperienceList is null")
        }


        //Log gia projectList
        val projectList = viewModel.projectList.value

        if (projectList != null) {
            for (item in projectList) {
                Log.d("ViewModel", "projectList item: $item")
            }
        } else {
            Log.d("ViewModel", "projectList is null")
        }


        //Log gia degreeDataList
        val degreeDataList = viewModel.degreeDataList.value

        if (degreeDataList != null) {
            for (item in degreeDataList) {
                Log.d("ViewModel", "degreeDataList item: $item")
            }
        } else {
            Log.d("ViewModel", "degreeDataList is null")
        }
    }
}