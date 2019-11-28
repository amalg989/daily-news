package com.personal.dailynews.ui.news

import com.personal.dailynews.data.model.Article

interface NewsListPresenterContract {
    fun showNewsDetails(article: Article)
}