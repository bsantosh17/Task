package com.example.mymvvm_retrofit_coroutine.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvm_retrofit_coroutine.databinding.ActivityMainBinding
import com.example.mymvvm_retrofit_coroutine.repository.MainRepository
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.CoinAdapter
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MyViewModelFactory
import com.example.mymvvm_retrofit_coroutine.network.RetrofitService

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val adapter = CoinAdapter(this)
    lateinit var binding: ActivityMainBinding

    lateinit var pd:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        pd = ProgressDialog(this)
        pd.show()
        pd.setCancelable(false)

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)


        viewModel.coinList.observe(this) {
           pd.hide()
            it?.let { it1 -> adapter.setMovies(it1) }
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }


        viewModel.getAllMovies()

    }
}