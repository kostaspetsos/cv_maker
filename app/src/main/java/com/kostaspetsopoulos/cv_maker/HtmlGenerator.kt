// HtmlGenerator.kt
package com.kostaspetsopoulos.cv_maker

import android.content.Context
import android.webkit.WebView
import java.io.IOException

class HtmlGenerator(
    private val context: Context,
    private val viewModel: ResumeViewModel,
    private val webView: WebView,
    private val selectedTemplateName: String // Pass the selected template name here
) {

    fun generateHtml() {
        try {
            // Create the selected template using the TemplateFactory
            val selectedTemplate =
                TemplateFactory.createTemplate(selectedTemplateName)

            // Construct the HTML template with actual data
            val filledTemplate = selectedTemplate.fillTemplate(viewModel)

            val baseUrl = "file:///android_asset/"

            // Load the HTML into the WebView with the base URL
            webView.loadDataWithBaseURL(baseUrl, filledTemplate, "text/html", "UTF-8", null)

            println("HTML content loaded into WebView")

        } catch (e: IOException) {
            e.printStackTrace()
            println("Error generating HTML content")
        }
    }

    fun getFilledTemplate(): String {
        // Create the selected template using the TemplateFactory
        val selectedTemplate =
            TemplateFactory.createTemplate(selectedTemplateName)

        // Return the filled template
        return selectedTemplate.fillTemplate(viewModel)
    }

    fun generatePdf(htmlContent: String, fileName: String) {
        val pdfGenerator = PdfGenerator(context, selectedTemplateName) // Pass the selected template name
        pdfGenerator.generatePdf(htmlContent, fileName)
    }
}
