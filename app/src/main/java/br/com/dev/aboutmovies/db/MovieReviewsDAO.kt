package br.com.dev.aboutmovies.db

import androidx.room.*
import br.com.dev.aboutmovies.models.Review
import br.com.dev.sobrefilmes.utilities.ORDER_BY_TITLE

@Dao
interface MovieReviewsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addReviews(reviews: List<Review>)

    @Update
    fun updateReview(review: Review)

    @Query("SELECT * FROM Review ORDER BY :order_by")
    fun getAllReviews(order_by: String = ORDER_BY_TITLE): List<Review>

    @Query("SELECT * FROM Review WHERE critics_pick = '1' ORDER BY :order_by")
    fun getReviwesByCriticsPick(order_by: String = ORDER_BY_TITLE): List<Review>

    @Query("SELECT * FROM Review WHERE (display_title || byline || headline || summary_short) LIKE :search ORDER BY :order_by")
    fun getReviwesQuery(search: String, order_by: String): List<Review>

    @Query("SELECT * FROM Review WHERE favorite = 1 ORDER BY :order_by")
    fun getFavoritesReviews(order_by: String = ORDER_BY_TITLE): List<Review>

}