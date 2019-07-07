package com.r.wikipedia.wikipedia.holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.activities.ArticleDetailsActivity
import com.r.wikipedia.wikipedia.models.WikiPage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_card_item.view.*

class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleImageView : ImageView = itemView.findViewById(R.id.article_image)
    private val titleTextView : TextView = itemView.findViewById(R.id.article_title)

    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener { view: View? ->
            var detailPageIntent = Intent(itemView.context, ArticleDetailsActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWithPage(page: WikiPage){

        currentPage = page

        titleTextView.text = page.title

        if(page.thumbnail != null)
            Picasso.get()
                .load(page.thumbnail!!.source)
                .into(articleImageView)
    }
}