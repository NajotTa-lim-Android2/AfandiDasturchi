package uz.najottalim.dasturchiafandi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.najottalim.dasturchiafandi.model.Joke
import uz.najottalim.dasturchiafandi.model.JokeDao

@Database(entities = [Joke::class], version = 1, exportSchema = false)
abstract class JokeDatabase: RoomDatabase() {

    abstract fun jokeDao(): JokeDao

    companion object {

        @Volatile
        private var INSTANCE: JokeDatabase? = null

        fun getInstance(context: Context): JokeDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JokeDatabase::class.java,
                        "joke_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance

            }

        }

    }


}