package uz.najottalim.dasturchiafandi.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import uz.najottalim.dasturchiafandi.model.Joke

private const val BASE_URL = "https://official-joke-api.appspot.com"

interface JokeService {

    @GET("/random_joke")
    fun getRandomJoke(): Call<Joke>

}

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

object JokeApi {

    val jokeService: JokeService by lazy { retrofit.create(JokeService::class.java) }

}

