package com.sebas.pr801.data

import com.sebas.pr801.data.model.MarvelResponse
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
	@GET("characters?ts=1&apikey=c494e0c1ba4d06b41698ab95d213abf8&hash=86d3fca3575ee024e931cb66bd5cf199")
	suspend fun getHeroes(): MarvelResponse
}

object ApiServiceFactory {
	fun makeApiService(): ApiService {
		return Builder()
			.baseUrl("http://gateway.marvel.com/v1/public/")
			.addConverterFactory(
				GsonConverterFactory.create()
			)
			.build().create(ApiService::class.java)
	}
}