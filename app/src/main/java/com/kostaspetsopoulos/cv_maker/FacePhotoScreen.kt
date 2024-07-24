package com.kostaspetsopoulos.cv_maker

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker

class FacePhotoScreen : Fragment() {
    lateinit var imageView: ImageView
    lateinit var button: ImageView
    private var imageUri: Uri? = null
    private lateinit var viewModel: ResumeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Top Bar Navigation Inflater
        val topBarLayout = layoutInflater.inflate(R.layout.top_bar_navigation, null) as LinearLayout
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment2)
        frameLayout.addView(topBarLayout, 0) // Add the top bar at the top of the FrameLayout

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Set the appropriate drawables for circle1 and circle2
        val circle2 = topBarLayout.findViewById<ImageView>(R.id.circle2)
        circle2.setImageResource(R.drawable.tab_icon)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.face_photo, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        val btnNxt = view.findViewById<ImageButton>(R.id.next_btn)
        btnNxt.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment3)
        }

        val btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment1)
        }

        imageView = view.findViewById(R.id.imageView)
        button = view.findViewById(R.id.addPhoto_btn)
        button.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

        // Restore the image if available
        viewModel.getProfileImageUri()?.let { uri ->
            imageView.setImageURI(uri)
        }

        // Check if the first name and last name are still there
        Log.d("FacePhotoScreen", "First Name from ViewModel: ${viewModel.firstName}, Last Name from ViewModel: ${viewModel.lastName}")

        return view
    }

    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data

        if (resultCode == Activity.RESULT_OK) {
            // Image Uri will not be null for RESULT_OK
            val fileUri = data?.data!!

            imageUri = fileUri
            imageView.setImageURI(fileUri)

            // Update ViewModel with the selected image Uri
            viewModel.setProfileImageUri(fileUri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(activity, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}
