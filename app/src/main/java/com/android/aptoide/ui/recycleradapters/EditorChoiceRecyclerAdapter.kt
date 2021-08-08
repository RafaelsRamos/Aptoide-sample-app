package com.android.aptoide.ui.recycleradapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.aptoide.R
import com.android.aptoide.models.App
import dagger.hilt.android.qualifiers.ApplicationContext

class EditorChoiceRecyclerAdapter(@ApplicationContext context: Context, posts: MutableList<App>): LiveRecyclerViewAdapter<App, EditorChoiceRecyclerAdapter.ViewHolder>(context, posts) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.layout_editor_choice_card, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), IBindableViewHolder<App> {
        val backgroundImageView: ImageView = itemView.findViewById(R.id.card_background)
        val title: TextView = itemView.findViewById(R.id.card_app_title)
        val stars: TextView = itemView.findViewById(R.id.card_app_stars)

        override fun bind(value: App) {
            title.text = value.name
            stars.text = value.rating.toString()
        }
    }
}