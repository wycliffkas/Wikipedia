package com.r.wikipedia.wikipedia.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.r.wikipedia.R
import kotlinx.android.synthetic.main.article_card_item.view.*

class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val resultIcon : ImageView = itemView.findViewById(R.id.result_icon)
    private val resultTitle : TextView = itemView.findViewById(R.id.result_title )
}