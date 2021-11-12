package com.lyricgan.arch.app.contract

import com.lyricgan.arch.app.BasePresenter
import com.lyricgan.arch.app.BaseView

/**
 * 详情契约接口
 * @author Lyric Gan
 */
interface DetailContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

    }
}