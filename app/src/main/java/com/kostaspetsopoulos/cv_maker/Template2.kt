package com.kostaspetsopoulos.cv_maker

import java.util.*

class Template2 : Template {
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
                      margin: 0;
                      padding: 0;
                      box-sizing: border-box;
                      width: 21cm;
                      min-height: 29.7cm;
                    }
                
                    #profile-container {
                      display: flex;
                      align-items: flex-start;
                      padding: 20px;
                    }
                
                    #profile-photo {
                      width: 150px;
                      height: 150px;
                      object-fit: cover;
                      margin-right: 20px;
                    }
                
                    #contact-info {
                        display: flex;
                        flex-direction: column;
                    }
                
                    .contact-item {
                        display: flex;
                        margin-bottom: 10px;
                    }
                    
                    .label {
                        min-width: 150px; /* Adjust as needed */
                        font-weight: bold;
                        margin-right: 10px;
                    }
                    
                    .value {
                        flex-grow: 1;
                    }
                
                    #contact-info h2 {
                      font-size: 32px;
                      margin-bottom: 10px;
                      color: #234070;
                      margin-top: 0;
                    }
                
                    #contact-info p {
                      margin: 5px 0;
                    }
                
                    #about-me-section,
                    #projects-section,
                    #education-section,
                    #experience-section,
                    #interests-section {
                      margin-top: 30px;
                    }
                
                    #about-me-section h3,
                    #projects-section h3,
                    #education-section h3,
                    #experience-section h3,
                    #interests-section h3 {
                      font-size: 18px;
                      color: #234070;
                      margin-bottom: 2px;
                    }
                
                    #about-me-section hr,
                    #projects-section hr,
                    #education-section hr,
                    #experience-section hr,
                    #interests-section hr {
                      border: 1px solid #234070;
                      margin-bottom: 15px;
                      margin-top: 2px;
                    }
                
                    .experience-item,
                    .education-item {
                      display: flex;
                      align-items: flex-start;
                      margin-bottom: 15px;
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
                
                    .company,
                    .university,
                    .interests-title {
                      font-weight: bold;
                    }
                    
                    .project-title {
                      font-weight: bold;
                      margin-bottom: 5px; /* Adjust the margin as needed */
                    }
                
                    .project-description {
                      font-size: 17px;
                      text-align: justify;
                      text-justify: inter-word;
                      word-wrap: break-word;
                      margin-top: 0; /* Remove any top margin */
                      margin-bottom: 0; /* Remove any bottom margin */
                    }
                
                
                    .project-item,
                    .interests-item {
                      margin-bottom: 15px;
                    }
                
                </style>
                </head>
                <body>
                
                  <div id="profile-container">
                    <img id="profile-photo" src="${viewModel.profileImageUri}" alt="Your Name">
                    <div id="contact-info">
                        <h2>${viewModel.firstName} ${viewModel.lastName}</h2>
                        <div class="contact-item">
                            <span class="label">Date of Birth:</span>
                            <span class="value">${viewModel.dateOfBirth}</span>
                        </div>
                        <div class="contact-item">
                            <span class="label">Phone:</span>
                            <span class="value">${viewModel.phoneNumber}</span>
                        </div>
                        <div class="contact-item">
                            <span class="label">Email:</span>
                            <span class="value">${viewModel.email}</span>
                        </div>
                        <div class="contact-item">
                            <span class="label">Address:</span>
                            <span class="value">${viewModel.address}</span>
                        </div>
                        <div class="contact-item">
                            <span class="label">LinkedIn:</span>
                            <span class="value">${viewModel.contactLink}</span>
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
                "Date of Birth:" to "Ημ. Γέννησεως:",
                "Phone:" to "Τηλέφωνο:",
                "Address:" to "Διεύθυνση:"
            )
        )

        // Dynamically change the personal information labels based on the app's language
        personalInfoLabelsMap[language]?.forEach { (englishLabel, translatedLabel) ->
            filledTemplate =
                filledTemplate.replace("<span class=\"label\">$englishLabel</span>", "<span class=\"label\">$translatedLabel</span>")
        }


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
            filledTemplate =
                filledTemplate.replace("<h3>$englishHeader</h3>", "<h3>$translatedHeader</h3>")
        }

        return filledTemplate
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
                    <div class="university">${item.schoolUniTitle}</div>
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
                    <div class="company">${item.companyName}</div>
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
                <p class="project-title">${item.projectTitle}</p>
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

        if (!aboutMe.isNullOrBlank()) {
            aboutMeData.append(
                """
        <div id="about-me-section">
            <h3>ABOUT ME</h3>
            <hr>
            <p style="font-size: 17px; text-align: justify; text-justify: inter-word; word-wrap: break-word;">$aboutMe</p>
        </div>
        """.trimIndent()
            )
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

}