package com.kostaspetsopoulos.cv_maker

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import java.io.File

class HtmlPreviewScreen : Fragment() {
    private lateinit var viewModel: ResumeViewModel
    companion object {
        private const val REQUEST_CODE_PICK_DIRECTORY = 123
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.html_preview, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

        // Listener for the documents_folder button
        val documentsFolder = view?.findViewById<ImageButton>(R.id.documents_folder)
        documentsFolder?.setOnClickListener {
            Log.d("HtmlPreviewScreen", "documentsFolder button clicked")

            // Open file manager to select a directory
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION

            try {
                startActivityForResult(intent, REQUEST_CODE_PICK_DIRECTORY)
                Log.d("HtmlPreviewScreen", "File manager opened successfully")
            } catch (e: ActivityNotFoundException) {
                Log.e("HtmlPreviewScreen", "No file manager app found", e)
                showSnackbar("No file manager app found")
            }
        }






        val btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            findNavController().navigate(R.id.action_fragment9_to_fragment8)
        }

        // Log user data for debugging
        logAllUserData()

        // Preview HTML on Screen
        val webView = view.findViewById<WebView>(R.id.preview_WebView)

        // Configure WebView settings
        val webSettings: WebSettings = webView.settings.apply {
            builtInZoomControls = true
            displayZoomControls = false
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            cacheMode = WebSettings.LOAD_DEFAULT
            userAgentString =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
        }
        // Set the initial scale (zoom level) to 100%
        webView.setInitialScale(100)



        webView.settings.allowFileAccess = true
        webView.webChromeClient = null


        val htmlGenerator = HtmlGenerator(requireContext(), viewModel, webView)
        htmlGenerator.generateHtml()

        // Generate PDF when the "Make PDF" button is clicked
        val pdfGenerator = HtmlGenerator(requireContext(), viewModel, webView)

        val makePdfBtn = view.findViewById<ImageButton>(R.id.makePDFbtn)
        makePdfBtn.setOnClickListener {
            val htmlContent = htmlGenerator.getFilledTemplate() // Modify this according to your implementation

            // Get the user's first name and last name from the ViewModel
            val sanitizedFirstName =
                viewModel.firstName?.replace("\\s".toRegex(), "_") ?: "Unknown"
            val sanitizedLastName =
                viewModel.lastName?.replace("\\s".toRegex(), "_") ?: "Unknown"

            // Create a filename based on the user's first name, last name, and timestamp
            val timestamp = System.currentTimeMillis()
            val fileName =
                "${sanitizedFirstName}_${sanitizedLastName}_cv_$timestamp.pdf"

            val fontPath = "file:///android_res/font/cambria.ttf"
            val fontFamily = "Cambria"

            try {
                pdfGenerator.generatePdf(htmlContent, fileName)
            } catch (e: Exception) {
                e.printStackTrace()
                showSnackbar("Error generating PDF")
            }
        }

        // Load the HTML content into the WebView and inject styles using JavaScript
        val htmlContent = htmlGenerator.getFilledTemplate()
        val styledHtmlContent = "<meta name=\"viewport\" content=\"width=450\">$htmlContent"
        webView.loadDataWithBaseURL(null, styledHtmlContent, "text/html", "UTF-8", null)

        return view
    }

    private fun logAllUserData() {
        // Your existing logAllUserData function remains unchanged
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_DIRECTORY && resultCode == Activity.RESULT_OK && data != null) {
            // Handle the directory selection here
            val uri = data.data
            Log.d("HtmlPreviewScreen", "Selected directory URI: $uri")

            // Save the selected directory URI
            saveSelectedDirectoryUri(uri)
        }
    }


    private fun saveSelectedDirectoryUri(uri: Uri?) {
        // Save the selected directory URI to SharedPreferences or any other storage mechanism
        if (uri != null) {
            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("selected_directory_uri", uri.toString())
                apply()
            }
            Log.d("HtmlPreviewScreen", "Selected directory URI saved: $uri")
        } else {
            Log.e("HtmlPreviewScreen", "Selected directory URI is null")
        }
    }


}
