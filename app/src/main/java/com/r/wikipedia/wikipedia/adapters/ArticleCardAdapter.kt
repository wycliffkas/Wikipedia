package com.r.wikipedia.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.holders.CardHolder

class ArticleCardAdapter() : RecyclerView.Adapter<CardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent?.context).
            inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }

}