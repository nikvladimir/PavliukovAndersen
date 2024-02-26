package com.example.pavliukovexamples.retrofit

data class ArticleDto(
    val author: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val urlToImage: String,
)
