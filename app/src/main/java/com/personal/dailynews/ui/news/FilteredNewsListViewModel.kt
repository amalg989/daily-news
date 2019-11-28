package com.personal.dailynews.ui.news

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.personal.dailynews.data.model.Response
import com.personal.dailynews.ui.base.BaseViewModel
import com.personal.dailynews.util.constants.NewsCategories
import com.personal.dailynews.util.network.apis.NewsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class FilteredNewsListViewModel: BaseViewModel() {
    val TAG:String = "FilteredNewsList"

    @Inject
    lateinit var newsApi: NewsAPI

    private lateinit var subscription: Disposable

    val newsListAdapter: NewsListAdapter = NewsListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadFilteredHedlines(NewsCategories.BITCOIN)
    }

    fun loadFilteredHedlines(query: NewsCategories){
        subscription = newsApi.getFilteredArticles(query.category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveHeadlinesListStart() }
            .doOnTerminate { onRetrieveHeadlinesListFinish() }
            .subscribe(
                { result -> onRetrieveHeadlinesListSuccess(result) },
                { error -> onRetrieveHeadlinesListError(error) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrieveHeadlinesListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveHeadlinesListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveHeadlinesListSuccess(response: Response){
        Log.d(TAG, "onRetrieveHeadlinesListSuccess, $response")
        newsListAdapter.updateNewsList(response.articles)
    }

    private fun onRetrieveHeadlinesListError(error: Throwable?){
        Log.d(TAG, "onRetrieveHeadlinesListError, $error")
        error?.printStackTrace()
    }
}