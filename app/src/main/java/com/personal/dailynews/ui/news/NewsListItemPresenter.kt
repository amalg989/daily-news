package com.personal.dailynews.ui.news

import android.util.Log
import android.view.View
import com.personal.dailynews.data.model.Article
import com.personal.dailynews.ui.news.details.NewsDetailsActivity

class NewsListItemPresenter(rootView: View) : NewsListPresenterContract {

    private var root: View? = null

    init {
        root = rootView
    }

    override fun showNewsDetails(article: Article) {
        Log.d("NewsListItemPresenter", "$article")
        try {
            root?.context?.startActivity(NewsDetailsActivity.getStartIntent(root?.context!!, article))
        } catch (e: Exception) {
            Log.d("NewsListItemPresenter", "showNewsDetails: error")
            e.printStackTrace()
        }
    }

}