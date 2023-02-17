package com.bootcamp.tugas3_bootcampidn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityDetailNewsBinding
import com.bootcamp.tugas3_bootcampidn.model.ArticlesItem
import com.bumptech.glide.Glide

class DetailNewsActivity : AppCompatActivity() {
	private lateinit var binding: ActivityDetailNewsBinding

	companion object {
		const val EXTRA_NEWS = "extra_news"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailNewsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportActionBar?.title = "Detail"

		val data = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)

		binding.apply {
			tvJudul.text = data?.title
			tvDeskripsi.text = data?.description

			Glide.with(this@DetailNewsActivity)
				.load(data?.urlToImage)
				.error(R.drawable.ic_launcher_background)
				.into(imgNews)
		}
	}
}