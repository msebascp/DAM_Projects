package com.sebas.pr802.retrofit.models

data class Data(
	val count: Int,
	val limit: Int,
	val offset: Int,
	val results: List<Result>,
	val total: Int
)