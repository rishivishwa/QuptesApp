package com.example.posapplication.viewModel// QuoteViewModel.kt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.posapplication.RetrofitInstance
import com.example.posapplication.dataModel.CategoryDataClass
import com.example.posapplication.dataModel.Quote
import com.example.posapplication.dataModel.QuotesCategory
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class QuoteViewModel : ViewModel() {
    val quotes = MutableLiveData<List<Quote>>()
    val loading = MutableLiveData<Boolean>()
    val categoryQuotes = MutableLiveData<CategoryDataClass.Data>()

    init{
        fetchQuotes()
    }

    private fun fetchQuotes() {
        loading.value = true
        RetrofitInstance.api.getQuotes().enqueue(object : Callback<QuotesCategory> {
            override fun onResponse(call: Call<QuotesCategory>, response: Response<QuotesCategory>) {
                if (response.isSuccessful) {
                    quotes.value = response.body()?.data?.quotes
                }
                loading.value = false
            }

            override fun onFailure(call: Call<QuotesCategory>, t: Throwable) {
                loading.value = false
            }
        })
    }

    private fun categoryQuotes(categoryId : String?) {
        loading.value = true
        RetrofitInstance.api.getCategoryQuotes(categoryId).enqueue(object : Callback<CategoryDataClass> {
            override fun onResponse(call: Call<CategoryDataClass>, response: Response<CategoryDataClass>) {
                if (response.isSuccessful) {
                    categoryQuotes.postValue(response.body()?.data)
                }
                loading.value = false
            }

            override fun onFailure(call: Call<CategoryDataClass>, t: Throwable) {
                loading.value = false
            }
        })
    }

    fun fetchQuotesByCategory(categoryId: String?) {
        categoryQuotes(categoryId)
    }
}