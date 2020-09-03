package br.com.dev.aboutmovies.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import br.com.dev.aboutmovies.data.GetMovieReviewAPI
import br.com.dev.aboutmovies.data.GetMovieReviewDB
import br.com.dev.aboutmovies.db.AboutMovieDB
import br.com.dev.aboutmovies.models.Review
import br.com.dev.sobrefilmes.utilities.ORDER_BY_OPENING_DATE
import br.com.dev.sobrefilmes.utilities.ORDER_BY_TITLE
import br.com.dev.sobrefilmes.utilities.Page

class MovieReviewViewModel: ViewModel() {

    fun updateReview(context: Context, review: Review) {
        val db = GetMovieReviewDB(AboutMovieDB.get(context).movieReviews())
        db.updateReview(review)
    }

    fun getReviews(context: Context, type: Int, search: String = "", offset: Int = 0, order_by: String = ORDER_BY_TITLE, local: Boolean = true): LiveData<List<Review>> {
        val api = GetMovieReviewAPI()
        val db = GetMovieReviewDB(AboutMovieDB.get(context).movieReviews())

        api.offset = offset

        return if (local) {
            when (type) {
                Page.ALL_ITEMS.ordinal -> db.getAllReviews(order_by)
                Page.CRITICS_PICK.ordinal -> db.getReviwesByCriticsPick(ORDER_BY_OPENING_DATE)
                Page.SEARCH.ordinal -> db.getReviwesQuery(search, order_by)
                else -> db.getFavorites(order_by)
            }
        } else {
            val data = when(type) {
                Page.ALL_ITEMS.ordinal -> api.getAllReviews(order_by)
                Page.CRITICS_PICK.ordinal -> api.getReviwesByCriticsPick(ORDER_BY_OPENING_DATE)
                Page.SEARCH.ordinal -> api.getReviwesQuery(search, order_by)
                else -> api.getFavorites(order_by)
            }

            data.observeForever {
                db.addReviews(it)
            }
            data
        }
    }
}