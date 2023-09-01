package com.example.news

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.news.adaptor.AllAdaptor
import com.example.news.adaptor.TrendAdaptor
import com.example.news.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
   private lateinit var adaptor: TrendAdaptor
   private lateinit var adaptorAll: AllAdaptor
   private lateinit var viewModel: MyViewModel
   private lateinit var trendEndless: Endless
   private lateinit var allEndless: Endless

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        Glide.with(binding.logo.context).load(R.drawable.ic_launcher_background).circleCrop().into(binding.logo)

        initRecycle()

    }


     private fun initRecycle() {

       val trendLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
         binding.listTrend.layoutManager = trendLayoutManager
         adaptor = TrendAdaptor()
         binding.listTrend.adapter = adaptor

      viewModel.headlines.observe(this@MainActivity) {
          adaptor.setData(it)
                        }

         trendEndless = object  : Endless(trendLayoutManager) {
             override fun loadData() { viewModel.getHeadline()
                                trendEndless.setLoading(false)
                            }
                        }

         binding.listTrend.addOnScrollListener(trendEndless)


         val allLayout = LinearLayoutManager(this)
         binding.all.layoutManager = allLayout
         adaptorAll = AllAdaptor()
         binding.all.adapter = adaptorAll

         viewModel.allData.observe(this@MainActivity) {
             adaptorAll.setList(it)

                        }

         allEndless = object : Endless(allLayout){
             override fun loadData() {
                 viewModel.getAll()
                 allEndless.setLoading(false)
                            }
                        }

         binding.all.addOnScrollListener(allEndless)


         viewModel.getAll()
         viewModel.getHeadline()
                    }
}

