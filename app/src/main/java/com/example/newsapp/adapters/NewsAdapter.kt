package com.example.newsapp.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter (private val onClickListener: OnClickListener): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(article_image_iv)
            source_tv.text = article.source.name
            title_tv.text = article.title
            description_tv.text = article.description
            published_at_tv.text = article.publishedAt
            setOnClickListener{
                onClickListener.onArticleItemClickListener(article)
            }
        }
    }

    //compare the new list and the old one and only update the changed items
    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallBack)

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    interface OnClickListener {
        fun onArticleItemClickListener(article: Article)
    }
}