package com.cheney.gankkotlin.bean

data class Gank(
    val _id: String,
    val author: String,
    val category: String,
    val content: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>?,
    val index: Int,
    val isOriginal: Boolean,
    val license: String,
    val likeCount: Int,
    val likeCounts: Int,
    val likes: List<String>,
    val markdown: String,
    val originalAuthor: String,
    val publishedAt: String?,
    val stars: Int,
    val status: Int,
    val tags: List<String>,
    val title: String,
    val type: String,
    val updatedAt: String,
    val url: String,
    val views: Int
) {
    fun getImageUrl(index: Int): String? {
        return images?.get(index)
    }

    fun publishedAt(): String? {
        return publishedAt?.substring(0, 10)
    }
}