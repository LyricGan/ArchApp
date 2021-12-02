package com.lyricgan.arch.app.presenter

import com.lyricgan.arch.app.contract.DetailContract
import com.lyricgan.arch.app.model.DetailRepository
import com.lyricgan.arch.app.model.UserItem

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

    override fun loadUser(url: String) {
        view.showLoading()
        repository.loadUser(url, object : DetailRepository.ResponseCallback {
            override fun onFailed() {
                view.showLoadFailed()
            }

            override fun onSuccess(userItem: UserItem) {
                view.showUser(userItem)
            }
        })
    }
}