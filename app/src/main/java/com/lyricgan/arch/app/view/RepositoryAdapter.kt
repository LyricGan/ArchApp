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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = items?.get(position)
        holder.viewBinding.tvName.text = item?.name
        holder.viewBinding.tvDescription.text = item?.description
        holder.viewBinding.tvForks.text = item?.getForksStr()
        holder.viewBinding.tvWatchers.text = item?.getWatchersStr()
        holder.viewBinding.tvStars.text = item?.getStarsStr()
        holder.viewBinding.layoutContent.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun setItems(items: List<RepositoryItem>) {
        this.items = items
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewBinding: ItemLayoutBinding = ItemLayoutBinding.bind(itemView)
    }
}