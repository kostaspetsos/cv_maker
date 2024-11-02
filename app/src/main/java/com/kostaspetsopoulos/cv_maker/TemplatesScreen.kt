package com.kostaspetsopoulos.cv_maker

import TemplateAdapter
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class TemplatesScreen : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var templateAdapter: TemplateAdapter

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.to_bottom_anim
        )
    }
    private var clicked = false

    // Floating Action Button For language select
    private lateinit var languageBtn: FloatingActionButton
    private lateinit var grBtn: FloatingActionButton
    private lateinit var engBtn: FloatingActionButton

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

        languageBtn = view.findViewById(R.id.languageBtn)
        grBtn = view.findViewById(R.id.grBtn)
        engBtn = view.findViewById(R.id.engBtn)

        recyclerView = view.findViewById(R.id.recyclerViewTemplates)

        templateAdapter = TemplateAdapter(getExampleItems()) { templateItem ->
            // Extract the template name from the TemplateItem
            val templateName = templateItem.templateName

            // Handle item click, for example, save the selected template name
            saveSelectedTemplateName(templateName)

            // Handle item click, for example, navigate to the first fragment
            findNavController().navigate(R.id.action_templatesFragment_to_fragment1)
        }

        val layoutManager = GridLayoutManager(requireActivity(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // Adjust span size dynamically here
                return 1
            }
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = templateAdapter

        // APP'S LANGUAGE SELECTION
        languageBtn.setOnClickListener {
            Log.d("Language Selector", " Language Selector has been Pressed")
            onBtnClicked()
        }
        grBtn.setOnClickListener {
            Log.d("Language Selector", " Greek Selected")

            // Change language to Greek
            changeAppLanguage(requireContext(), "el")

            // Restart fragment for the changes to take effect
            requireActivity().recreate()
        }
        engBtn.setOnClickListener {
            Log.d("Language Selector", " English Selected")

            // Change language to the default language (English)
            changeAppLanguage(requireContext(), "en")

            // Restart activity for the changes to take effect
            requireActivity().recreate()
        }
    }

    private fun changeAppLanguage(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }

    private fun onBtnClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            grBtn.visibility = View.VISIBLE
            engBtn.visibility = View.VISIBLE
        } else {
            grBtn.visibility = View.INVISIBLE
            engBtn.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            engBtn.startAnimation(fromBottom)
            grBtn.startAnimation(fromBottom)
            languageBtn.startAnimation(rotateOpen)
        } else {
            engBtn.startAnimation(toBottom)
            grBtn.startAnimation(toBottom)
            languageBtn.startAnimation(rotateClose)
        }
    }

    private fun getExampleItems(): List<TemplateItem> {
        val exampleItems = mutableListOf<TemplateItem>()
        exampleItems.add(TemplateItem("Template 1", R.drawable.temp1_preview))
        exampleItems.add(TemplateItem("Template 2", R.drawable.temp2_preview))
        exampleItems.add(TemplateItem("Template 3", R.drawable.temp3_preview))
        exampleItems.add(TemplateItem("Template 4", R.drawable.temp4_preview))
        exampleItems.add(TemplateItem("Template 5", R.drawable.temp5_preview))
        exampleItems.add(TemplateItem("Template 6", R.drawable.temp6_preview))
        exampleItems.add(TemplateItem("Template 7", R.drawable.temp7_preview))
        exampleItems.add(TemplateItem("Template 8", R.drawable.temp8_preview))
        exampleItems.add(TemplateItem("Template 9", R.drawable.temp9_preview))
        exampleItems.add(TemplateItem("Template 10", R.drawable.temp10_preview))
        return exampleItems
    }

    private fun saveSelectedTemplateName(templateName: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("selected_template", templateName)
        editor.apply()
    }

}

data class TemplateItem(val templateName: String, val imageResourceId: Int)
