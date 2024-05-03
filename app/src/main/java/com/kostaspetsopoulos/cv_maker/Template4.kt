package com.kostaspetsopoulos.cv_maker

import java.util.*

class Template4 : Template {
    private val viewModel: ResumeViewModel = ResumeViewModel()

    override fun fillTemplate(viewModel: ResumeViewModel): String {
        // Replace placeholders in the template with actual data
        var filledTemplate = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Your CV</title>
                <style>
                    body {
                        font-family: 'helvetica', serif;
                        box-sizing: border-box;
                        width: 696px;
                        min-height: 29.7cm;
                    }

                    /* Blue Bar on top of the page */
                    #top-bar {  
                        width: 700px;
                        height: 120px; /* Double the original size */
                        display: flex;
                        justify-content: space-between;
                        background-color: #e8e8e8;
                        position: relative; /* Required for positioning children */
                        z-index: 0; 
                    }

                    #profile-photo {
                        width: 150px; 
                        height: 150px; 
                        border-radius: 50%;
                        position: absolute;
                        margin-left: 520px;
                        margin-top: 30px;
                        z-index: 2;
                    }

                    #profile-info {
                        position: absolute;
                        align-items: center;
                        margin-top: 30px;
                        margin-left: 20px; /* Adjust as needed */
                        color: #4a4a4a;
                        z-index: 2; /* Ensure the text is on top of the photo */
                        max-width: 450px;
                    }

                    #profile-info h2 {
                        margin: 0; /* Remove default margin */
                        font-size: 30px; /* Initial font size */
                        white-space: nowrap; /* Prevent line breaks */
                        overflow: hidden; /* Hide overflowing text */
                        text-overflow: ellipsis; /* Add ellipsis for overflow */
                    }
                    
                    .phone {
                        position: absolute;
                        display: flex;
                        margin-left: 20px;
                        margin-top: 93px;
                        font-size: 13px;
                        font-weight: 400;
                        color: #4a4a4a;
                    }
                    
                    .phone .label {
                        margin-right: 5px; /* Adjust spacing between label and value as needed */
                        font-size: 13px;
                        font-weight: 600;
                        color: #4a4a4a;
                    }
                    
                    .email {
                        position: absolute;
                        display: flex;
                        margin-left: 200px;
                        margin-top: 93px;
                        font-size: 13px;
                        font-weight: 400;
                        color: #4a4a4a;
                    }
            
                    .email .label {
                        margin-right: 10px; /* Adjust spacing between label and value as needed */
                        font-size: 13px;
                        font-weight: 600;
                        color: #4a4a4a;
                    }
                    
                    .address {
                        position: absolute;
                        display: flex;
                        margin-left: 20px;
                        margin-top: 130px;
                        font-size: 13px;
                        font-weight: 400;
                        color: #4a4a4a;
                    }
                    
                    .address .label {
                        margin-right: 5px; /* Adjust spacing between label and value as needed */
                        font-size: 13px;
                        font-weight: 600;
                        color: #4a4a4a;
                    }
                    
                    .contactLink {
                        position: absolute;
                        display: flex;
                        margin-left: 200px;
                        margin-top: 130px;
                        font-size: 13px;
                        font-weight: 400;
                        color: #4a4a4a;
                    }
            
                    .contactLink .label {
                        margin-right: 10px; /* Adjust spacing between label and value as needed */
                        font-size: 13px;
                        font-weight: 600;
                        color: #4a4a4a;
                    }
                    
                    #education-section {
                        margin-top: 100px;
                    }
        
                    #about-me-section,
                    #projects-section,
                    #experience-section,
                    #interests-section {
                        margin-top: 50px;
                        color: #4a4a4a;
                        
                    }

                    #about-me-section h3,
                    #projects-section h3,
                    #education-section h3,
                    #experience-section h3,
                    #interests-section h3 {
                        font-size: 18px;
                        color: #4a4a4a;
                        margin-bottom: 2px;
                        page-break-after: avoid;
                    }

                    #about-me-section hr,
                    #projects-section hr,
                    #education-section hr,
                    #experience-section hr,
                    #interests-section hr {
                        border: 1px solid #4a4a4a;
                        margin-bottom: 15px;
                        margin-top: 2px;
                    }
            
                    .experience-item,
                    .education-item {
                        display: flex;
                        align-items: flex-start;
                        margin-bottom: 15px;
                        margin-bottom: 15px;
                        color: #4a4a4a;
                    }
            
                    .time-period {
                        width: 280px;
                    }
            
                    .education-details {
                        margin-right: 0px;
                        width: 600px;
                    }
                
                    .work-details {
                        margin-right: 0px;
                        width: 600px;
                    }
                    
                    .job-details {
                        overflow-wrap: break-word; /* For newer browsers */
                        word-wrap: break-word; /* For older browsers */
                        margin-top: 5px;
                    }
            
                    .company,
                    .university,
                    .interests-title {
                        font-weight: bold;
                        color: #4a4a4a;
                    }
                
                    .project-title {
                        font-weight: bold;
                        margin-bottom: 5px;
                        color: #4a4a4a;
                    }
            
                    .project-description {
                        font-size: 17px;
                        text-align: justify;
                        text-justify: inter-word;
                        word-wrap: break-word;
                        margin-top: 0; /* Remove any top margin */
                        margin-bottom: 0; /* Remove any bottom margin */
                        color: #4a4a4a;
                    }
            
            
                    .project-item,
                    .interests-item {
                        margin-bottom: 15px;
                        color: #4a4a4a;
                    }
                    
                    

                </style>
            </head>
            <body>
                <div id="top-bar">
                        <img id="profile-photo" src="${viewModel.profileImageUri}" alt="Profile Photo">    
                    <div id="profile-info">
                        <h2 id="name-surname" style="font-weight: 600;">${viewModel.firstName} ${viewModel.lastName}</h2>
                    </div>
                    
                    <div class="phone">
                        <span class="label">Phone:</span>
                        <span class="value">${viewModel.phoneNumber}</span>
                    </div>
                    <div class="email">
                        <span class="label">Email:</span>
                        <span class="value">${viewModel.email}</span>
                    </div>
                    <div class="address">
                        <span class="label">Address:</span>
                        <span class="value">${viewModel.address}</span>
                    </div>
                    <div class="contactLink">
                        <span class="label">LinkedIn:</span>
                        <span class="value">${viewModel.contactLink}</span>
                    </div>
                </div>
                
                ${generateEducationData(viewModel)}
    
                ${generateWorkExperienceData(viewModel)}
        
                ${generateProjectData(viewModel)}  
                
                ${generateAboutMeData(viewModel)} 
        
                ${generateInterestsData(viewModel)}
            </body>
            </html>
        """.trimIndent()

        // Dynamically change the labels and headers based on the app's language
        val language = Locale.getDefault().language

        val personalInfoLabelsMap = mapOf(
            "en" to mapOf(
                "Date of Birth:" to "Date of Birth:",
                "Phone:" to "Phone:",
                "Address:" to "Address:"
            ),
            "el" to mapOf(
                "Date of Birth:" to "Ημ. Γεννήσεως:",
                "Phone:" to "Τηλέφωνο:",
                "Address:" to "Διεύθυνση:"
            )
        )

        // Dynamically change the personal information labels based on the app's language
        personalInfoLabelsMap[language]?.forEach { (englishLabel, translatedLabel) ->
            filledTemplate = filledTemplate.replace(englishLabel, translatedLabel)
        }

        // Dynamically change the headers based on the app's language
        val headersMap = mapOf(
            "en" to mapOf(
                "EDUCATION" to "EDUCATION",
                "WORK EXPERIENCE" to "WORK EXPERIENCE",
                "INTERESTS" to "INTERESTS",
                "ABOUT ME" to "ABOUT ME"
            ),
            "el" to mapOf(
                "EDUCATION" to "ΕΚΠΑΙΔΕΥΣΗ",
                "WORK EXPERIENCE" to "ΕΡΓΑΣΙΑΚΗ ΕΜΠΕΙΡΙΑ",
                "INTERESTS" to "ΕΝΔΙΑΦΕΡΟΝΤΑ",
                "ABOUT ME" to "ΤΟ ΠΡΟΦΙΛ ΜΟΥ"
            )
        )

        headersMap[language]?.forEach { (englishHeader, translatedHeader) ->
            filledTemplate = filledTemplate.replace(englishHeader, translatedHeader)
        }

        return filledTemplate
    }
}


    private fun generateEducationData(viewModel: ResumeViewModel): String {
        val educationData = StringBuilder()
        val educationList = viewModel.degreeDataList.value

        if (educationList != null && educationList.isNotEmpty()) {
            // Add the header outside the loop, so it's printed only once
            educationData.append(
                """
        <div id="education-section">
            <h3>EDUCATION</h3>
            <hr>
        """.trimIndent()
            )

            for (item in educationList) {
                educationData.append(
                    """
            <div class="education-item">
                <div class="time-period">${item.degreePeriod}</div>
                <div class="education-details">
                    <div class="university" style="font-weight: bold;">${item.schoolUniTitle}</div>
                    <div class="school">${item.degreeTitle}</div>
                    <div class="grade">${item.degreeGrade}</div>
                </div>
            </div>
            """.trimIndent()
                )
            }

            educationData.append("</div>")
        } else {
            // No education data available
        }

        return educationData.toString()
    }


private fun generateWorkExperienceData(viewModel: ResumeViewModel): String {
    val workExperienceData = StringBuilder()
    val workExperienceList = viewModel.workExperienceList.value

    if (workExperienceList != null && workExperienceList.isNotEmpty()) {
        // Add the header outside the loop, so it's printed only once
        workExperienceData.append(
            """
        <div id="experience-section">
            <h3>WORK EXPERIENCE</h3>
            <hr>
        """.trimIndent()
        )

        for (item in workExperienceList) {
            workExperienceData.append(
                """
            <div class="experience-item">
                <div class="time-period">${item.timePeriod}</div>
                <div class="work-details">
                    <div class="company" style="font-weight: bold;">${item.companyName}</div>
                    <div class="job-title">${item.jobTitle}</div>
                    <div class="job-details">${item.details}</div>
                </div>
            </div>
            """.trimIndent()
            )
        }

        workExperienceData.append("</div>")
    } else {
        // No work experience data available
    }

    return workExperienceData.toString()
}



private fun generateProjectData(viewModel: ResumeViewModel): String {
        val projectData = StringBuilder()
        val projectList = viewModel.projectList.value

        if (projectList != null && projectList.isNotEmpty()) {
            // Add the header outside the loop, so it's printed only once
            projectData.append(
                """
        <div id="projects-section">
            <h3>PROJECTS</h3>
            <hr>
        """.trimIndent()
            )

            for (item in projectList) {
                projectData.append(
                    """
            <div class="project-item">
                <p class="project-title" style="font-weight: bold;">${item.projectTitle}</p>
                <p class="project-description">${item.projectDescription}</p>
            </div>
            """.trimIndent()
                )
            }

            projectData.append("</div>")
        } else {
            // No project data available, do not include the "PROJECTS" title
        }

        return projectData.toString()
    }

    private fun generateAboutMeData(viewModel: ResumeViewModel): String {
        val aboutMeData = StringBuilder()
        val aboutMe = viewModel.aboutMe

        // Check if either Date of Birth or About Me is not empty
        if (!viewModel.dateOfBirth.isNullOrBlank() || !aboutMe.isNullOrBlank()) {
            // Start About Me section
            aboutMeData.append(
                """
        <div id="about-me-section">
            <h3>ABOUT ME</h3>
            <hr>
        """.trimIndent()
            )

            // Conditionally include Date of Birth if it exists
            if (!viewModel.dateOfBirth.isNullOrBlank()) {
                aboutMeData.append(
                    """
            <div class="about-me-item">
                <p style="font-size: 13px;"><span">Date of Birth:</span> ${viewModel.dateOfBirth}</p>
            </div>
            """.trimIndent()
                )
            }

            // Include About Me text if it exists
            if (!aboutMe.isNullOrBlank()) {
                aboutMeData.append(
                    """
            <div class="about-me-item">
                <p style="font-size: 17px; text-align: justify; text-justify: inter-word; word-wrap: break-word;">$aboutMe</p>
            </div>
            """.trimIndent()
                )
            }

            // Close About Me section
            aboutMeData.append("</div>")
        }

        return aboutMeData.toString()
    }

    private fun generateInterestsData(viewModel: ResumeViewModel): String {
        val interestsData = StringBuilder()
        val interests = viewModel.interests

        if (!interests.isNullOrBlank()) {
            interestsData.append(
                """
        <div id="interests-section">
            <h3>INTERESTS</h3>
            <hr>
            <div class="interests-item">
                <p style="font-size: 17px; text-align: justify; text-justify: inter-word; word-wrap: break-word;">$interests</p>
            </div>
        </div>
        """.trimIndent()
            )
        }

        return interestsData.toString()
    }

