package com.begreen.grew.src.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.begreen.grew.R
import com.begreen.grew.src.main.model.First
import com.begreen.grew.src.main.model.Second
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

class AgeNewsAdapter(val context: Context, private val recommendList: List<Second>):
    RecyclerView.Adapter<AgeNewsAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgFeed = itemView.findViewById<ImageView>(R.id.imgAgeNews)
        private val tvFeedTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvNewsCompany = itemView.findViewById<TextView>(R.id.tvCompany)
        private val tvNewsDate = itemView.findViewById<TextView>(R.id.tvDate)

        fun bind(recommend: Second, context: Context) {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(8))

            Glide
                .with(context)
                .load(recommend.img_url)
                .apply(requestOptions)
                .into(imgFeed) //멤버 프로필

            tvFeedTitle.text = recommend.title
            tvNewsCompany.text = recommend.provider
            tvNewsDate.text = recommend.published_at
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_news_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(recommendList[position], context)
        holder.itemView.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(recommendList[position].news_url))
            startActivity(context, browserIntent, null)
        }

        holder.itemView.setOnLongClickListener {
             val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, recommendList[position].news_url);
            val chooser = Intent.createChooser(intent, "친구에게 공유하기")
            startActivity(context, chooser, null)

            false
        }
    }

    override fun getItemCount(): Int {
        return recommendList.size
    }
}