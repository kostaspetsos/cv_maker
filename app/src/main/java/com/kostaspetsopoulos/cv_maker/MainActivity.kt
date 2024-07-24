package com.kostaspetsopoulos.cv_maker

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var resumeViewModel: ResumeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // Disable Night Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        resumeViewModel = ViewModelProvider(this).get(ResumeViewModel::class.java)

        // Load data from SharedPreferences when the app starts
        loadFromPreferences()

        Log.d("NameSurnameScreen", "Data loaded: First Name: ${resumeViewModel.firstName}, Last Name: ${resumeViewModel.lastName}")
    }

    override fun onStop() {
        super.onStop()

        // Save data to SharedPreferences when the app stops
        saveToPreferences()
    }

    private fun saveToPreferences() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("firstName", resumeViewModel.firstName)
            putString("lastName", resumeViewModel.lastName)
            putString("dateOfBirth", resumeViewModel.dateOfBirth)
            putString("profileImageUri", resumeViewModel.profileImageUri)
            putString("phoneNumber", resumeViewModel.phoneNumber)
            putString("contactLink", resumeViewModel.contactLink)
            putString("address", resumeViewModel.address)
            putString("email", resumeViewModel.email)
            putString("aboutMe", resumeViewModel.aboutMe)
            putString("interests", resumeViewModel.interests)
            // Convert lists to JSON strings
            putString("degreeDataList", Gson().toJson(resumeViewModel.degreeDataList.value))
            putString("workExperienceList", Gson().toJson(resumeViewModel.workExperienceList.value))
            putString("projectList", Gson().toJson(resumeViewModel.projectList.value))
            apply()
        }
    }

    private fun loadFromPreferences() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        resumeViewModel.firstName = sharedPreferences.getString("firstName", "") ?: ""
        resumeViewModel.lastName = sharedPreferences.getString("lastName", "") ?: ""
        resumeViewModel.dateOfBirth = sharedPreferences.getString("dateOfBirth", "") ?: ""
        resumeViewModel.profileImageUri = sharedPreferences.getString("profileImageUri", null)
        resumeViewModel.phoneNumber = sharedPreferences.getString("phoneNumber", "") ?: ""
        resumeViewModel.contactLink = sharedPreferences.getString("contactLink", "") ?: ""
        resumeViewModel.address = sharedPreferences.getString("address", "") ?: ""
        resumeViewModel.email = sharedPreferences.getString("email", "") ?: ""
        resumeViewModel.aboutMe = sharedPreferences.getString("aboutMe", "") ?: ""
        resumeViewModel.interests = sharedPreferences.getString("interests", "") ?: ""

        // Convert JSON strings back to lists
        val gson = Gson()
        val degreeDataType = object : TypeToken<MutableList<DegreeData>>() {}.type
        val workExperienceDataType = object : TypeToken<MutableList<WorkExperienceData>>() {}.type
        val projectDataType = object : TypeToken<MutableList<ProjectData>>() {}.type

        val degreeDataList: MutableList<DegreeData> = gson.fromJson(sharedPreferences.getString("degreeDataList", "[]"), degreeDataType)
        val workExperienceList: MutableList<WorkExperienceData> = gson.fromJson(sharedPreferences.getString("workExperienceList", "[]"), workExperienceDataType)
        val projectList: MutableList<ProjectData> = gson.fromJson(sharedPreferences.getString("projectList", "[]"), projectDataType)

        resumeViewModel.setDegreeDataList(degreeDataList)
        resumeViewModel.setWorkExperienceList(workExperienceList)
        resumeViewModel.setProjectList(projectList)
    }
}
