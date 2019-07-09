package com.r.wikipedia.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.WikiApplication
import com.r.wikipedia.wikipedia.adapters.ArticleCardAdapter
import com.r.wikipedia.wikipedia.adapters.ArticleListAdapter
import com.r.wikipedia.wikipedia.managers.WikiManager
import com.r.wikipedia.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.doAsync

class FavoriteFragment : Fragment() {

    private var wikiManager: WikiManager? = null
    private var favoriteRecyclerView : RecyclerView? = null
    private var adapter: ArticleCardAdapter = ArticleCardAdapter()

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
        favoriteRecyclerView!!.layoutManager = GridLayoutManager(context, 2)
        favoriteRecyclerView!!.adapter = adapter
        return view
    }

    override fun onResume() {
        super.onResume()
        doAsync{
            val favoriteArticles = wikiManager!!.getFavorites()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favoriteArticles as ArrayList<WikiPage>)
            activity?.runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }


}
