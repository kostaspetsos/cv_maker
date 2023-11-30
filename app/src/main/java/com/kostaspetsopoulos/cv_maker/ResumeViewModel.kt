package com.kostaspetsopoulos.cv_maker

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Define a ViewModel to store user data
class ResumeViewModel : ViewModel() {
    var firstName: String = ""
    var lastName: String = ""
    var dateOfBirth: String = ""
    var profileImageUri: Uri? = null
    var phoneNumber: String = ""
    var contactLink: String = ""
    var address: String = ""
    var email: String = ""
    var aboutMe: String = ""
    var interests: String = ""


    //Education Screen
    private val _degreeDataList = MutableLiveData<MutableList<DegreeData>>()
    val degreeDataList: LiveData<MutableList<DegreeData>> get() = _degreeDataList

    init {
        _degreeDataList.value = mutableListOf()
    }

    fun addDegreeData(degreeData: DegreeData) {
        _degreeDataList.value?.add(degreeData)
        _degreeDataList.value = _degreeDataList.value
    }

    fun removeDegreeData(degreeData: DegreeData) {
        _degreeDataList.value?.remove(degreeData)
        _degreeDataList.value = _degreeDataList.value
    }


    //Work Experience Screen
    private val _workExperienceList = MutableLiveData<MutableList<WorkExperienceData>>()
    val workExperienceList: LiveData<MutableList<WorkExperienceData>> get() = _workExperienceList

    init {
        _workExperienceList.value = mutableListOf()
    }

    fun addWorkExperience(workExperienceData: WorkExperienceData) {
        _workExperienceList.value?.add(workExperienceData)
        _workExperienceList.value = _workExperienceList.value
    }

    fun removeWorkExperience(workExperienceData: WorkExperienceData) {
        _workExperienceList.value?.remove(workExperienceData)
        _workExperienceList.value = _workExperienceList.value
    }

    // Project Screen
    private val _projectList = MutableLiveData<MutableList<ProjectData>>()
    val projectList: LiveData<MutableList<ProjectData>> get() = _projectList

    init {
        _projectList.value = mutableListOf()
    }

    fun addProject(projectData: ProjectData) {
        _projectList.value?.add(projectData)
        _projectList.value = _projectList.value
    }

    fun removeProject(projectData: ProjectData) {
        _projectList.value?.remove(projectData)
        _projectList.value = _projectList.value
    }

}