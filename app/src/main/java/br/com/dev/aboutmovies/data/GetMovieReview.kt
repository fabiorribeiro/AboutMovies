package br.com.dev.aboutmovies.data

import androidx.lifecycle.LiveData
import br.com.dev.aboutmovies.models.Review

interface GetMovieReview {
    fun getAllReviews(order_by: String): LiveData<List<Review>>
    fun getReviwesByCriticsPick(order_by: String): LiveData<List<Review>>
    fun getReviwesQuery(search: String, order_by: String): LiveData<List<Review>>
    fun getFavorites(order_by: String): LiveData<List<Review>> { return object: LiveData<List<Review>>(){} }
}