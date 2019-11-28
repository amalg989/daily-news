package com.personal.dailynews.util.network.apis

import com.personal.dailynews.data.model.Article
import com.personal.dailynews.data.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    /**
     * Get Top News Headlines from selected countries
     * @param country Origin country for news articles. Default is 'us'
     */
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String = "us"):Observable<Response>

    /**
     * Get News Artciles based on the selected subjects
     * @param query category name to filter results with. Default is 'bitcoin'. Options [' bitcoin', 'apple', 'earthquake', 'animal']
     */
    @GET("everything")
    fun getFilteredArticles(@Query("q") query: String = "bitcoin"):Observable<Response>
}
