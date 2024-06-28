package com.example.posapplication.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.posapplication.dataModel.CategoryDataClass
import com.example.posapplication.dataModel.Quote
import com.example.posapplication.databinding.EveryCategoryItemBinding
import java.io.File

class EveryCategoryAdapter(private val quotes: List<CategoryDataClass.Data.Item?>?,
                           private val categoryName : String? = null,
                           var onItemClicked: (List<CategoryDataClass.Data.Item?>?, Int) -> Unit )
    : RecyclerView.Adapter<EveryCategoryAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(val binding: EveryCategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = EveryCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes?.get(position)
        Glide
            .with(holder.itemView.context)
            .load("https://d35zfoayiky5yq.cloudfront.net/img/quotes/$categoryName/"+quote?.org_img)
            .into(holder.binding.mainImage)
        holder.binding.mainImage.setOnClickListener {
            onItemClicked(quotes,position)
        }

    }


    override fun getItemCount(): Int {
        return quotes?.size ?:0
    }
}