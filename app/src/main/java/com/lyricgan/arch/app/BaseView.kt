package com.lyricgan.arch.app

/**
 * 基础视图接口
 * @author Lyric Gan
 */
interface BaseView<T> {

    fun setPresenter(presenter : T)
}