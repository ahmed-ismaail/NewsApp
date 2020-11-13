package com.example.newsapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.data.models.Article
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news), NewsAdapter.OnClickListener {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter : NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        newsAdapter = NewsAdapter(this)
        rvSavedNews.apply {
            adapter = newsAdapter
            rvSavedNews.layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onArticleItemClickListener(article: Article) {
        val bundle = Bundle().apply {
            putSerializable("article", article)
        }

        findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment, bundle)
    }
}