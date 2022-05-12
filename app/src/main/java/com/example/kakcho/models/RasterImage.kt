package com.example.kakcho.models

data class RasterImage(
    val formats: List<Format>,
    val size: Int,
    val size_height: Int,
    val size_width: Int
)