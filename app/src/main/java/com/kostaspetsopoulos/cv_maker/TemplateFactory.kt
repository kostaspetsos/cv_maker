package com.kostaspetsopoulos.cv_maker

object TemplateFactory {

    //To template Factory vlepei poio template eixe epileksei o xrhsths mesa apo to sharedViewPreferences kai etsi kalei to analogo
    //Template na kanei fillHtml me ta data kai sth synexeia sto webview
    fun createTemplate(templateName: String): Template {
        return when (templateName) {
            "Template 1" -> Template1()
            "Template 2" -> Template2()
            // Add more templates as needed
            else -> Template1() // Default to Template1 if the templateName is not recognized
        }
    }
}

interface Template {
    fun fillTemplate(viewModel: ResumeViewModel): String
}
