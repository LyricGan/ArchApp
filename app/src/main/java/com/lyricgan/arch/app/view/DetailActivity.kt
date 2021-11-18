package com.lyricgan.arch.app.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lyricgan.arch.app.contract.DetailContract
import com.lyricgan.arch.app.databinding.ActivityDetailBinding
import com.lyricgan.arch.app.model.DetailRepository
import com.lyricgan.arch.app.model.RepositoryItem
import com.lyricgan.arch.app.model.UserItem
import com.lyricgan.arch.app.presenter.DetailPresenter

/**
 * 详情页
 * @author Lyric Gan
 */
class DetailActivity : AppCompatActivity(), DetailContract.View {
    override lateinit var presenter: DetailContract.Presenter
    private lateinit var viewBinding: ActivityDetailBinding
    private lateinit var repository: DetailRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        repository = DetailRepository()
        presenter = DetailPresenter(this, repository)

        val item: RepositoryItem? = intent.getParcelableExtra("params")
        item?.let {
            showRepositoryViews(it)
            it.owner?.url?.let { url ->
                (presenter as DetailPresenter).loadUser(url)
            }
        }
    }

    override fun showLoading() {
        viewBinding.progress.visibility = View.VISIBLE
    }

    override fun showUser(userItem: UserItem) {
        runOnUiThread {
            viewBinding.progress.visibility = View.GONE
            viewBinding.tvOwnerName.text = userItem.name
            viewBinding.tvOwnerHomepage.text = userItem.homepage
        }
    }

    override fun showMessage(message: String) {
        runOnUiThread {
            viewBinding.progress.visibility = View.GONE
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showRepositoryViews(item: RepositoryItem) {
        viewBinding.tvName.text = item.name
        viewBinding.tvDescription.text = item.description
        viewBinding.tvHomepage.text = item.homepage
        viewBinding.tvWatchers.text = item.getWatchersStr()
        viewBinding.tvForks.text = item.getForksStr()
        viewBinding.tvStars.text = item.getStarsStr()
    }
}