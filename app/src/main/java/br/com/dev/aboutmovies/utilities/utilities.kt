package br.com.dev.sobrefilmes.utilities

const val DATABASE_NAME = "ReviewsDB"
const val DB_VERSION = 1

const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"
const val API_KEY = "OOg6nWqYbSPxT0LoeQGQKd7llj6YDaiv"

const val ORDER_BY_TITLE = "by-title"
const val ORDER_BY_OPENING_DATE = "by-opening-date"
const val ORDER_BY_PUBLICATION_DATE = "by-publication-date"

enum class Page {
    ALL_ITEMS,
    CRITICS_PICK,
    SEARCH,
    FAVORITE
}

