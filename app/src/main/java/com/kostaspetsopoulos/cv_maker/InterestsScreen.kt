package com.kostaspetsopoulos.cv_maker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class InterestsScreen : Fragment() {
    private lateinit var viewModel: ResumeViewModel
    private lateinit var interests: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top Bar Navigation Inflater
        val topBarLayout = layoutInflater.inflate(R.layout.top_bar_navigation, null) as LinearLayout
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment8)
        frameLayout.addView(topBarLayout, 0) // Add the top bar at the top of the FrameLayout

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Set the appropriate drawables for circle1 and circle2
        val circle8 = topBarLayout.findViewById<ImageView>(R.id.circle8)

        circle8.setImageResource(R.drawable.tab_icon)
    }

    override fun onPause() {
        super.onPause()
        // Save ViewModel data when leaving the fragment
        viewModel.interests = interests.text.toString()
        Log.d("InterestsScreen", "Data saved: Interests: ${viewModel.interests}")
    }


    override fun onResume() {
        super.onResume()
        // Restore ViewModel data when returning to the fragment
        interests.setText(viewModel.interests)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.interests, container, false)

        interests = view.findViewById(R.id.editTextInterests_input)

        val btnNxt = view.findViewById<ImageButton>(R.id.preview_btn)
        btnNxt.setOnClickListener {
            Log.d("Before DataToHTML", "Before calling dataToHTML")
            //dataToHTML()
            Log.d("After DataToHTML", "After calling dataToHTML")
            findNavController().navigate(R.id.action_fragment8_to_fragment9)
        }

        val btnBck = view.findViewById<ImageButton>(R.id.back_btn)

        btnBck.setOnClickListener {
            findNavController().navigate(R.id.action_fragment8_to_fragment7)
        }


        return view
    }

}