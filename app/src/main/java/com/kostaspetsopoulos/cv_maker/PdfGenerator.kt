import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.layout.Document
import com.itextpdf.layout.font.FontProvider
import com.kostaspetsopoulos.cv_maker.BuildConfig
import com.kostaspetsopoulos.cv_maker.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PdfGenerator(private val context: Context) {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123
    }

    fun generatePdf(htmlContent: String, fileName: String) {
        if (checkWriteExternalStoragePermission()) {
            savePdf(htmlContent, fileName)
        } else {
            requestWriteExternalStoragePermission()
        }
    }

    private fun savePdf(htmlContent: String, fileName: String) {
        val folderName = "generatedCVs" // New folder name
        val folder = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) // Use app-specific external storage directory

        if (folder != null && !folder.exists()) {
            folder.mkdirs() // Create the folder if it doesn't exist
        }

        val filePath = File(folder, fileName)

        try {
            // Create a PdfDocument with A4 page size
            val writer = PdfWriter(filePath)
            val pdfDoc = PdfDocument(writer)
            pdfDoc.defaultPageSize = PageSize.A4

            // Create Document and set margins
            val document = Document(pdfDoc)
            document.setMargins(0f, 0f, 0f, 0f)

            val properties = ConverterProperties()
            properties.baseUri = "/"
            HtmlConverter.convertToPdf(htmlContent, pdfDoc, properties)

            // Close the document
            document.close()

            // Show a success message on the screen using Toast
            showToast("PDF generated successfully: ${filePath.absolutePath}")

            // Open the generated PDF automatically
            openPdf(filePath)

            // Log the saved location
            Log.d("PdfGenerator", "PDF saved at: ${filePath.absolutePath}")
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
        // Generate a content URI for the file using FileProvider
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )


        // Create an intent to view the PDF file
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        // Start the activity to view the PDF file
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            showToast("No PDF viewer found")
        }
    }

    private fun checkWriteExternalStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestWriteExternalStoragePermission() {
        ActivityCompat.requestPermissions(
            context as AppCompatActivity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
        )
    }
}
