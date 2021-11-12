package com.lyricgan.arch.app.contract

import com.lyricgan.arch.app.BasePresenter
import com.lyricgan.arch.app.BaseView
import com.lyricgan.arch.app.model.RepositoryItem

/**
 * 主页需要实现的接口
 * @author Lyric Gan
 */
interface MainContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun showContent(repositoryItems: List<RepositoryItem>)

        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter {

        fun search(name: String)
    }
}