package com.r.wikipedia.wikipedia

import android.app.Application
import com.r.wikipedia.wikipedia.managers.WikiManager
import com.r.wikipedia.wikipedia.providers.ArticleDataProvider
import com.r.wikipedia.wikipedia.repositories.AritcleDatabaseOpenHelper
import com.r.wikipedia.wikipedia.repositories.FavouritesRepository
import com.r.wikipedia.wikipedia.repositories.HistoryRepositories

class WikiApplication : Application() {
    private var dbHelper: AritcleDatabaseOpenHelper? = null
    private var favouritesRepository: FavouritesRepository? = null
    private var historyRepository: HistoryRepositories? = null
    private var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set


    override fun onCreate() {
        super.onCreate()

        dbHelper = AritcleDatabaseOpenHelper(applicationContext)
        favouritesRepository = FavouritesRepository(dbHelper!!)
        historyRepository = HistoryRepositories(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favouritesRepository!!, historyRepository!!)

    }


}