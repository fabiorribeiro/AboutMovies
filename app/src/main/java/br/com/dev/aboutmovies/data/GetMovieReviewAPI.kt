package br.com.dev.aboutmovies.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.dev.aboutmovies.api.MovieReviewService
import br.com.dev.aboutmovies.models.ResultReviews
import br.com.dev.aboutmovies.models.Review
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMovieReviewAPI: GetMovieReview {
    private val api = MovieReviewService.getInstance()
    var offset: Int = 0

    override fun getAllReviews(order_by: String): LiveData<List<Review>> {
        val cb = CallbackMovieReview()
        api.getAllReviews(offset, order_by).enqueue(cb.callback)
        return cb.result
    }

    override fun getReviwesByCriticsPick(order_by: String): LiveData<List<Review>> {
        val cb = CallbackMovieReview()
        api.getReviwesByCriticsPick(offset, order_by).enqueue(cb.callback)
        return cb.result
    }

    override fun getReviwesQuery(search: String, order_by: String): LiveData<List<Review>> {
        val cb = CallbackMovieReview()
        api.getReviwesQuery(search, offset, order_by).enqueue(cb.callback)
        return cb.result
    }

    private class CallbackMovieReview {
        val result = MutableLiveData<List<Review>>()

        val callback = object: Callback<ResultReviews> {
            override fun onResponse(call: Call<ResultReviews>, response: Response<ResultReviews>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        result.value = it.results
                    }
                }
            }

            override fun onFailure(call: Call<ResultReviews>, t: Throwable) {
                Log.d("API", t.message.toString())
            }
        }
    }
}