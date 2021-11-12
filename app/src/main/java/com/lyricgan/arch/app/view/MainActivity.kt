package com.lyricgan.arch.app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyricgan.arch.app.contract.MainContract
import com.lyricgan.arch.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {

    }
}