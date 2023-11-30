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

class PersonalInfoScreen : Fragment() {
    private lateinit var viewModel: ResumeViewModel

    private lateinit var phoneNumberEditText: EditText
    private lateinit var contactLinkEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var emailEditText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top Bar Navigation Inflater
        val topBarLayout = layoutInflater.inflate(R.layout.top_bar_navigation, null) as LinearLayout
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment3)
        frameLayout.addView(topBarLayout, 0) // Add the top bar at the top of the FrameLayout

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Set the appropriate drawables for circle1 and circle2
        val circle3 = topBarLayout.findViewById<ImageView>(R.id.circle3)

        circle3.setImageResource(R.drawable.tab_icon)
    }

    override fun onPause() {
        super.onPause()
        // Save ViewModel data when leaving the fragment
        viewModel.phoneNumber = phoneNumberEditText.text.toString()
        viewModel.contactLink = contactLinkEditText.text.toString()
        viewModel.address = addressEditText.text.toString()
        viewModel.email = emailEditText.text.toString()
    }

    override fun onResume() {
        super.onResume()
        // Restore ViewModel data when returning to the fragment
        phoneNumberEditText.setText(viewModel.phoneNumber)
        contactLinkEditText.setText(viewModel.contactLink)
        addressEditText.setText(viewModel.address)
        emailEditText.setText(viewModel.email)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.personal_info, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        phoneNumberEditText = view.findViewById(R.id.editTextPhoneNumber)
        contactLinkEditText = view.findViewById(R.id.editTextContactLink)
        addressEditText = view.findViewById(R.id.editTextAddress)
        emailEditText = view.findViewById(R.id.editTextEmail)

        val btnNxt = view.findViewById<ImageButton>(R.id.next_btn)
        btnNxt.setOnClickListener {
            findNavController().navigate(R.id.action_fragment3_to_fragment4)
        }

        var btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            findNavController().navigate(R.id.action_fragment3_to_fragment2)
        }

        Log.d("NameSurnameScreen", "Data saved: First Name: ${viewModel.firstName}, Last Name: ${viewModel.lastName}")

        return view

    }


}