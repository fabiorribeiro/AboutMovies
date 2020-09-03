package br.com.dev.aboutmovies.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.dev.aboutmovies.models.Review
import br.com.dev.aboutmovies.utilities.Converters
import br.com.dev.sobrefilmes.utilities.DATABASE_NAME
import br.com.dev.sobrefilmes.utilities.DB_VERSION

@Database(entities = [Review::class], version = DB_VERSION)
@TypeConverters(Converters::class)
abstract class AboutMovieDB: RoomDatabase() {
    companion object{
        private var INSTANCE: AboutMovieDB? = null

        fun get(context: Context): AboutMovieDB {
            return INSTANCE?: run {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AboutMovieDB::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun movieReviews(): MovieReviewsDAO
}