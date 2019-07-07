package com.r.wikipedia.wikipedia.activities

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.adapters.ArticleListAdapter
import com.r.wikipedia.wikipedia.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private var articleProvider : ArticleDataProvider = ArticleDataProvider()
    private var adapter: ArticleListAdapter = ArticleListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        search_results_recycler.layoutManager = LinearLayoutManager(this)
        search_results_recycler.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu!!.findItem(R.id.search_bar)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.requestFocus()
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(query: String): Boolean {

                articleProvider.search(query, 0, 20) { wikiResult ->
                    adapter.currentResults.clear()
                    adapter.currentResults.addAll(wikiResult.query!!.pages)
                    runOnUiThread { adapter.notifyDataSetChanged() }
                }
                return false

            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }


}
