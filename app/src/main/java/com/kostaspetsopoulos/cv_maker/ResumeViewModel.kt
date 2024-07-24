package com.kostaspetsopoulos.cv_maker

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResumeViewModel : ViewModel() {
    var firstName: String = ""
    var lastName: String = ""
    var dateOfBirth: String = ""
    var profileImageUri: String? = null
    var phoneNumber: String = ""
    var contactLink: String = ""
    var address: String = ""
    var email: String = ""
    var aboutMe: String = ""
    var interests: String = ""

    var phoneLabel: Uri? = null
    var emailLabel: Uri? = null
    var addressLabel: Uri? = null
    var contactLinkLabel: Uri? = null

    // Education Screen
    private val _degreeDataList = MutableLiveData<MutableList<DegreeData>>(mutableListOf())
    val degreeDataList: LiveData<MutableList<DegreeData>> get() = _degreeDataList

    // Work Experience Screen
    private val _workExperienceList = MutableLiveData<MutableList<WorkExperienceData>>(mutableListOf())
    val workExperienceList: LiveData<MutableList<WorkExperienceData>> get() = _workExperienceList

    // Project Screen
    private val _projectList = MutableLiveData<MutableList<ProjectData>>(mutableListOf())
    val projectList: LiveData<MutableList<ProjectData>> get() = _projectList

    init {
        // Set URIs for the images
        phoneLabel = Uri.parse("file:///android_asset/phone-jpg.jpg")
        emailLabel = Uri.parse("file:///android_asset/email-white.png")
        addressLabel = Uri.parse("file:///android_asset/location_icon.png")
        contactLinkLabel = Uri.parse("file:///android_asset/link-black.png")
    }

    fun addDegreeData(degreeData: DegreeData) {
        _degreeDataList.value?.add(degreeData)
        _degreeDataList.value = _degreeDataList.value
    }

    fun removeDegreeData(degreeData: DegreeData) {
        _degreeDataList.value?.remove(degreeData)
        _degreeDataList.value = _degreeDataList.value
    }

    fun addWorkExperience(workExperienceData: WorkExperienceData) {
        _workExperienceList.value?.add(workExperienceData)
        _workExperienceList.value = _workExperienceList.value
    }

    fun removeWorkExperience(workExperienceData: WorkExperienceData) {
        _workExperienceList.value?.remove(workExperienceData)
        _workExperienceList.value = _workExperienceList.value
    }

    fun addProject(projectData: ProjectData) {
        _projectList.value?.add(projectData)
        _projectList.value = _projectList.value
    }

    fun removeProject(projectData: ProjectData) {
        _projectList.value?.remove(projectData)
        _projectList.value = _projectList.value
    }

    fun setDegreeDataList(list: MutableList<DegreeData>) {
        _degreeDataList.value = list
    }

    fun setWorkExperienceList(list: MutableList<WorkExperienceData>) {
        _workExperienceList.value = list
    }

    fun setProjectList(list: MutableList<ProjectData>) {
        _projectList.value = list
    }

    fun getProfileImageUri(): Uri? {
        return profileImageUri?.let { Uri.parse(it) }
    }

    fun setProfileImageUri(uri: Uri?) {
        profileImageUri = uri?.toString()
    }
}
