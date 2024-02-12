package com.sebas.pr501.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sebas.pr501.models.CalculadoraHipotecaModel
import kotlinx.coroutines.delay

class CalculadoraHipotecaViewModel : ViewModel() {

	private val _capital = MutableLiveData<String>()
	val capital: MutableLiveData<String> = _capital

	private val _plazo = MutableLiveData<String>()
	val plazo: MutableLiveData<String> = _plazo

	private val _cuota = MutableLiveData<Double>()
	val cuota: MutableLiveData<Double> = _cuota

	private val _calculateEnable = MutableLiveData<Boolean>()
	val calculateEnable: MutableLiveData<Boolean> = _calculateEnable

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: MutableLiveData<Boolean> = _isLoading

	private val _showCuota = MutableLiveData<Boolean>()
	val showCuota: MutableLiveData<Boolean> = _showCuota

	fun onTextFieldsChange(capital: String, plazo: String) {
		_capital.value = capital
		_plazo.value = plazo
		_calculateEnable.value = isValidPositiveNumber(capital) && isValidPositiveNumber(plazo)
	}

	private fun isValidPositiveNumber(number: String): Boolean {
		return number.isNotEmpty() &&
				number.matches(Regex("^\\d*\\.?\\d+\$")) &&
				number.toDouble() > 0
	}

	suspend fun onCalculateClick() {
		_showCuota.value = false
		_cuota.value = calcularHipoteca()
		_isLoading.value = true
		delay(3000)
		_isLoading.value = false
		_showCuota.value = true
	}

	fun calcularHipoteca(): Double {
		val calculadoraHipotecaModel = CalculadoraHipotecaModel(
			capital = _capital.value?.toDouble() ?: 0.0,
			plazo = _plazo.value?.toInt() ?: 0,
		)
		return calculadoraHipotecaModel.calcularCuota()
	}
}