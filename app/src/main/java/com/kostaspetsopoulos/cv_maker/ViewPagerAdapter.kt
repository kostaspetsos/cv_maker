package com.kostaspetsopoulos.cv_maker

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 8

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NameSurnameScreen()
            1 -> FacePhotoScreen()
            2 -> PersonalInfoScreen()
            3 -> EducationScreen()
            4 -> WorkExperienceScreen()
            5 -> ProjectsScreen()
            6 -> AboutMeScreen()
            7 -> InterestsScreen()
            else -> throw IndexOutOfBoundsException()
        }
    }
}
