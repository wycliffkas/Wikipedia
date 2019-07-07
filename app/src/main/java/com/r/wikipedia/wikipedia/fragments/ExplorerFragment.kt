package com.r.wikipedia.wikipedia.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.activities.SearchActivity
import com.r.wikipedia.wikipedia.adapters.ArticleCardAdapter
import com.r.wikipedia.wikipedia.models.WikiResult
import com.r.wikipedia.wikipedia.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.fragment_explorer.*
import kotlinx.android.synthetic.main.fragment_explorer.view.*
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ExplorerFragment : Fragment() {

    private val articleProvider : ArticleDataProvider = ArticleDataProvider()
    var searchCardView : CardView? = null
    var exploreRecycler : RecyclerView? = null
    var adapter: ArticleCardAdapter = ArticleCardAdapter()
    var refresher: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_explorer, container, false)


        searchCardView = view.findViewById(R.id.search_card_view)
        exploreRecycler = view.findViewById(R.id.explore_article_recycler)
        refresher = view.findViewById(R.id.refresher)

        searchCardView!!.setOnClickListener {
            val searchIntent = Intent(context, SearchActivity::class.java )
            context!!.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            getRandomArticles()
        }

        getRandomArticles()

        return view

    }

    private fun getRandomArticles(){
        refresher?.isRefreshing = true

        try {
            articleProvider.getRandom(15) { wikiResult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiResult.query!!.pages)
                activity!!.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher?.isRefreshing = false

                }
            }
        }
        catch (ex: Exception){
            val builder = AlertDialog.Builder(activity!!)
            builder.setMessage(ex.message).setTitle("oops!")
            val dialog = builder.create()
            dialog.show()
        }

    }
}
