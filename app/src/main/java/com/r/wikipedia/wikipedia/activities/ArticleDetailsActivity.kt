package com.r.wikipedia.wikipedia.activities

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.WikiApplication
import com.r.wikipedia.wikipedia.managers.WikiManager
import com.r.wikipedia.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_details.*
import org.jetbrains.anko.toast
import java.lang.Exception

class ArticleDetailsActivity : AppCompatActivity() {
    private var currentPage: WikiPage? = null
    private var wikiManager: WikiManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        wikiManager = (applicationContext as WikiApplication).wikiManager

        //sets our toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        supportActionBar?.title = currentPage?.title

        article_detail_webview!!.loadUrl(currentPage!!.fullurl)


        article_detail_webview.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                // Display the loaded page title in a toast message

            }
        }

        wikiManager?.addHistory(currentPage!!)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        else if(item.itemId == R.id.action_favorite){

            try {
                if(wikiManager!!.getIsFavorite(currentPage!!.pageid!!)){
                    wikiManager!!.removeFavorite(currentPage!!.pageid!!)
                    toast("Article removed from favorites")
                }
                else {
                    wikiManager!!.addFavourite(currentPage!!)
                    toast("Article added to favorites")
                }
            }
            catch (ex: Exception){
                toast("Unable to add favorites")
            }


        }
        return true
    }


}
