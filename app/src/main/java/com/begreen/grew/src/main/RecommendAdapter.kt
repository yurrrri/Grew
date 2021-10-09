package com.begreen.grew.src.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.begreen.grew.R
import com.begreen.grew.src.main.model.First
import com.begreen.grew.src.main.view.MainActivity
import com.begreen.grew.src.main.view.WebViewFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class RecommendAdapter(val context: Context, private val recommendList: List<First>):
    RecyclerView.Adapter<RecommendAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgFeed = itemView.findViewById<ImageView>(R.id.imgNews)
        private val tvFeedTitle = itemView.findViewById<TextView>(R.id.tvNewsTitle)
        private val tvNewsCompany = itemView.findViewById<TextView>(R.id.tvNewsCompany)
        private val tvNewsDate = itemView.findViewById<TextView>(R.id.tvNewsDate)
        private val btnBookMark = itemView.findViewById<Button>(R.id.btnBookMark)

        fun bind(recommend: First, context: Context) {
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

            if (recommend.is_scrap==0){
                btnBookMark.setBackgroundResource(R.drawable.ic_empty_bookmark)
            }
            else{
                btnBookMark.setBackgroundResource(R.drawable.ic_bookmark_full)
            }

            btnBookMark.setOnClickListener {
                if (recommend.is_scrap==0){
                    btnBookMark.setBackgroundResource(R.drawable.ic_bookmark_full)
                }
                else{
                    btnBookMark.setBackgroundResource(R.drawable.ic_empty_bookmark)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_recommend, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(recommendList[position], context)
        holder.itemView.setOnClickListener {
            val webView = WebViewFragment()
            val bundle = Bundle()
            bundle.putString("url", recommendList[position].news_url)
            webView.arguments = bundle
            switchContent(webView)
        }

        holder.itemView.setOnLongClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, recommendList[position].news_url);
            val chooser = Intent.createChooser(intent, "공유하기")
            ContextCompat.startActivity(context, chooser, null)

            false
        }
    }

    override fun getItemCount(): Int {
        return recommendList.size
    }

    private fun switchContent(fragment: Fragment) {
        if (context is MainActivity) {
            val mainActivity = context
            val frag: Fragment = fragment
            mainActivity.changeFragment(frag)
        }
    }
}