package com.lyricgan.arch.app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyricgan.arch.app.contract.DetailContract
import com.lyricgan.arch.app.databinding.ActivityDetailBinding

/**
 * 详情页
 * @author Lyric Gan
 */
class DetailActivity : AppCompatActivity(), DetailContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun setPresenter(presenter: DetailContract.Presenter) {

    }
}