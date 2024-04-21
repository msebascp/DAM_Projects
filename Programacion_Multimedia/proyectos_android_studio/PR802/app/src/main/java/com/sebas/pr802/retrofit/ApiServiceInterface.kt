package com.sebas.pr802.retrofit

import com.sebas.pr802.retrofit.models.MarvelResponse
import retrofit2.http.GET

interface ApiServiceInterface {
	@GET("characters?ts=1&apikey=c494e0c1ba4d06b41698ab95d213abf8&hash=86d3fca3575ee024e931cb66bd5cf199")
	suspend fun getHeroes(): MarvelResponse
}