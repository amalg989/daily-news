package com.personal.dailynews.ui.main.sections

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.ChipGroup
import com.personal.dailynews.R
import com.personal.dailynews.databinding.ActivityFilterBinding
import com.personal.dailynews.databinding.ActivityHeadlinesBinding
import com.personal.dailynews.ui.news.FilteredNewsListViewModel
import com.personal.dailynews.ui.news.NewsListViewModel
import com.personal.dailynews.util.constants.NewsCategories

/**
 * Fragment to display a list of news articles with a filter option
 */
class FilterActivityFragment : Fragment() {
    private lateinit var binding: ActivityFilterBinding
    private lateinit var viewModel: FilteredNewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_filter, container, false)
        binding.headlinesList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(FilteredNewsListViewModel::class.java)
        binding.viewModel = viewModel

        val root = binding.root

        val chipGroup = root.findViewById<ChipGroup>(R.id.filters)

        chipGroup.setOnCheckedChangeListener {
                chipGroup, i ->
                    when(i) {
                        R.id.filter_bitcoin -> viewModel.loadFilteredHedlines(NewsCategories.BITCOIN)
                        R.id.filter_animal -> viewModel.loadFilteredHedlines(NewsCategories.ANIMAL)
                        R.id.filter_apple -> viewModel.loadFilteredHedlines(NewsCategories.APPLE)
                        R.id.filter_earthquake -> viewModel.loadFilteredHedlines(NewsCategories.EARTHQUAKE)
                    }
        }

        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): FilterActivityFragment {
            return FilterActivityFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}