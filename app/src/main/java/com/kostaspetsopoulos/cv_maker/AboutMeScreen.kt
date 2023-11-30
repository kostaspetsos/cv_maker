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

class AboutMeScreen : Fragment() {
    private lateinit var viewModel: ResumeViewModel
    private lateinit var aboutMe: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top Bar Navigation Inflater
        val topBarLayout = layoutInflater.inflate(R.layout.top_bar_navigation, null) as LinearLayout
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment7)
        frameLayout.addView(topBarLayout, 0) // Add the top bar at the top of the FrameLayout

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Set the appropriate drawables for circle1 and circle2
        val circle7 = topBarLayout.findViewById<ImageView>(R.id.circle7)

        circle7.setImageResource(R.drawable.tab_icon)
    }

    override fun onPause() {
        super.onPause()
        // Save ViewModel data when leaving the fragment
        viewModel.aboutMe = aboutMe.text.toString()
    }

    override fun onResume() {
        super.onResume()
        // Restore ViewModel data when returning to the fragment
        aboutMe.setText(viewModel.aboutMe)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.about_me, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        aboutMe = view.findViewById(R.id.editTextAboutMe_input)

        val btnNxt = view.findViewById<ImageButton>(R.id.next_btn)
        btnNxt.setOnClickListener {
            findNavController().navigate(R.id.action_fragment7_to_fragment8)
        }

        val btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            findNavController().navigate(R.id.action_fragment7_to_fragment6)
        }

        Log.d("AboutMeScreen", "Data saved: First Name: ${viewModel.firstName}, Last Name: ${viewModel.lastName}")
        return view
    }

}
