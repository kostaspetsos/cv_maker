// HtmlGenerator.kt
package com.kostaspetsopoulos.cv_maker

import PdfGenerator
import android.content.Context
import android.webkit.WebView
import java.io.IOException

class HtmlGenerator(
    private val context: Context,
    private val viewModel: ResumeViewModel,
    private val webView: WebView
) {

    fun generateHtml() {
        try {
            // Retrieve the selected template name from SharedPreferences
            val sharedPreferences =
                context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val selectedTemplateName =
                sharedPreferences.getString("selected_template", "Template 1")

            // Create the selected template using the TemplateFactory
            val selectedTemplate =
                TemplateFactory.createTemplate(selectedTemplateName ?: "")

            // Construct the HTML template with actual data
            val filledTemplate = selectedTemplate.fillTemplate(viewModel)

            // Load the HTML into the WebView
            webView.loadDataWithBaseURL(null, filledTemplate, "text/html", "UTF-8", null)

            println("HTML content loaded into WebView")

        } catch (e: IOException) {
            e.printStackTrace()
            println("Error generating HTML content")
        }
    }

    fun getFilledTemplate(): String {
        // Retrieve the selected template name from SharedPreferences
        val sharedPreferences =
            context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val selectedTemplateName =
            sharedPreferences.getString("selected_template", "Template 1")

        // Create the selected template using the TemplateFactory
        val selectedTemplate =
            TemplateFactory.createTemplate(selectedTemplateName ?: "")

        // Return the filled template
        return selectedTemplate.fillTemplate(viewModel)
    }

    fun generatePdf(htmlContent: String, fileName: String) {
        val pdfGenerator = PdfGenerator(context)
        pdfGenerator.generatePdf(htmlContent, fileName)
    }
}
