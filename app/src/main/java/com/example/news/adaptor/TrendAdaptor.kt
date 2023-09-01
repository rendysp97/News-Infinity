package com.example.news.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.ListItemTrendBinding
import com.example.news.model.trend.Article
import java.text.SimpleDateFormat
import java.util.Locale


class TrendAdaptor: RecyclerView.Adapter<MyViewHolder>() {

    val listData = ArrayList<Article>()


    fun setData(article:List<Article>) {

        listData.addAll(article)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val data:ListItemTrendBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_item_trend,
            parent,
            false
        )
        return MyViewHolder(data)
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData[position]);
    }
}


class MyViewHolder(val binding: ListItemTrendBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {

        val apiFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val desiredFormat = "dd MMMM yyyy"
        val formattedPublishedAt = convertApiTimeToDesiredFormat(article.publishedAt, apiFormat, desiredFormat)


        binding.apply {
            desc.text = article.title
            author.text = article.source.name
            date.text = formattedPublishedAt

            Glide.with(img.context).load(article.urlToImage).into(img)
        }


    }

    private fun convertApiTimeToDesiredFormat(apiTime: String, apiFormat: String, desiredFormat: String): String {
        val data = SimpleDateFormat(apiFormat, Locale.getDefault())
        val sdfDesired = SimpleDateFormat(desiredFormat, Locale.getDefault())

        try {
            val date = data.parse(apiTime)
            return sdfDesired.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }
}


