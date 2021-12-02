package com.lyricgan.arch.app.presenter

import com.lyricgan.arch.app.contract.MainContract
import com.lyricgan.arch.app.model.MainRepository
import com.lyricgan.arch.app.model.RepositoryItem

/**
 * 主页Presenter
 * @author Lyric Gan
 */
class MainPresenter(
    private val view: MainContract.View,
    private val repository: MainRepository
) : MainContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {
    }

    override fun search(name: String) {
        view.showLoading()
        repository.search(name, object : MainRepository.RepositoryCallback {
            override fun onFailed() {
                view.showLoadFailed()
            }

            override fun onSuccess(items: List<RepositoryItem>) {
                view.showContent(items)
            }
        })
    }
}