package com.r.wikipedia.wikipedia.activities

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity() {
    private var currentPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        //sets our toolbar
        setSupportActionBar(toolbar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        article_detail_webview!!.loadUrl(currentPage!!.fullurl)

//        article_detail_webview!!.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                return true
//            }
//        }

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



    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return true
    }


}
