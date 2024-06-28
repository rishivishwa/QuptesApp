package com.example.posapplication.dataModel

data class CategoryDataClass(
    val status: Status?,
    val `data`: Data?
) {
    data class Status(
        val message: String?,
        val code: String?,
        val code_str: String?,
        val extra_data: String?
    )

    data class Data(
        val Lastupdated: String?,
        val items: List<Item?>?
    ) {
        data class Item(
            val quotes: String?,
            val `by`: String?,
            val thumbnail: String?,
            val org_img: String?,
            val isPremimum: String?
        )
    }
}