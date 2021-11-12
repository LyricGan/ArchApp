package com.lyricgan.arch.app.presenter

import com.lyricgan.arch.app.contract.DetailContract
import com.lyricgan.arch.app.model.DetailRepository

/**
 * 详情Presenter
 * @author Lyric Gan
 */
class DetailPresenter(
    private val view: DetailContract.View,
    private val repository: DetailRepository
) : DetailContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {
    }

    override fun getDetail(url: String) {

    }
}