package br.com.dev.aboutmovies.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.dev.aboutmovies.db.MovieReviewsDAO
import br.com.dev.aboutmovies.models.Review

class GetMovieReviewDB(private val db: MovieReviewsDAO): GetMovieReview {

    override fun getAllReviews(order_by: String): LiveData<List<Review>> {
        val data = MutableLiveData<List<Review>>()

        class Task: AsyncTask<Unit, Unit, List<Review>>() {
            override fun doInBackground(vararg p0: Unit?): List<Review> { return db.getAllReviews(order_by) }
            override fun onPostExecute(result: List<Review>?) {
                data.value = result
            }
        }
        Task().execute()

        return data
    }

    override fun getReviwesByCriticsPick(order_by: String): LiveData<List<Review>> {
        val data = MutableLiveData<List<Review>>()

        class Task: AsyncTask<Unit, Unit, List<Review>>() {
            override fun doInBackground(vararg p0: Unit?): List<Review> { return db.getReviwesByCriticsPick(order_by) }
            override fun onPostExecute(result: List<Review>?) { data.value = result }
        }
        Task().execute()

        return data
    }

    override fun getReviwesQuery(search: String, order_by: String): LiveData<List<Review>> {
        val data = MutableLiveData<List<Review>>()

        class Task: AsyncTask<Unit, Unit, List<Review>>() {
            override fun doInBackground(vararg p0: Unit?): List<Review> { return db.getReviwesQuery("%$search%", order_by) }
            override fun onPostExecute(result: List<Review>?) { data.value = result }
        }
        Task().execute()

        return data
    }

    override fun getFavorites(order_by: String): LiveData<List<Review>> {
        val data = MutableLiveData<List<Review>>()

        class Task: AsyncTask<Unit, Unit, List<Review>>() {
            override fun doInBackground(vararg p0: Unit?): List<Review> { return db.getFavoritesReviews(order_by) }
            override fun onPostExecute(result: List<Review>?) { data.value = result }
        }
        Task().execute()

        return data
    }

    fun addReviews(reviews: List<Review>) {
        Thread { db.addReviews(reviews) }.start()
    }

    fun updateReview(review: Review) {
        Thread { db.updateReview(review) }.start()
    }
}