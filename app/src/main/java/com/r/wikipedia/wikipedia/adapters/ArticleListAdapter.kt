package com.r.wikipedia.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.holders.ListItemHolder

class ArticleListAdapter() : RecyclerView.Adapter<ListItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var listItem = LayoutInflater.from(parent?.context).
            inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
         //To change body of created functions use File | Settings | File Templates.
    }

}