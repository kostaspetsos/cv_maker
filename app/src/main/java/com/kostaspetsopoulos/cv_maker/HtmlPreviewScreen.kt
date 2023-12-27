package com.kostaspetsopoulos.cv_maker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class HtmlPreviewScreen : Fragment() {
    private lateinit var viewModel: ResumeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.html_preview, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ResumeViewModel::class.java)

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

        // Enable wide viewport support (Zoomed Out)
        webView.settings.useWideViewPort = true
        // Set the initial scale (zoom level)
        webView.setInitialScale(0)

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
        webView.loadDataWithBaseURL(null, htmlGenerator.getFilledTemplate(), "text/html", "UTF-8", null)

        return view
    }

    private fun logAllUserData() {
        // Your existing logAllUserData function remains unchanged
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}
