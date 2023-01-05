package com.lyricgan.arch.app.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyricgan.arch.app.R
import com.lyricgan.arch.app.contract.MainContract
import com.lyricgan.arch.app.databinding.ActivityMainBinding
import com.lyricgan.arch.app.model.MainRepository
import com.lyricgan.arch.app.model.RepositoryItem
import com.lyricgan.arch.app.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    override lateinit var presenter: MainContract.Presenter

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewBinding.editText.text.toString().let {
                        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(viewBinding.editText.windowToken, 0)
                        if (!TextUtils.isEmpty(it)) {
                            presenter.search(it)
                        }
                    }
                    return true
                }
                return false
            }
        })

        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        presenter = MainPresenter(this, MainRepository())
    }

    override fun showLoading() {
        showViews(true)
    }

    override fun showContent(repositoryItems: List<RepositoryItem>) {
        runOnUiThread {
            showViews(false)

            val adapter = RepositoryAdapter(this)
            adapter.setItems(repositoryItems)
            adapter.setCallback(object : RepositoryAdapter.Callback {
                override fun onItemClick(item: RepositoryItem?) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("params", item)
                    startActivity(intent)
                }
            })
            viewBinding.recyclerView.adapter = adapter
        }
    }

    override fun showLoadFailed() {
        runOnUiThread {
            viewBinding.progress.visibility = View.GONE
            Toast.makeText(this, R.string.request_failed, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showViews(loading: Boolean) {
        viewBinding.apply {
            if (loading) {
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }
}