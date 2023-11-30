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


class NameSurnameScreen : Fragment() {
    private lateinit var viewModel: ResumeViewModel
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var dateofBirthEditText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top Bar Navigation Inflater
        val topBarLayout = layoutInflater.inflate(R.layout.top_bar_navigation, null) as LinearLayout
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment1)
        frameLayout.addView(topBarLayout, 0) // Add the top bar at the top of the FrameLayout

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Set the appropriate drawables for circle1 and circle2
        val circle1 = topBarLayout.findViewById<ImageView>(R.id.circle1)

        circle1.setImageResource(R.drawable.tab_icon)
    }

    override fun onPause() {
        super.onPause()
        // Save ViewModel data when leaving the fragment
        viewModel.firstName = nameEditText.text.toString()
        viewModel.lastName = surnameEditText.text.toString()
        viewModel.dateOfBirth = dateofBirthEditText.text.toString()
    }

    override fun onResume() {
        super.onResume()
        // Restore ViewModel data when returning to the fragment
        nameEditText.setText(viewModel.firstName)
        surnameEditText.setText(viewModel.lastName)
        dateofBirthEditText.setText(viewModel.dateOfBirth)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savedName", viewModel.firstName)
        outState.putString("savedSurname", viewModel.lastName)
        outState.putString("savedDateOfBirth", viewModel.dateOfBirth)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("NameSurnameScreen", "Fragment Created")
        val view = inflater.inflate(R.layout.name_surname, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        nameEditText = view.findViewById(R.id.editTextTextPersonName)
        surnameEditText = view.findViewById(R.id.editTextTextPersonName2)
        dateofBirthEditText = view.findViewById(R.id.editTextTextDateOfBirth)

        val btnNxt = view.findViewById<ImageButton>(R.id.next_btn)
        btnNxt.setOnClickListener {
            viewModel.firstName = nameEditText.text.toString()
            viewModel.lastName = surnameEditText.text.toString()
            viewModel.dateOfBirth = dateofBirthEditText.text.toString()
            Log.d("NameSurnameScreen", "Data saved: First Name: ${viewModel.firstName}, Last Name: ${viewModel.lastName}")
            findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }

        val btnCancel = view.findViewById<ImageButton>(R.id.back_btn)
        btnCancel.setOnClickListener {
            Log.d("NameSurnameScreen", "Cancel Button Pressed.   Heading back to templates..")
            findNavController().navigate(R.id.action_fragment1_to_templatesFragment)
        }

        // Restore the saved text if available
        savedInstanceState?.let {
            val savedName = it.getString("savedName", "")
            val savedSurname = it.getString("savedSurname", "")
            val savedDateOfBirth = it.getString("savedDateOfBirth", "")
            nameEditText.setText(savedName)
            surnameEditText.setText(savedSurname)
            dateofBirthEditText.setText(savedDateOfBirth)
            Log.d("NameSurnameScreen", "Data restored: First Name: $savedName, Last Name: $savedSurname")
        }

        return view
    }


}
