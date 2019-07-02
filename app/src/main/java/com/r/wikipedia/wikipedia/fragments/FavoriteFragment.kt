package com.r.wikipedia.wikipedia.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.adapters.ArticleCardAdapter
import com.r.wikipedia.wikipedia.adapters.ArticleListAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {

    var favoriteRecyclerView : RecyclerView? = null

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
