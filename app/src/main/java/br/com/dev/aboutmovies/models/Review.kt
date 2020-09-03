package br.com.dev.aboutmovies.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.dev.sobrefilmes.models.Link
import br.com.dev.sobrefilmes.models.MultiMedia

@Entity
class Review(
    @PrimaryKey
    val display_title: String = "",
    val byline: String? = "",
    val critics_pick: String? = "",
    val date_updated: String? = "",
    val headline: String? = "",
    val link: Link? = Link(),
    val mpaa_rating: String? = "",
    val multimedia: MultiMedia? = MultiMedia(),
    val opening_date: String? = "",
    val publication_date: String? = "",
    val summary_short: String? = "",
    var favorite: Boolean = false)
