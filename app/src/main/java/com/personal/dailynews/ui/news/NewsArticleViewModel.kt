package com.personal.dailynews.ui.news

import androidx.lifecycle.MutableLiveData
import com.arover.moment.Moment
import com.personal.dailynews.data.model.Article
import com.personal.dailynews.ui.base.BaseViewModel
import com.personal.dailynews.util.constants.Constants

class NewsArticleViewModel: BaseViewModel() {

    private var articleData: Article? = null
    private val image = MutableLiveData<String>()
    private val author = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val link = MutableLiveData<String>()
    private val date = MutableLiveData<String>()
    private val content = MutableLiveData<String>()
    private val sourceName =  MutableLiveData<String>()

    fun bind(article: Article) {
        articleData = article
        image.value = article.urlToImage
        author.value = article.author
        title.value = article.title
        description.value = article.description
        link.value = article.url
        date.value = article.publishedAt
        content.value = article.content
        sourceName.value = "source: ${article.source?.name?:""}"
    }

    fun getImage(): MutableLiveData<String>  {
        return image
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getContent(): MutableLiveData<String> {
        return content
    }

    fun getSource(): MutableLiveData<String> {
        return sourceName
    }

    fun getArticle(): Article? {
        return articleData
    }

    fun getLink(): MutableLiveData<String> {
        return link
    }
}
