package com.personal.dailynews.ui.base

import androidx.lifecycle.ViewModel
import com.personal.dailynews.ui.news.FilteredNewsListViewModel
import com.personal.dailynews.ui.news.NewsListViewModel
import com.personal.dailynews.util.injectors.DaggerViewModelInjector
import com.personal.dailynews.util.injectors.ViewModelInjector
import com.personal.dailynews.util.network.APIModule

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .apiModule(APIModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is NewsListViewModel -> injector.inject(this)
            is FilteredNewsListViewModel -> injector.inject(this)
        }
    }
}