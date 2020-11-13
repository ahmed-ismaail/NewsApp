package com.example.newsapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.data.models.Article
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news), NewsAdapter.OnClickListener {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        newsAdapter = NewsAdapter(this)
        rvSavedNews.apply {
            adapter = newsAdapter
            rvSavedNews.layoutManager = LinearLayoutManager(activity)
        }

        //used to delete article when swipe right or left
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view, "Article deleted successfully", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(rvSavedNews)
        }


        viewModel.getAllSavedArticles().observe(viewLifecycleOwner, Observer { articles ->
            //newsAdapter.differ will calculate the differences between the new list and
            //the old list then update the recyclerView
            newsAdapter.differ.submitList(articles)
        })


    }

    override fun onArticleItemClickListener(article: Article) {
        val bundle = Bundle().apply {
            putSerializable("article", article)
        }

        findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment, bundle)
    }
}