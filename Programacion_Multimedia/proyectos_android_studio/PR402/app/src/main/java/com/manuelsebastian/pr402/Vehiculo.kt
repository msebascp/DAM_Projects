package com.manuelsebastian.pr402

open class Vehiculo(
	val numRuedas: Int,
	val motor: String,
	val numAsientos: Int,
	val color: String,
	val modelo: String
) {
	override fun toString(): String {
		return "Vehiculo(ruedas=$numRuedas, motor='$motor', asientos=$numAsientos, color='$color', modelo='$modelo')"
	}
}