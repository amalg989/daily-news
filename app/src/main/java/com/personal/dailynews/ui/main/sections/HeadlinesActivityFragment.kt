package com.personal.dailynews.ui.main.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.personal.dailynews.R
import com.personal.dailynews.databinding.ActivityHeadlinesBinding
import com.personal.dailynews.ui.news.NewsListViewModel

/**
 * Fragment to display headlines
 */
class HeadlinesActivityFragment : Fragment() {
    private lateinit var binding: ActivityHeadlinesBinding
    private lateinit var viewModel: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_headlines, container, false)
        binding.headlinesList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(NewsListViewModel::class.java)
        binding.viewModel = viewModel

        val root = binding.root

        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): HeadlinesActivityFragment {
            return HeadlinesActivityFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}