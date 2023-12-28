package com.example.jetpackcomposedemo.model

data class ItemModel(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageId: Int,
    val source: String = "source code"
)