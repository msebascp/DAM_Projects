package com.manuelsebastian.pr402

class Trailer(
	numRuedas: Int,
	motor: String,
	numAsientos: Int,
	color: String,
	modelo: String,
	val cargaMaxima: Int
) : Vehiculo(numRuedas, motor, numAsientos, color, modelo)