package com.personal.dailynews.ui.main.sections

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.personal.dailynews.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val currentPosition = position + 1

        when(currentPosition) {
            1 -> return HeadlinesActivityFragment.newInstance(currentPosition)
            2 -> return FilterActivityFragment.newInstance(currentPosition)
            3 -> return ProfileActivityFragment.newInstance(currentPosition)
        }

        return HeadlinesActivityFragment.newInstance(currentPosition)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}