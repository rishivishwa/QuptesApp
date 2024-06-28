package com.example.posapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posapplication.adapter.EveryCategoryAdapter
import com.example.posapplication.adapter.ImagePagerAdapter
import com.example.posapplication.adapter.QuotesCategoryAdapter
import com.example.posapplication.dataModel.CategoryDataClass
import com.example.posapplication.dataModel.Quote
import com.example.posapplication.databinding.ActivityMainBinding
import com.example.posapplication.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    private var selectedCategoryId : String? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var imagePagerAdapter: ImagePagerAdapter

    private val viewModel: QuoteViewModel by viewModels()
    private var quotesCategoryAdapter: QuotesCategoryAdapter? = null
    private var everyCategoryAdapter: EveryCategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        quotesCategoryAdapter = QuotesCategoryAdapter(arrayListOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        viewModel.quotes.observe(this){quotes ->
            quotesCategoryAdapter = QuotesCategoryAdapter(quotes,::onCategoryClicked)
            binding.recyclerView.adapter = quotesCategoryAdapter
            if (quotes.isNotEmpty()) {
                onCategoryClicked(quotes, 0)
            }
        }

        viewModel.categoryQuotes.observe(this){ image ->
            binding.recyclerView1.layoutManager = GridLayoutManager(this, 2)
            everyCategoryAdapter = EveryCategoryAdapter(image.items,selectedCategoryId,::onItemClick)
            binding.recyclerView1.adapter = everyCategoryAdapter
        }

        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) binding.progressBar.visibility = View.VISIBLE else binding.progressBar.visibility = View.GONE
        }

    }
    private fun onItemClick(image : List<CategoryDataClass.Data.Item?>?, position :  Int){

    }
    private fun onCategoryClicked(quotes : List<Quote>,position :  Int){
        selectedCategoryId = quotes[position].categoryId
        viewModel.fetchQuotesByCategory(selectedCategoryId)
    }

}