package com.example.pavliukovandersen.retrofit

data class ArticleDto(
    val author: String,
    val title: String,
    val description: String,
    val source: SourceDto
)
