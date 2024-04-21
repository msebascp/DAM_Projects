package com.sebas.pr802.retrofit.models

data class Comics(
	val available: Int,
	val collectionURI: String,
	val items: List<Item>,
	val returned: Int
)