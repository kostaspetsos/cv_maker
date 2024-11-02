package com.kostaspetsopoulos.cv_maker

import java.util.*

class Template10 : Template {
    private val viewModel: ResumeViewModel = ResumeViewModel()

    override fun fillTemplate(viewModel: ResumeViewModel): String {

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
   
                    #profile-info {      /* NAME & SURNAME    */
                        position: relative;
                        color: #f27275;
                        z-index: 2;
                        max-width: 696px;
                    }
                    
                    .contact-details {
                        margin-top: 2px;
                    }
                    
                    .phone, .email, .address, .contactLink {
                        display: inline-block;
                        font-weight: 400;
                        color: #4a4a4a;
                    }
                    
                    .phone::after, 
                    .email::after, 
                    .address::after {
                        content: "|";
                        margin: 0 3px;
                    }
                    
                    .value {
                        margin-left: 3px;
                    }
                    
                    #profile-info h2 {    /* Font size NAME & SURNAME */
                        margin: 0;
                        font-size: 30px;
                        white-space: nowrap;
                        overflow: hidden;
                        text-overflow: ellipsis;
                    }
                    
                    .contact-details .phone, 
                    .contact-details .email, 
                    .contact-details .address, 
                    .contact-details .contactLink {
                        font-size: 12px; /* Font size for phone, email, address, and contact link */
                    }
                    
                    #education-section {
                        margin-top: 40px;
                    }
                    
                    #about-me-section,
                    #projects-section,
                    #experience-section,
                    #interests-section {
                        margin-top: 50px;
                        color: #4a4a4a;
                        page-break-inside: avoid;
                    }
                    
                    #about-me-section h3,
                    #projects-section h3,
                    #education-section h3,
                    #experience-section h3,
                    #interests-section h3 {
                        font-size: 15px;
                        color: #f27275;
                        margin-bottom: 2px;
                        page-break-after: avoid;
                    }
         
                    .experience-item,
                    .education-item {
                        display: flex;
                        font-size: 15px;
                        align-items: flex-start;
                        margin-bottom: 15px;
                        color: #4a4a4a;
                    }
                    
                    .time-period {
                        width: 280px;
                        font-weight: bold;
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
                        overflow-wrap: break-word;
                        word-wrap: break-word;
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
                        font-size: 15px;
                        text-align: justify;
                        text-justify: inter-word;
                        word-wrap: break-word;
                        margin-top: 0;
                        margin-bottom: 0;
                        color: #4a4a4a;
                    }
                    
                    .project-item,
                    .interests-item {
                        margin-bottom: 15px;
                        color: #4a4a4a;
                        font-size: 15px;
                    }

                </style>
            </head>
            <body>
                <div id="top-bar">
                         
                    <div id="profile-info">
                        <h2 id="name-surname" style="font-weight: 600;">${viewModel.firstName} ${viewModel.lastName}</h2>
                        <div class="contact-details">
                            <div class="phone" data-label="Phone">
                                <span class="value">${viewModel.phoneNumber}</span>
                            </div>
                            <div class="email" data-label="Email">
                                <span class="value">${viewModel.email}</span>
                            </div>
                            <div class="address" data-label="Address">
                                <span class="value">${viewModel.address}</span>
                            </div>
                            <div class="contactLink" data-label="LinkedIn">
                                <span class="value">${viewModel.contactLink}</span>
                            </div>
                        </div>
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
                    <div class="job-details">&#8226; ${item.details}</div>
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
        """.trimIndent()
        )

        // Conditionally include Date of Birth if it exists
        if (!viewModel.dateOfBirth.isNullOrBlank()) {
            aboutMeData.append(
                """
            <div class="about-me-item">
                <p style="font-size: 15px;"><span">Date of Birth:</span> ${viewModel.dateOfBirth}</p>
            </div>
            """.trimIndent()
            )
        }

        // Include About Me text if it exists
        if (!aboutMe.isNullOrBlank()) {
            aboutMeData.append(
                """
            <div class="about-me-item">
                <p style="font-size: 15px; text-align: justify; text-justify: inter-word; word-wrap: break-word;">$aboutMe</p>
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
           
            <div class="interests-item">
                <p style="font-size: 15px; text-align: justify; text-justify: inter-word; word-wrap: break-word;">$interests</p>
            </div>
        </div>
        """.trimIndent()
        )
    }

    return interestsData.toString()
}

