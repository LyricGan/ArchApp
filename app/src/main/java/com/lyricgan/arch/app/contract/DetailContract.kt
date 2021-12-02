package com.lyricgan.arch.app.contract

import com.lyricgan.arch.app.BasePresenter
import com.lyricgan.arch.app.BaseView
import com.lyricgan.arch.app.model.UserItem

/**
 * 详情需要实现的接口
 * @author Lyric Gan
 */
interface DetailContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun showUser(userItem: UserItem)

        fun showLoadFailed()
    }

    interface Presenter : BasePresenter {

        fun loadUser(url: String)
    }
}