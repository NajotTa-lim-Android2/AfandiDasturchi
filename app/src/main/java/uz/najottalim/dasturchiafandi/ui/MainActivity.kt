package uz.najottalim.dasturchiafandi.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.najottalim.dasturchiafandi.databinding.ActivityMainBinding
import uz.najottalim.dasturchiafandi.model.Joke
import uz.najottalim.dasturchiafandi.network.JokeApi
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        JokeApi.jokeService.getRandomJoke().enqueue(object : Callback<Joke> {

            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {

                if (response.isSuccessful) {

                    val body = response.body()


                    if (body != null) {
                        val joke: Joke = body



                        binding.jokeSetupTxt.text = joke.setup
                        binding.jokePunchlineTxt.text = joke.punchline

                    } else {



                        Snackbar.make(binding.root, "Ma'lumotni olishda xatolik yuz berdi", Snackbar.LENGTH_SHORT).show()
                    }

                } else {
                    Snackbar.make(binding.root, "Ma'lumotni olishda xatolik yuz berdi", Snackbar.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {

                Log.d(TAG, "onFailure: sldkf")
                Snackbar.make(binding.root, "Requestni jo'natishda xatolik", Snackbar.LENGTH_SHORT).show()
            }
        })

    }
}