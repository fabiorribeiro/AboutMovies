package br.com.dev.aboutmovies.models

class ResultReviews(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<Review>)
