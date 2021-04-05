package uz.najottalim.dasturchiafandi.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JokeDao {

    @Query("SELECT * FROM jokes")
    fun getAllJokes(): LiveData<List<Joke>>

    @Query("SELECT * FROM jokes WHERE id = :randomId")
    fun getRandomJoke(randomId: Int): LiveData<Joke>

    @Insert
    fun insertJoke(joke: Joke)
}
