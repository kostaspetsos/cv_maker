package com.kostaspetsopoulos.cv_maker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TemplatesScreen : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var templateAdapter: TemplateAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.templates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TemplatesScreen", "onViewCreated called with item count: ${getExampleItems().size}")

        recyclerView = view.findViewById(R.id.recyclerViewTemplates)

        // Initialize TemplateAdapter with item click listener
        templateAdapter = TemplateAdapter(getExampleItems()) { position ->
            // Handle item click, for example, navigate to the first fragment
            findNavController().navigate(R.id.action_templatesFragment_to_fragment1)
        }

        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = templateAdapter
    }

    private fun getExampleItems(): List<TemplateItem> {
        val exampleItems = mutableListOf<TemplateItem>()
        exampleItems.add(TemplateItem("Template 1"))
        exampleItems.add(TemplateItem("Template 2"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))
        exampleItems.add(TemplateItem("Template 3"))


        Log.d("TemplateAdapter", "Item count: ${exampleItems.size}")
        return exampleItems
    }

}



data class TemplateItem(val templateName: String)
