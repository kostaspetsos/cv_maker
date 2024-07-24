package com.kostaspetsopoulos.cv_maker

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.util.Log
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

        // Retrieve the selected template name from SharedPreferences
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val selectedTemplateName =
            sharedPreferences.getString("selected_template", "Template 1") ?: "Template 1"

        var templateNameForWebview = "";

        // Preview HTML on Screen
        val webView = view.findViewById<WebView>(R.id.preview_WebView)
        val hiddenWebview = view.findViewById<WebView>(R.id.preview_hidden_WebView)

        // Configure WebView settings
        webView.settings.apply {
            builtInZoomControls = true
            displayZoomControls = false
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            allowFileAccess = true
            cacheMode = WebSettings.LOAD_DEFAULT
            userAgentString =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"

            // Enable support for loading custom fonts
            setSupportZoom(true) // Optional: Enable zooming
            builtInZoomControls = true // Optional: Enable built-in zoom controls
            displayZoomControls = false // Optional: Hide zoom controls
            allowFileAccess = true // Allow access to files
            allowContentAccess = true // Allow access to the content URL



            // Apply font settings based on the selected template
            when (selectedTemplateName) {
                "Template 1", "Template 2", "Template 5", "Template 3", "Template 4", "Template 6"  -> {

                    val fontPath = "file:///android_res/font/jost.ttf"
                    val fontFamily = "Jost"
                    setFontFamily(webView, fontPath, fontFamily)
                }
                /*"Template 4" -> {

                    val fontPath = "file:///android_res/font/cambria.ttf"
                    val fontFamily = "Cambria"
                    setFontFamily(webView, fontPath, fontFamily)
                }  */
                else -> {
                    // Default font settings
                    // For any other template, use default font settings
                }
            }
        }

        // Apply font settings based on the selected template
        templateNameForWebview = when (selectedTemplateName) {
            "Template 6", "Template 7" -> {
                "$selectedTemplateName Responsive";
            }
            else -> {
                "$selectedTemplateName";
            }
        }

        // Set the initial scale (zoom level) to 100%
        webView.setInitialScale(100)

        val htmlGenerator = HtmlGenerator(requireContext(), viewModel, webView, templateNameForWebview)
        htmlGenerator.generateHtml()


        val btnBck = view.findViewById<ImageButton>(R.id.back_btn)
        btnBck.setOnClickListener {
            findNavController().navigate(R.id.action_fragment9_to_fragment8)
        }


        // Generate PDF when the "Make PDF" button is clicked
        val makePdfBtn = view.findViewById<ImageButton>(R.id.makePDFbtn)
        makePdfBtn.setOnClickListener {
            val htmlGeneratorForExport = HtmlGenerator(requireContext(), viewModel, hiddenWebview,
                "$selectedTemplateName"
            )
            htmlGeneratorForExport.generateHtml()

            val htmlContent = htmlGeneratorForExport.getFilledTemplate()

            // Get the user's first name and last name from the ViewModel
            val sanitizedFirstName =
                viewModel.firstName?.replace("\\s".toRegex(), "_") ?: "Unknown"
            val sanitizedLastName =
                viewModel.lastName?.replace("\\s".toRegex(), "_") ?: "Unknown"

            // Create a filename based on the user's first name, last name, and timestamp
            val timestamp = System.currentTimeMillis()
            val fileName =
                "${sanitizedFirstName}_${sanitizedLastName}_cv_$timestamp.pdf"

            try {
                htmlGeneratorForExport.generatePdf(htmlContent, fileName)
            } catch (e: Exception) {
                e.printStackTrace()
                showSnackbar("Error generating PDF")
            }
        }

        return view
    }

    private fun setFontFamily(webView: WebView, fontPath: String, fontFamily: String) {
        val javascript = "var style = document.createElement('style');" +
                "style.innerHTML = '@font-face { font-family: $fontFamily; src: url($fontPath); }';" +
                "document.head.appendChild(style);"
        webView.evaluateJavascript(javascript, null)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Listener for the documents_folder button
        val documentsFolderButton = view.findViewById<ImageButton>(R.id.documents_folder)
        documentsFolderButton.setOnClickListener {
            openFileExplorerInDocumentsFolder()
        }
    }

    private fun openFileExplorerInDocumentsFolder() {
        val documentsUri = Uri.parse("content://com.android.externalstorage.documents/document/primary:Documents")

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*" // Set the MIME type to match all files
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, documentsUri)
        }

        try {
            startActivityForResult(intent, REQUEST_CODE_PICK_DIRECTORY)
        } catch (e: ActivityNotFoundException) {
            Log.e("HtmlPreviewScreen", "No file manager app found", e)
            showSnackbar("No file manager app found")
        }
    }


}
