package com.r.wikipedia.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.holders.CardHolder
import com.r.wikipedia.wikipedia.models.WikiPage

class ArticleCardAdapter() : RecyclerView.Adapter<CardHolder>() {

    val currentResults : ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent?.context).
            inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return currentResults.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        var page = currentResults[position]
        holder?.updateWithPage(page)
    }

}