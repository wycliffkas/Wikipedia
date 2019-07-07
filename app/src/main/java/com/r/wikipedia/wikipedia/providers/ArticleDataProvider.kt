package com.r.wikipedia.wikipedia.providers

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.core.ResponseHandler
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.r.wikipedia.wikipedia.models.Url
import com.r.wikipedia.wikipedia.models.WikiResult
import java.io.Reader

class ArticleDataProvider {

    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "wycliff wikipedia")
    }

     fun search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?){
         Url.getSearchUrl(term, skip, take).httpGet()
             .responseObject(WikipediaDataSerializer()){ _, response, result ->
                 if(response.statusCode != 200){
                     throw Exception("unable to get articles")
                 }
                 val(data, _) = result
                 responseHandler.invoke(data as WikiResult)
             }
     }

    fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?){
        Url.getRandomUrl(take).httpGet()
            .responseObject(WikipediaDataSerializer()){ _, response, result ->

                if(response.statusCode != 200){
                    throw Exception("unable to get articles")
                }
                val(data, _) = result
                responseHandler.invoke(data as WikiResult)
            }
    }

     class WikipediaDataSerializer : ResponseDeserializable<WikiResult> {
         override fun deserialize(reader: Reader) = Gson().fromJson(reader, WikiResult::class.java)!!
     }
}