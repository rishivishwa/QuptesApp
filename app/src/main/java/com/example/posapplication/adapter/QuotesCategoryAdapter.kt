package com.example.posapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.posapplication.R
import com.example.posapplication.dataModel.Quote
import com.example.posapplication.databinding.ItemQuotesBinding

class QuotesCategoryAdapter(private val quotes: List<Quote>, private var onCategoryClicked: ((List<Quote>, Int) -> Unit)? = null ) : RecyclerView
    .Adapter<QuotesCategoryAdapter
    .QuoteViewHolder>() {

    private var selectedPosition = 0

    class QuoteViewHolder(val binding: ItemQuotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemQuotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.binding.categoryQuotesName.text = quote.CategoryName

        if (position == selectedPosition) {
            holder.binding.categoryQuotesName.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.selected_position))
        } else {
            holder.binding.categoryQuotesName.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.transparent))
        }

        holder.binding.categoryQuotesName.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            onCategoryClicked?.invoke(quotes, position)
        }
    }

    override fun getItemCount(): Int {
        return quotes.size
    }
}