package com.example.news.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.ListItemAllBinding
import com.example.news.model.all.ArticleAll
import java.text.SimpleDateFormat
import java.util.Locale

class AllAdaptor : RecyclerView.Adapter<MyHolder>(){

    val listAll = ArrayList<ArticleAll>()

    fun setList(article: List<ArticleAll>) {
        listAll.addAll(article)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataInflater:ListItemAllBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_item_all,
            parent,
            false
        )
        return MyHolder(dataInflater)
    }

    override fun getItemCount(): Int {
        return  listAll.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(listAll[position])
    }
}

class MyHolder(val binding:ListItemAllBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: ArticleAll) {

        val apiFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val desiredFormat = "dd MMMM yyyy"
        val formattedPublishedAt = convertApiTimeToDesiredFormat(article.publishedAt, apiFormat, desiredFormat)

        binding.apply {

            titleAll.text = article.title
            authorAll.text = article.author
            calender.text = formattedPublishedAt

            Glide.with(imgAll.context).load(article.urlToImage).error(R.drawable.image).placeholder(R.drawable.loading_icon).into(imgAll)
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
