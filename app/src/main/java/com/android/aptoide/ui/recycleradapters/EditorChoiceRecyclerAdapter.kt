package com.android.aptoide.ui.recycleradapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.aptoide.R
import com.android.aptoide.models.App
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.qualifiers.ApplicationContext

class EditorChoiceRecyclerAdapter(
    @ApplicationContext context: Context,
    posts: MutableList<App>
): LiveRecyclerViewAdapter<App, EditorChoiceRecyclerAdapter.ViewHolder>(context, posts) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.item_editor_choice_card, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), IBindableViewHolder<App>, RequestListener<Drawable> {
        private val backgroundImageView: ImageView = itemView.findViewById(R.id.card_background)
        private val title: TextView = itemView.findViewById(R.id.card_app_title)
        private val stars: TextView = itemView.findViewById(R.id.card_app_stars)

        private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)

        override fun bind(value: App) {
            value.graphic?.run {
                Glide
                    .with(backgroundImageView.context)
                    .load(this)
                    .centerCrop()
                    .timeout(3000)
                    .listener(this@ViewHolder)
                    .into(backgroundImageView)
            }
            title.text = value.name
            stars.text = if (value.rating > 0.0) value.rating.toString() else "--"

        }

        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            onImageLoadFinish()
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            onImageLoadFinish()
            return false
        }

        private fun onImageLoadFinish() {
            progressBar.visibility = View.GONE
            title.visibility = View.VISIBLE
            stars.visibility = View.VISIBLE
        }
    }
}