package com.r.wikipedia.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.WikiApplication
import com.r.wikipedia.wikipedia.adapters.ArticleCardAdapter
import com.r.wikipedia.wikipedia.adapters.ArticleListAdapter
import com.r.wikipedia.wikipedia.managers.WikiManager
import com.r.wikipedia.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_details.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class HistoryFragment : Fragment() {
    private var wikiManager: WikiManager? = null
    var historyRecycler : RecyclerView? = null
    private var adapter: ArticleCardAdapter = ArticleCardAdapter()

    init {
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById(R.id.history_article_recycler)

        historyRecycler!!.layoutManager = LinearLayoutManager(context)
        historyRecycler!!.adapter = adapter



        return view
    }

    override fun onResume() {
        super.onResume()

        doAsync {
            val history = wikiManager!!.getHistory()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(history as ArrayList<WikiPage>)
            activity?.runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.clear_history){
            activity!!.alert(
                "Are you sure you want to clear History", "confirm"){
                yesButton {
                    adapter.currentResults.clear();
                    doAsync {
                        wikiManager?.clearHistory()
                    }
                    activity!!.runOnUiThread { adapter.notifyDataSetChanged() }
                }
                noButton {

                }

            }.show()
        }

        return true
    }


}
