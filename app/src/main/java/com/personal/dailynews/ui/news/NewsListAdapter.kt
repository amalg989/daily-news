package com.personal.dailynews.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.personal.dailynews.R
import com.personal.dailynews.data.model.Article
import com.personal.dailynews.databinding.ItemHeadlinesBinding

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private lateinit var newsList:List<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        val binding: ItemHeadlinesBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_headlines, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return if(::newsList.isInitialized) newsList.size else 0
    }

    fun updateNewsList(newsList:List<Article>){
        this.newsList = newsList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemHeadlinesBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = NewsArticleViewModel()
        private val presenter = NewsListItemPresenter(binding.root)

        fun bind(article:Article){
            viewModel.bind(article)
            binding.viewModel = viewModel
            binding.presenter = presenter
        }
    }
}