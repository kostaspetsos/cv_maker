package com.kostaspetsopoulos.cv_maker

import java.util.*

class Template3 : Template {
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
                    
                    /* Blue Bar on top of the page */
                    #top-bar {  
                        width: 700px;
                        height: 60px; /* Double the original size */
                        display: flex;
                        justify-content: space-between;
                        background-color: #376894;
                        position: relative; /* Required for positioning children */
                        z-index: 0; 
                    }
                
                    .profile-pic-container {
                        margin-left: 20px;
                        margin-top: 30px;
                        position: relative;
                        width: 120px; /* To width tou circle */
                        height: 120px; /* tou circle */
                    }
                
                    .white-circle {
                        width: 100%; 
                        height: 100%; 
                        background-color: white;
                        border-radius: 50%; /* This will create a circle */
                        position: absolute;
                        top: 0;
                        left: 0;
                    }
                
                    #profile-photo {
                        width: 100px; 
                        height: 100px; 
                        border-radius: 50%;
                        position: absolute;
                        margin-left: 10px;
                        margin-top: 10px;
                        z-index: 1;
                    }
            
                    #profile-info {
                        position: absolute;
                        align-items: center;
                        margin-top: 20px;
                        margin-left: 160px; /* Adjust as needed */
                        color: white; /* Change to your desired color */
                        z-index: 2; /* Ensure the text is on top of the photo */
                    }
                
                    #profile-info h2 {
                        margin: 0; /* Remove default margin */
                    }
                    
                    .phone {
                        position: absolute;
                        display: flex;
                        margin-left: 160px;
                        margin-top: 20px;
                        font-size: 10px;
                        font-weight: bold;
                    }
            
                    .phone .label {
                        margin-right: 5px; /* Adjust spacing between label and value as needed */
                        font-size: 10px;
                        font-weight: bold;
                    }
                    
                    .email {
                        position: absolute;
                        display: flex;
                        margin-left: 160px;
                        margin-top: 40px;
                        font-size: 10px;
                        font-weight: bold;
                    }
            
                    .email .label {
                        margin-right: 10px; /* Adjust spacing between label and value as needed */
                        font-size: 10px;
                        font-weight: bold;
                    }
            
                    .address {
                        position: absolute;
                        display: flex;
                        white-space: nowrap; /* When i have space between words it wont move to a different row */
                        margin-left: 400px;
                        margin-top: 20px;
                        font-size: 10px;
                        font-weight: bold;
                    }
            
                    .address .label {
                        margin-right: 5px; /* Adjust spacing between label and value as needed */
                        font-size: 10px;
                        font-weight: bold;
                    }
                    
                    .contactLink {
                        position: absolute;
                        display: flex;
                        margin-left: 400px;
                        margin-top: 40px;
                        font-size: 10px;
                        font-weight: bold;
                    }
            
                    .contactLink .label {
                        margin-right: 10px; /* Adjust spacing between label and value as needed */
                        font-size: 10px;
                        font-weight: bold;
                    }
                    
                    #education-section {
                        margin-top: 120px;
                    }
                    
                    #about-me-section,
                    #projects-section,
                    #experience-section,
                    #interests-section {
                        margin-top: 50px;
                        page-break-inside: avoid;
                    }
            
                    #about-me-section h3,
                    #projects-section h3,
                    #education-section h3,
                    #experience-section h3,
                    #interests-section h3 {
                        font-size: 18px;
                        color: #376894;
                        margin-bottom: 2px;
                        page-break-after: avoid;
                    }
            
                    #about-me-section hr,
                    #projects-section hr,
                    #education-section hr,
                    #experience-section hr,
                    #interests-section hr {
                        border: 1px solid #376894;
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
                        margin-bottom: 5px; 
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
                    
                    /* Prevent breaks inside items like education or projects */
                    .experience-item,
                    .education-item,
                    .project-item,
                    .interests-item {
                        page-break-inside: avoid; /* Ensure each item is on the same page */
                        margin-bottom: 15px;
                    }
                    
                    
    
                </style>
            </head>
            <body>
        
                <div id="top-bar">
                    <div class="profile-pic-container">
                        <div class="white-circle"></div>
                        <img id="profile-photo" src="${viewModel.profileImageUri}" alt="Profile Photo">
                    </div>
                    <div id="profile-info">
                        <h2>${viewModel.firstName} ${viewModel.lastName}</h2>
                    </div>
                    <!-- You can add other elements to the top bar here -->
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
                <p style="font-size: 17px;"><span">Date of Birth:</span> ${viewModel.dateOfBirth}</p>
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

}