package com.sebas.pr802.retrofit.models

data class MarvelResponse(
	val attributionHTML: String,
	val attributionText: String,
	val code: Int,
	val copyright: String,
	val data: Data,
	val etag: String,
	val status: String
)