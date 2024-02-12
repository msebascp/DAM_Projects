package com.sebas.pr501.models

class CalculadoraHipotecaModel(
	val capital: Double,
	val plazo: Int,
	val tasaInteres: Double = 0.01605
) {
	fun calcularCuota(): Double {
		return capital * tasaInteres / 12 / (1 - Math.pow(
			1 + (tasaInteres / 12),
			-plazo.toDouble() * 12
		));
	}
}