package com.kostaspetsopoulos.cv_maker

object TemplateFactory {

    //To template Factory vlepei poio template eixe epileksei o xrhsths mesa apo to sharedViewPreferences kai etsi kalei to analogo
    //Template na kanei fillHtml me ta data kai sth synexeia sto webview
    fun createTemplate(templateName: String): Template {
        return when (templateName) {
            "Template 1" -> Template1()
            "Template 2" -> Template2()
            "Template 3" -> Template3()
            "Template 4" -> Template4()
            "Template 5" -> Template5()
            "Template 6" -> Template6()
            "Template 6 Responsive" -> Template6Responsive()
            "Template 7" -> Template7()
            "Template 7 Responsive" -> Template7Responsive()
            "Template 8" -> Template8()
            "Template 9" -> Template9()
            "Template 10" -> Template10()
            else -> Template1() // Default to Template1 if the templateName is not recognized
        }
    }
}

interface Template {
    fun fillTemplate(viewModel: ResumeViewModel): String
}

