package com.example.cryptoanalysis.data.model

data class ResponseNews(val numResults: Int = 0,
                        val articles: List<ArticlesItem>?,
                        val status: Int = 0)