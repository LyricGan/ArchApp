package com.lyricgan.arch.app.model

/**
 * 用户仓库实体类
 * @author Lyric Gan
 */
data class RepositoryItem constructor(
    var id: Long? = 0,
    var name: String? = "",
    var description: String? = "",
    var watchers: Int? = 0,
    var stars: Int? = 0,
    var forks: Int? = 0,
    var owner: UserItem? = null
) {

    fun getWatchersStr(): String {
        return "$watchers \nWatch"
    }

    fun getStarsStr(): String {
        return "$stars \nStar"
    }

    fun getForksStr(): String {
        return "$forks \nFork"
    }
}
