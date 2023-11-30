package com.kostaspetsopoulos.cv_maker

import android.content.Context
import android.webkit.WebView
import java.io.*

class HtmlGenerator(
    private val context: Context,
    private val viewModel: ResumeViewModel,
    private val webView: WebView
) {

    fun generateHtml() {
        try {
            // Construct the HTML template with actual data
            val filledTemplate = fillTemplate()

            // Load the HTML into the WebView
            webView.loadDataWithBaseURL(null, filledTemplate, "text/html", "UTF-8", null)

            println("HTML content loaded into WebView")

        } catch (e: IOException) {
            e.printStackTrace()
            println("Error generating HTML content")
        }
    }

    private fun fillTemplate(): String {
        // Replace placeholders in the template with actual data
        var filledTemplate = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your CV</title>
        <style>
            @media print {
                body {
                    font-family: 'Times New Roman', serif;
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                }
            }

            body {
                background-color: #ffffff; /* Set background color to white */
            }

            .header h1 {
                color: #333;
                margin-bottom: 2px;
                font-size: 17px;
            }

            .header {
                text-align: center;
                background-color: #ffffff; /* Set background color to white */
                padding: 20px; /* Add padding to the header */
            }

            .personal-info {
                margin-top: 10px;
                border-top: 1px solid #ddd;
                padding-top: 20px;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                align-items: center;
            }

            .personal-info p {
                display: inline-block;
                margin-right: 10px; /* Adjust the margin to add space between the p elements */
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
                border-bottom: 2px solid #333;
                padding-bottom: 5px;
                margin-bottom: 10px;
                font-size: 17px;
            }

            h3 {
                color: #555;
                margin-bottom: 5px;
                font-size: 12px;
            }

            p {
                margin: 10px 0;
                color: #333;
                font-size: 12px; /* Increase font size for better readability */
            }

            a {
                color: #4285f4;
                text-decoration: none;
                font-size: 12px;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>

        <div class="header">
            <h1>${viewModel.firstName} ${viewModel.lastName}</h1>            
            <!-- Add photo here if available -->
            <!--
            <img src="${viewModel.profileImageUri}" alt="Your Photo">
            -->
        </div>

        <div class="personal-info">
            <p>${viewModel.address}</p>
            <p>|</p>
            <p>${viewModel.phoneNumber}</p>
            <p>|</p>
            <p>${viewModel.contactLink}</p>
            <p>|</p>
            <p>${viewModel.email}</p>
        </div>

        <hr> <!-- Add a horizontal line below the header -->

        <div class="education" style="text-align: center;">
            <h2>Education</h2>
            ${generateEducationData()}
        </div>

        <div class="work-experience" style="text-align: center;">
            <h2>Work Experience</h2>
            ${generateWorkExperienceData()}
        </div>

        <div class="projects" style="text-align: center;">
            <h2>Projects</h2>
            ${generateProjectData()}
        </div>

        <div class="about-me" style="text-align: center;">
            <h2>About Me</h2>
            <p>${viewModel.aboutMe}</p>
            <p>Date of Birth: ${viewModel.dateOfBirth}</p>
        </div>

        <div class="interests" style="text-align: center;">
            <h2>Interests</h2>
            <p>${viewModel.interests}</p>
        </div>

    </body>
    </html>
    """.trimIndent()

        return filledTemplate
    }

    private fun generateEducationData(): String {
        val educationData = StringBuilder()
        val educationList = viewModel.degreeDataList.value
        if (educationList != null) {
            for (item in educationList) {
                educationData.append(
                    """
                    <div class="education-item">
                        <h3>${item.degreeTitle}</h3>
                        <p>Degree Grade: ${item.degreeGrade}</p>
                        <p>${item.degreePeriod}</p>
                    </div>
                """.trimIndent()
                )
            }
        } else {
            educationData.append("<p>No education data available</p>")
        }
        return educationData.toString()
    }

    private fun generateWorkExperienceData(): String {
        val workExperienceData = StringBuilder()
        val workExperienceList = viewModel.workExperienceList.value
        if (workExperienceList != null) {
            for (item in workExperienceList) {
                workExperienceData.append(
                    """
                    <div class="experience-item">
                        <h3>${item.companyName}</h3>
                        <p>${item.jobTitle}</p>
                        <p>Time Period: ${item.timePeriod}</p>
                        <p>Details: ${item.details}</p>
                    </div>
                """.trimIndent()
                )
            }
        } else {
            workExperienceData.append("<p>No work experience data available</p>")
        }
        return workExperienceData.toString()
    }

    private fun generateProjectData(): String {
        val projectData = StringBuilder()
        val projectList = viewModel.projectList.value
        if (projectList != null) {
            for (item in projectList) {
                projectData.append(
                    """
                    <div class="project-item">
                        <h3>${item.projectTitle}</h3>
                        <p>${item.projectDescription}</p>
                    </div>
                """.trimIndent()
                )
            }
        } else {
            projectData.append("<p>No project data available</p>")
        }
        return projectData.toString()
    }
}
