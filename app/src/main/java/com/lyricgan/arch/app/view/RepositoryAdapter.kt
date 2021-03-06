package com.lyricgan.arch.app.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lyricgan.arch.app.R
import com.lyricgan.arch.app.databinding.ItemLayoutBinding
import com.lyricgan.arch.app.model.RepositoryItem

/**
 * 列表适配器
 * @author Lyric Gan
 */
class RepositoryAdapter(private val context: Context): RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private var items: List<RepositoryItem>? = null
    private var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        val viewHolder = RepositoryViewHolder(itemView)
        viewHolder.viewBinding.layoutContent.setOnClickListener {
            callback?.onItemClick(viewHolder.item)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        items?.get(position)?.let {
            holder.viewBinding.tvName.text = it.name
            holder.viewBinding.tvDescription.text = it.description
            holder.viewBinding.tvWatchers.text = it.getWatchersStr()
            holder.viewBinding.tvStars.text = it.getStarsStr()
            holder.viewBinding.tvForks.text = it.getForksStr()
            holder.item = it
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun setItems(items: List<RepositoryItem>) {
        this.items = items
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewBinding: ItemLayoutBinding = ItemLayoutBinding.bind(itemView)
        var item: RepositoryItem? = null
    }

    interface Callback {

        fun onItemClick(item : RepositoryItem?)
    }
}