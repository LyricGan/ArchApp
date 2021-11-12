package com.lyricgan.arch.app.contract

import com.lyricgan.arch.app.BasePresenter
import com.lyricgan.arch.app.BaseView

/**
 * 详情需要实现的接口
 * @author Lyric Gan
 */
interface DetailContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun showContent()

        fun showMessage()
    }

    interface Presenter : BasePresenter {

        fun getDetail(url: String)
    }
}