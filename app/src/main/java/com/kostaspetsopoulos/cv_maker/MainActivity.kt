package com.kostaspetsopoulos.cv_maker

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var resumeViewModel: ResumeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        //Kanw disable to Night Mode  NA TO SVHSW ARGOTERA
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        resumeViewModel = ViewModelProvider(this).get(ResumeViewModel::class.java) // Add this line

        Log.d("NameSurnameScreen", "Data saved: First Name: ${resumeViewModel.firstName}, Last Name: ${resumeViewModel.lastName}")




    }

}
