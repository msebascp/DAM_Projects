package com.sebas.pr802.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
	private
	const val BASE_URL = "http://gateway.marvel.com/v1/public/"
	val api: ApiServiceInterface by lazy {
		val retrofit = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		retrofit.create(ApiServiceInterface::class.java)
	}
}