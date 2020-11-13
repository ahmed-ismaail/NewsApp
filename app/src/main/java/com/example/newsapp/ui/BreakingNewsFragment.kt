package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.data.models.Article
import com.example.newsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news),
    NewsAdapter.OnClickListener {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

        newsAdapter = NewsAdapter(this)
        rvBreakingNews.apply {
            adapter = newsAdapter
            rvBreakingNews.layoutManager = LinearLayoutManager(activity)
        }

        viewModel.breakingNewsMutableLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {//when the api call is successful
                    //hide progressBar
                    paginationProgressBar.visibility = View.INVISIBLE
                    response.data?.let { newsResponse ->
                        Log.d(TAG, "An Error Occured: ${response.data}")
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {//when the api call is failed
                    //hide ProgressBar
                    paginationProgressBar.visibility = View.INVISIBLE
                    response.message?.let { errorMessage ->
                        Log.d(TAG, "An Error Occurred: $errorMessage")
                    }
                }
                is Resource.Loading -> {
                    //show ProgressBar
                    paginationProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onArticleItemClickListener(article: Article) {
        TODO("Not yet implemented")
    }
}