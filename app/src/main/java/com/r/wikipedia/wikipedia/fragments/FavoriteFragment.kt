package com.r.wikipedia.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.WikiApplication
import com.r.wikipedia.wikipedia.adapters.ArticleCardAdapter
import com.r.wikipedia.wikipedia.adapters.ArticleListAdapter
import com.r.wikipedia.wikipedia.managers.WikiManager
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    private var wikiManager: WikiManager? = null
    var favoriteRecyclerView : RecyclerView? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorite, container, false)

        favoriteRecyclerView = view.findViewById(R.id.favourite_article_recycler)
        favoriteRecyclerView!!.layoutManager = LinearLayoutManager(context)
        favoriteRecyclerView!!.adapter = ArticleCardAdapter()

        return view
    }


}
