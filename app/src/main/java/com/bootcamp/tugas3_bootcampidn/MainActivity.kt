package com.bootcamp.tugas3_bootcampidn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.tugas3_bootcampidn.adapter.NewsAdapter
import com.bootcamp.tugas3_bootcampidn.api.ApiConfig
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityMainBinding
import com.bootcamp.tugas3_bootcampidn.model.ArticlesItem
import com.bootcamp.tugas3_bootcampidn.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Home"

    ApiConfig.getService().getNews().enqueue(object : Callback<News>{
        override fun onResponse(call: Call<News>, response: Response<News>) {
            Log.d("RESPONSE", response.isSuccessful.toString())
            if(response.isSuccessful) {
                val news = response.body()
                val dataNews = news?.articles
                val newsAdapter = NewsAdapter()
                newsAdapter.setData(dataNews as List<ArticlesItem>)
                binding.rvNews.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    setHasFixedSize(true)
                    adapter = newsAdapter
                }
            }
        }

        override fun onFailure(call: Call<News>, t: Throwable) {
            Log.d("gagal", "onFailure: " + t.localizedMessage)
        }

    })
    }
}