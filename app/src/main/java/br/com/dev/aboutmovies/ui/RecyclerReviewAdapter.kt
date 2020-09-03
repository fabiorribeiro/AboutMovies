package br.com.dev.aboutmovies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.dev.aboutmovies.R
import br.com.dev.aboutmovies.models.Review

class RecyclerReviewAdapter(val clickItem: OnItemClickListener, val clickFavorite: OnFavoriteClickListener, val clickShare: OnShareClickListener): RecyclerView.Adapter<ReviewVH>() {

    interface OnItemClickListener {
        fun onClick(item: Review)
    }

    interface OnFavoriteClickListener{
        fun onClick(item: Review)
    }

    interface OnShareClickListener{
        fun onClick(item: Review)
    }

    private val itens = mutableListOf<Review>()

    fun addItens(reviews: List<Review>) {
        itens.addAll(reviews.filter { it.display_title !in itens.map { it.display_title } })
        notifyDataSetChanged()
    }

    fun setItens(reviews: List<Review>) {
        itens.clear()
        itens.addAll(reviews)
        notifyDataSetChanged()
    }

    override fun getItemCount() = itens.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_review_list, parent, false)
        return ReviewVH(itemView)
    }

    override fun onBindViewHolder(holder: ReviewVH, position: Int) {
        val item = itens[position]

        holder.textView_movie_title.setText(item.display_title)
        holder.textView_movie_description.setText(item.summary_short)
        holder.imageView_image.contentDescription = item.display_title

        if (item.favorite) {
            holder.imageView_favorite.setBackgroundResource(R.drawable.ic_favorite)
        } else {
            holder.imageView_favorite.setBackgroundResource(R.drawable.ic_favorite_no)
        }

        holder.imageView_favorite.setOnClickListener {
            clickFavorite.onClick(item)
            notifyDataSetChanged()
        }
        holder.imageView_share.setOnClickListener {
            clickShare.onClick(item)
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener {
            clickItem.onClick(item)
        }
    }
}

class ReviewVH(itemView: View): RecyclerView.ViewHolder(itemView) {
    val constraintLayout_options = itemView.findViewById<ConstraintLayout>(R.id.constraintLayout_options)
    val imageView_favorite = constraintLayout_options.findViewById<ImageView>(R.id.imageView_favorite)
    val imageView_share = constraintLayout_options.findViewById<ImageView>(R.id.imageView_share)
    val textView_movie_title = itemView.findViewById<TextView>(R.id.textView_movie_title)
    val textView_movie_description = itemView.findViewById<TextView>(R.id.textView_movie_description)
    val imageView_image = itemView.findViewById<ImageView>(R.id.imageView_image)
}