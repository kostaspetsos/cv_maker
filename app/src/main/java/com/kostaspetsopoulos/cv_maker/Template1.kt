package com.kostaspetsopoulos.cv_maker

import android.util.Log
import java.util.*

class Template1 : Template {
    private val viewModel: ResumeViewModel = ResumeViewModel()

    override fun fillTemplate(viewModel: ResumeViewModel): String {
        // Replace placeholders in the template with actual data
        val educationData = generateEducationData(viewModel)
        val workExperienceData = generateWorkExperienceData(viewModel)
        val projectData = generateProjectData(viewModel)
        val interestsData = generateInterestsData(viewModel)
        val aboutMeData = generateAboutMeData(viewModel)
        var filledTemplate = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your CV</title>
        <style>
            .print-body {
                background-color: #ffffff;
            }

            body {
                background-color: #ffffff;
                font-family: 'helvetica', serif;
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                width: 21cm;
                min-height: 29.7cm
            }

            .header h1 {
                color: #333;
                margin: 0;
                margin-top: 70px;
                margin-bottom: 10px;
                font-size: 24px;
            }

            .header {
                text-align: center;
                background-color: #ffffff;
                padding: 3px;
                margin-bottom: 0;
            }

            .personal-info {
                margin: 0;
                padding-top: 0;
                padding-bottom: 0;
                margin-bottom: 4px;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                align-items: center;
                font-size: 17px;
            }

            .personal-info p {
                display: inline-block;
                margin-right: 3px;
            }
            
            hr {
                margin: 0;
            }

            .header img {
                max-width: 100%;
                max-height: 150px;
                border-radius: 50%;
                margin-top: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            

            .header h2,
            .education h2,
            .work-experience h2,
            .projects h2,
            .about-me h2,
            .interests h2 {
                color: #333;
                padding-bottom: 5px;
                margin-top: 20px;
                margin-bottom: 0px;
                font-size: 17px;
                text-align: center;
            }

            h3 {
                text-align: left;
                color: #555;
                margin-bottom: 5px;
                font-size: 10px;
            }

            p {
                margin: 0;
                color: #333;
            }

            a {
                color: #4285f4;
                text-decoration: none;
                font-size: 10px;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body class="print-body">

        <div class="header">
            <h1>${viewModel.firstName} ${viewModel.lastName}</h1>            
            <!-- Add photo here if available -->
            <!--
            <img src="${viewModel.profileImageUri}" alt="Your Photo">
            -->
        </div>

        <div class="personal-info" style="text-align: center;">
            <p>${viewModel.address}</p>
            <p>|</p>
            <p>${viewModel.phoneNumber}</p>
            <p>|</p>
            <p>${viewModel.contactLink}</p>
            <p>|</p>
            <p>${viewModel.email}</p>
        </div>

        <hr> <!-- Add a horizontal line below the header -->

        
            ${generateEducationData(viewModel)}
            
            ${generateWorkExperienceData(viewModel)}
    
            ${generateProjectData(viewModel)}  
    
            ${generateInterestsData(viewModel)}

            ${generateAboutMeData(viewModel)}
    
        </body>
        </html>
    """.trimIndent()


        // Dynamically change the headers based on the app's language
        val language = Locale.getDefault().language
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
            filledTemplate = filledTemplate.replace("<h2>$englishHeader</h2>", "<h2>$translatedHeader</h2>")
        }

        return filledTemplate
    }

    private fun generateEducationData(viewModel: ResumeViewModel): String {
        val educationData = StringBuilder()
        val educationList = viewModel.degreeDataList.value
        Log.e("HtmlGenerator", "Got into generateEducationData")

        if (educationList != null && educationList.isNotEmpty()) {
            // Add the header outside the loop, so it's printed only once
            educationData.append(
                """
            <div class="education" style="text-align: center; margin-bottom: 10px;">
                <h2>EDUCATION</h2>
            </div>
            """.trimIndent()
            )

            for (item in educationList) {
                educationData.append(
                    """
                <div class="education-item" style="padding: 5px; padding-top: 0; padding-left: 50px; padding-right: 50px; margin-bottom: 10px;">
                    <div style="margin-bottom: 2px;">
                        <p style="font-weight: bold; font-size: 17px; display: inline-block; float: left;">${item.schoolUniTitle}</p>
                        <p style="font-size: 17px; display: inline-block; float: right; margin-bottom: 0;">${item.degreePeriod}</p>
                        <div style="clear: both;"></div> <!-- Add this line to clear the float -->
                    </div>
                    <div style="margin-bottom: 0; clear: both;"> <!-- Adjusted margin-bottom to 0 -->
                        <p style="font-size: 17px; display: inline-block;">${item.degreeTitle}</p>
                    </div>
                    <div style="margin-bottom: 2px;">
                        <p style="font-size: 17px; display: inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;&nbsp;&nbsp;${item.degreeGrade}</p>
                    </div>
                </div>
                """.trimIndent()
                )
            }
        } else {
            Log.e("HtmlGenerator", "No education data available")
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
            <div class="work-experience" style="text-align: center; margin-bottom: 10px;">
                <h2>WORK EXPERIENCE</h2>
            </div>
            """.trimIndent()
            )

            for (item in workExperienceList) {
                workExperienceData.append(
                    """
                <div class="experience-item" style="padding: 5px; padding-top: 0; padding-left: 50px; padding-right: 50px; margin-bottom: 10px;">
                    <div style="margin-bottom: 2px;">
                        <p style="font-weight: bold; font-size: 17px; display: inline-block; float: left;">${item.companyName}</p>
                        <p style="font-size: 17px; display: inline-block; float: right; margin-bottom: 0;">${item.timePeriod}</p>
                        <div style="clear: both;"></div> <!-- Add this line to clear the float -->
                    </div>
                    <div style="margin-bottom: 0; clear: both;"> <!-- Adjusted margin-bottom to 0 -->
                        <p style="font-size: 17px; display: inline-block;">${item.jobTitle}</p>
                    </div>
                    <div style="margin-bottom: 2px;">
                        <p style="font-size: 17px; display: inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;&nbsp;&nbsp;${item.details}</p>
                    </div>
                </div>
                """.trimIndent()
                )
            }
        } else {
            //No work experience data available
        }

        return workExperienceData.toString()
    }

    private fun generateProjectData(viewModel: ResumeViewModel): String {
        val projectData = StringBuilder()
        val projectList = viewModel.projectList.value

        if (projectList != null && projectList.isNotEmpty()) {
            // Add the header outside the loop, so it's printed only once
            var isHeaderAdded = false
            for (item in projectList) {
                if (!isHeaderAdded) {
                    projectData.append(
                        """
                    <div class="projects" style="text-align: center; margin-bottom: 10px;">
                        <h2>PROJECTS</h2>
                    </div>
                    """.trimIndent()
                    )
                    isHeaderAdded = true
                }

                projectData.append(
                    """
                <div class="project-item" style="padding: 5px; padding-top: 0; padding-left: 50px; padding-right: 50px; margin-bottom: 10px;">
                    <div style="display: flex; align-items: flex-start;">
                        <p style="font-weight: bold; font-size: 17px; margin-bottom: 2px;">${item.projectTitle}</p>
                    </div>
                    <div style="display: flex; justify-content: space-between;">
                        <p style="font-size: 17px; margin-bottom: 2px;">&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;&nbsp;&nbsp;${item.projectDescription}</p>
                    </div>
                </div>
                """.trimIndent()
                )
            }
        } else {
            // No project data available, do not include the "PROJECTS" title
        }

        return projectData.toString()
    }

    private fun generateInterestsData(viewModel: ResumeViewModel): String {
        val interestsData = StringBuilder()
        val interests = viewModel.interests

        if (!interests.isNullOrBlank()) {
            interestsData.append(
                """
            <div class="interests" style="padding: 5px; padding-top: 0; padding-left: 50px; padding-right: 50px; margin-bottom: 10px;">
                <h2>INTERESTS</h2>
                <p style="margin: 0; text-align: justify; text-justify: inter-word; word-wrap: break-word; font-size: 17px;">$interests</p>
            </div>
        """.trimIndent()
            )
        }

        return interestsData.toString()
    }

    private fun generateAboutMeData(viewModel: ResumeViewModel): String {
        val aboutMeData = StringBuilder()
        val aboutMe = viewModel.aboutMe

        if (!aboutMe.isNullOrBlank()) {
            aboutMeData.append(
                """
        <div class="about-me" style="padding: 5px; padding-top: 0; padding-left: 50px; padding-right: 50px; margin-bottom: 10px;">
            <h2>ABOUT ME</h2>
        """.trimIndent()
            )

            // Conditionally include Date of Birth if it exists
            if (!viewModel.dateOfBirth.isNullOrBlank()) {
                aboutMeData.append(
                    """
                <p style="font-size: 17px">Date of Birth: ${viewModel.dateOfBirth}</p>
                """.trimIndent()
                )
            }

            aboutMeData.append(
                """
            <p style="margin: 0; text-align: justify; text-justify: inter-word; word-wrap: break-word; font-size: 17px;">$aboutMe</p>
        </div>
    """.trimIndent()
            )
        }

        return aboutMeData.toString()
    }
}