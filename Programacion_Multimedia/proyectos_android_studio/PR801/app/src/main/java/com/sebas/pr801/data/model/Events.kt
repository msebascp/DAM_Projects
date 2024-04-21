package com.sebas.pr801.data.model

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)