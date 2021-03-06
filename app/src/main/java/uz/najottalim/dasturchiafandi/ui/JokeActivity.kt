package uz.najottalim.dasturchiafandi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.najottalim.dasturchiafandi.database.JokeDatabase
import uz.najottalim.dasturchiafandi.databinding.ActivityMainBinding
import uz.najottalim.dasturchiafandi.model.Joke
import uz.najottalim.dasturchiafandi.viewmodel.JokeViewModel

class JokeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jokeDatabase = JokeDatabase.getInstance(applicationContext)

        val jokeDao = jokeDatabase.jokeDao()

        val viewModelFactory = JokeViewModel.JokeViewModelFactory(
            jokeDao = jokeDao
        )

        viewModel = JokeViewModel(jokeDao)

        viewModel = ViewModelProvider(this, viewModelFactory).get(JokeViewModel::class.java)

        viewModel.jokeLive.observe(this, Observer { jokeLive ->

            binding.jokeSetupTxt.text = jokeLive.setup
            binding.jokePunchlineTxt.text = jokeLive.punchline

        })

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextRandomJokeBtn.setOnClickListener {

            viewModel.refreshJoke()


        }

    }
}