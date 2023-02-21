package com.lyricgan.arch.app.model

import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * 主页数据仓库
 * @author Lyric Gan
 */
class MainRepository {

    fun search(name: String, callback: RepositoryCallback) {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url("https://api.github.com/users/$name/repos")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val str = response.body?.string()
                    str?.let { callback.onSuccess(parseItems(it)) } ?: callback.onFailed()
                } else {
                    callback.onFailed()
                }
            }
        })
    }

    private fun parseItems(str: String): List<RepositoryItem> {
        val items = ArrayList<RepositoryItem>()
        val jsonArray = JSONArray(str)
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.optJSONObject(i)
            val item = RepositoryItem()
            parseItem(item, obj)

            items.add(item)
        }
        return items
    }

    private fun parseItem(item: RepositoryItem, jsonObject: JSONObject) {
        item.id = jsonObject.optLong("id")
        item.name = jsonObject.optString("name")
        item.description = jsonObject.optString("description", "")
        item.watchers = jsonObject.optInt("watchers_count")
        item.stars = jsonObject.optInt("stargazers_count")
        item.forks = jsonObject.optInt("forks_count")
        item.homepage = jsonObject.optString("html_url")
        val ownerObject = jsonObject.optJSONObject("owner")
        ownerObject?.let {
            val userItem = UserItem()
            userItem.id = it.optLong("id")
            userItem.name = it.optString("name")
            userItem.url = it.optString("url")
            item.owner = userItem
        }
    }

    interface RepositoryCallback {

        fun onFailed()

        fun onSuccess(items: List<RepositoryItem>)
    }
}