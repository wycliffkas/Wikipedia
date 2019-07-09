package com.r.wikipedia.wikipedia.repositories

import com.google.gson.Gson
import com.r.wikipedia.wikipedia.models.WikiPage
import com.r.wikipedia.wikipedia.models.WikiThumbnail
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select

class FavouritesRepository(val databaseHelper: AritcleDatabaseOpenHelper) {
    private val TABLE_NAME: String = "Favourites"

    fun addFavorites(page: WikiPage){
        databaseHelper.use {
            insert(TABLE_NAME,
                "id" to page.pageid,
                "title" to page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail))
        }
    }

    fun removeFavoriteById(pageId: Int) {
        databaseHelper.use {
            delete(TABLE_NAME, "id = {pageId}", "pageId" to pageId)
        }
    }

    fun isArticleFavorite(pageId: Int) : Boolean {
        var pages = getAllFavorite()
        return pages.any{ page ->
        page.pageid  == pageId
        }

    }

    fun getAllFavorite(): ArrayList<WikiPage>{
        var pages =  ArrayList<WikiPage>()

        val articleRowParser = rowParser { id: Int, title: String, url: String, thumbnailJson: String ->
            val page = WikiPage()
            page.title = title
            page.pageid = id
            page.fullurl = url
            page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)

            pages.add(page)
        }

        databaseHelper.use {
            select(TABLE_NAME).parseList(articleRowParser)
        }

        return pages
    }

}