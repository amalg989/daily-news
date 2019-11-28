package com.personal.dailynews.ui.main.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.personal.dailynews.R

/**
 * Fragment to display user profile information
 */
class ProfileActivityFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_profile, container, false)

        //initialize root view components
        initView(root)

        return root
    }

    private fun initView(root: View) {
        val title = root.findViewById<TextView>(R.id.title)
        title?.text = "Headline views ${arguments?.getInt(ARG_SECTION_NUMBER)}"
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): ProfileActivityFragment {
            return ProfileActivityFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}