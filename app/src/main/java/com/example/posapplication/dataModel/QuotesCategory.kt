package com.example.posapplication.dataModel
data class QuotesCategory(val status: Status, val data: QuoteList)

data class Status(val message: String, val code: String, val code_str: String, val extra_data: String)

data class QuoteList(val Lastupdated: String, val quotes: List<Quote>)

data class Quote(
    val CategoryName: String,
    val categoryId: String,
    val categoryImage: String,
    val thumbnail_folder: String,
    val largeimg_folder: String
)