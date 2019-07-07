package com.r.wikipedia.wikipedia.managers

import com.r.wikipedia.wikipedia.models.WikiPage
import com.r.wikipedia.wikipedia.models.WikiResult
import com.r.wikipedia.wikipedia.providers.ArticleDataProvider
import com.r.wikipedia.wikipedia.repositories.FavouritesRepository
import com.r.wikipedia.wikipedia.repositories.HistoryRepositories

class WikiManager(private val provider: ArticleDataProvider,
                  private val favouritesRepository: FavouritesRepository,
                  private val historyRepositories: HistoryRepositories) {

    private var favoritesCache: ArrayList<WikiPage>? = null
    private var historyCache: ArrayList<WikiPage>? = null

    fun search(term: String, skip: Int, take: Int, handler: (result: WikiResult) -> Unit?){
        return provider.search(term, skip, take, handler)
    }

    fun getRandom(take: Int, handler: (result: WikiResult) -> Unit?){
        return provider.getRandom(take, handler)
    }

    fun getHistory(): ArrayList<WikiPage>? {
        if( historyCache == null)
            historyCache = historyRepositories.getAllHistory()
        return historyCache
    }

    fun getFavorites(): ArrayList<WikiPage>? {
        if( favoritesCache == null)
            favoritesCache = favouritesRepository.getAllFavorite()
        return favoritesCache
    }

    fun addFavourite(page: WikiPage) {
        favoritesCache?.add(page)
        favouritesRepository.addFavorites(page)
    }

    fun removeFavorite(pageId: Int) {
        favouritesRepository.removeFavoriteById(pageId)
        favoritesCache = favoritesCache!!.filter { it.pageid != pageId } as ArrayList<WikiPage>
    }

    fun getIsFavorite(pageId: Int): Boolean{
        return favouritesRepository.isArticleFavorite(pageId)
    }

    fun addHistory(page: WikiPage) {
        historyCache?.add(page)
        historyRepositories.addHistory(page)
    }

    fun clearHistory(){
        historyCache?.clear()
        val allHistory = historyRepositories.getAllHistory()
        allHistory?.forEach { historyRepositories.removeFavoriteById(it.pageid!!) }
    }






}