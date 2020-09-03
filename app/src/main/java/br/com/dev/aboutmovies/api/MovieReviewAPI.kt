package br.com.dev.aboutmovies.api

import br.com.dev.aboutmovies.models.ResultReviews
import br.com.dev.sobrefilmes.utilities.API_KEY
import br.com.dev.sobrefilmes.utilities.BASE_URL
import br.com.dev.sobrefilmes.utilities.ORDER_BY_TITLE
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieReviewAPI {
    @GET("reviews/search.json")
    fun getAllReviews(@Query("offset") offset: Int = 0,
                     @Query("order") order: String = ORDER_BY_TITLE,
                     @Query("api-key") apikey: String = API_KEY): Call<ResultReviews>

    @GET("reviews/search.json")
    fun getReviwesByCriticsPick(@Query("offset") offset: Int = 0,
                                @Query("order") order: String = ORDER_BY_TITLE,
                                @Query("critics-pick") critics_pick: String = "Y",
                                @Query("api-key") apikey: String = API_KEY): Call<ResultReviews>

    @GET("reviews/search.json")
    fun getReviwesQuery(@Query("query") query: String,
                        @Query("offset") offset: Int = 0,
                        @Query("order") order: String = ORDER_BY_TITLE,
                        @Query("api-key") apikey: String = API_KEY): Call<ResultReviews>
}

class MovieReviewService {
    companion object{
        private var INSTANCE: MovieReviewAPI? = null

        fun getInstance(): MovieReviewAPI {
            return INSTANCE?: run {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieReviewAPI::class.java)
                INSTANCE = instance
                return instance
            }
        }
    }
}