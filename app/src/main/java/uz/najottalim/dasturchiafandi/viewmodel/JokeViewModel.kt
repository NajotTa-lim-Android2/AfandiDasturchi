package uz.najottalim.dasturchiafandi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.najottalim.dasturchiafandi.model.Joke
import uz.najottalim.dasturchiafandi.model.JokeDao
import uz.najottalim.dasturchiafandi.network.JokeApi
import kotlin.random.Random

class JokeViewModel(val jokeDao: JokeDao) : ViewModel() {

    val jokeLive = MutableLiveData<Joke>()

    init {
        fetchJoke()


    }




    fun refreshJoke() {
        fetchJoke()
    }


    private fun fetchJoke() {
        JokeApi.jokeService.getRandomJoke().enqueue(object : Callback<Joke> {

            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {

                if (response.isSuccessful) {

                    val body = response.body()


                    if (body != null) {
                        val joke: Joke = body

                        jokeLive.value = joke

                    } else {


                        //                        Snackbar.make(binding.root, "Ma'lumotni olishda xatolik yuz berdi", Snackbar.LENGTH_SHORT).show()
                    }

                } else {
                    //                    Snackbar.make(binding.root, "Ma'lumotni olishda xatolik yuz berdi", Snackbar.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {

                //                Log.d(TAG, "onFailure: sldkf")
                //                Snackbar.make(binding.root, "Requestni jo'natishda xatolik", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    class JokeViewModelFactory(val jokeDao: JokeDao) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {

                return JokeViewModel(jokeDao = jokeDao) as T

            }

            throw IllegalArgumentException("Not assignable ViewModel")
        }


    }

}