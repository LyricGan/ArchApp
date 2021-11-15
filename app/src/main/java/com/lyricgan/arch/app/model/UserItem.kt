package com.lyricgan.arch.app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 用户实体类
 * @author Lyric Gan
 */
@Parcelize
data class UserItem(
    var id: Long? = 0,
    var name: String? = "",
    var url: String? = "",
    var avatarUrl: String? = "",
    var homepage: String? = ""
) : Parcelable
