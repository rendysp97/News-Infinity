package com.example.news

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class Endless(val layout:LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var isLoading = false;
    private val visibleThreshold = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = layout.itemCount
        val lastVisibleItem = layout.findLastVisibleItemPosition()

        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            isLoading = true
            loadData()

        }

    }

    fun setLoading(isLoading: Boolean) {
       this.isLoading = isLoading
    }


    abstract fun loadData()
}