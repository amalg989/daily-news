package com.personal.dailynews.util.injectors

import com.personal.dailynews.ui.news.FilteredNewsListViewModel
import com.personal.dailynews.ui.news.NewsListViewModel
import com.personal.dailynews.util.network.APIModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(APIModule::class)])
interface ViewModelInjector {
    fun inject(newsListViewModel: NewsListViewModel)
    fun inject(filteredNewsListViewModel: FilteredNewsListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun apiModule(apiModule: APIModule): Builder
    }

}