package com.lyricgan.arch.app.view

import android.content.Context
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
import com.lyricgan.arch.app.contract.MainContract
import com.lyricgan.arch.app.databinding.ActivityMainBinding
import com.lyricgan.arch.app.model.MainRepository
import com.lyricgan.arch.app.model.RepositoryItem
import com.lyricgan.arch.app.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    override lateinit var presenter: MainContract.Presenter

    private var viewBinding: ActivityMainBinding? = null
    private val repository: MainRepository = MainRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        viewBinding?.editText?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewBinding?.editText?.text?.toString()?.let {
                        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(viewBinding?.editText?.windowToken, 0)
                        if (!TextUtils.isEmpty(it)) {
                            presenter.search(it)
                        }
                    }
                    return true
                }
                return false
            }
        })

        val recyclerView = viewBinding?.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        presenter = MainPresenter(this, repository)
    }

    override fun showLoading() {
        viewBinding?.progress?.visibility = View.VISIBLE
        viewBinding?.recyclerView?.visibility = View.GONE
    }

    override fun showContent(repositoryItems: List<RepositoryItem>) {
        runOnUiThread {
            viewBinding?.progress?.visibility = View.GONE
            viewBinding?.recyclerView?.visibility = View.VISIBLE

            val repositoryAdapter = RepositoryAdapter(this)
            repositoryAdapter.setItems(repositoryItems)
            viewBinding?.recyclerView?.adapter = repositoryAdapter
        }
    }

    override fun showMessage(message: String) {
        runOnUiThread {
            viewBinding?.progress?.visibility = View.GONE
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}