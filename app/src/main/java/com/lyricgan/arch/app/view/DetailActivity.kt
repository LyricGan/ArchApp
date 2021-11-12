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
    override lateinit var presenter: DetailContract.Presenter
    private lateinit var viewBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun showLoading() {
    }

    override fun showContent() {
    }

    override fun showMessage() {
    }
}