package com.lyricgan.arch.app.model

import okhttp3.*
import org.json.JSONObject
import java.io.IOException

/**
 * 详情数据仓库
 * @author Lyric Gan
 */
class DetailRepository {

    fun loadUser(url: String, callback: ResponseCallback) {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val str = response.body?.string()
                    str?.let { callback.onSuccess(parseUser(it)) } ?: callback.onFailed()
                } else {
                    callback.onFailed()
                }
            }
        })
    }

    fun parseUser(str: String) : UserItem {
        val jsonObject = JSONObject(str)
        val userItem = UserItem()
        userItem.id = jsonObject.optLong("id")
        userItem.name = jsonObject.optString("name")
        userItem.url = jsonObject.optString("url")
        userItem.avatarUrl = jsonObject.optString("avatar_url")
        userItem.homepage = jsonObject.optString("html_url")

        return userItem
    }

    interface ResponseCallback {

        fun onFailed()

        fun onSuccess(userItem: UserItem)
    }
}