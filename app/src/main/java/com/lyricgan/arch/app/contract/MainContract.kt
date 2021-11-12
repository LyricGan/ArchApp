package com.lyricgan.arch.app.contract

import com.lyricgan.arch.app.BasePresenter
import com.lyricgan.arch.app.BaseView

/**
 * 主页契约接口
 * @author Lyric Gan
 */
interface MainContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

    }
}