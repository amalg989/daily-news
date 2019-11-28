package com.personal.dailynews.ui.news.details

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.personal.dailynews.R
import com.personal.dailynews.data.model.Article
import com.personal.dailynews.databinding.ActivityNewsDetailsBinding
import com.personal.dailynews.ui.news.NewsArticleViewModel

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailsBinding

    companion object {
        @JvmStatic
        fun getStartIntent(
            context: Context,
            article: Article
        ): Intent {
            val intent = Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra("article", Gson().toJson(article))
            }

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            val viewModel = NewsArticleViewModel()
            val article: Article = Gson().fromJson(intent?.extras?.getString("article"), Article::class.java)
            viewModel.bind(article)
            binding.viewModel = viewModel
            binding.lifecycleOwner = this

            val fab = binding.root.findViewById<FloatingActionButton>(R.id.fab)

            fab.setOnClickListener {
                Log.d("NewsDetailsActivity", "viewModel.getLink().value ${viewModel.getLink().value}")
                this.openLinkInBrowser(viewModel.getLink().value?:"")
            }
        } catch (e: Exception) {
            Log.d("NewsDetailsActivity", "Binding error")
            e.printStackTrace()
        }
    }

    private fun openLinkInBrowser(url: String) {
        if(url == "") {
            Toast.makeText(applicationContext, "Cannot open link", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}