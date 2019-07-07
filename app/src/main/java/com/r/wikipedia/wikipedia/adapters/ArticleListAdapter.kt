package com.r.wikipedia.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.holders.ListItemHolder
import com.r.wikipedia.wikipedia.models.WikiPage

class ArticleListAdapter() : RecyclerView.Adapter<ListItemHolder>() {
    val currentResults : ArrayList<WikiPage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var listItem = LayoutInflater.from(parent?.context).
            inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)
    }

    override fun getItemCount(): Int {
        return currentResults.size
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
         val page = currentResults[position]
         holder.updateWithPage(page)
    }

}