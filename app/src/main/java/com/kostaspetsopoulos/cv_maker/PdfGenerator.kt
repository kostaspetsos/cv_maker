package com.kostaspetsopoulos.cv_maker

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import com.itextpdf.html2pdf.HtmlConverter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PdfGenerator(private val context: Context) {

    fun generatePdf(htmlContent: String, fileName: String) {
        val filePath = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "$fileName")

        try {
            // Create an output stream
            val outputStream = FileOutputStream(filePath)

            // Use HtmlConverter to convert HTML content to PDF
            HtmlConverter.convertToPdf(htmlContent, outputStream)

            // Close the output stream
            outputStream.close()

            // Show a success message on the screen using Toast
            showToast("PDF generated successfully: ${filePath.absolutePath}")

            // Open the generated PDF automatically
            openPdf(filePath)
        } catch (e: IOException) {
            e.printStackTrace()

            // Show an error message on the screen using Toast
            showToast("Error generating PDF")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun openPdf(file: File) {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            showToast("No PDF viewer found")
        }
    }

}
