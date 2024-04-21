package com.sebas.pr802.retrofit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.pr802.retrofit.models.Data
import com.sebas.pr802.retrofit.models.MarvelResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
	private val apiService = RetrofitInstance.api
	public val marvelGetResponse: MutableState<MarvelResponse> = mutableStateOf(
		MarvelResponse(
			attributionHTML = "",
			attributionText = "",
			code = 0,
			copyright = "",
			data = Data(
				count = 0,
				limit = 0,
				offset = 0,
				results = listOf(),
				total = 0
			),
			etag = "",
			status = ""
		)
	)

	fun getHeroes() {
		viewModelScope.launch {
			try {
				val response = apiService.getHeroes()
				if (response.status == "Ok") {
					marvelGetResponse.value = response
				}
			} catch (e: Exception) {
				// Handle errors here
			}
		}
	}
}