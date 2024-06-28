package com.example.posapplication

import com.example.posapplication.dataModel.CategoryDataClass
import com.example.posapplication.dataModel.QuotesCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("quotes/quotes_master.json")
    fun getQuotes(): Call<QuotesCategory>

    @GET("quotes/{categoryId}.json")
    fun getCategoryQuotes(
        @Path("categoryId") categoryId: String?
    ): Call<CategoryDataClass>
}
