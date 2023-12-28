package com.manuelsebastian.pr401

class Profesor(notasSize: Int) {
	private var notas: IntArray = IntArray(notasSize)

	fun getNotas(): IntArray {
		return notas
	}

	fun setNota(index: Int, nota: Int) {
		notas[index] = nota
	}

	fun getMaxNota(): String {
		if (notas.isEmpty()) {
			return "No hay notas."
		}
		var max = 0
		var index = 0
		for (i in notas.indices) {
			if (notas[i] > max) {
				max = notas[i]
				index = i
			}
		}
		return "La nota máxima es la nota[$index]: $max"
	}

	/*
	Dado que no podemos eliminar las posiciones de un array, lo que hacemos es
	poner la nota a 0
	*/
	fun deleteNota(index: Int) {
		notas[index] = 0
	}

	fun deleteAllNotas() {
		notas = IntArray(notas.size)
	}

	fun calculateMedia(): String {
		if (notas.size < 3) {
			return "No hay suficientes notas para calcular la media truncada."
		}

		val sortedNotas = notas.sorted()
		val notasTruncadas =
			sortedNotas.subList(1, sortedNotas.size - 1) // Elimina el mínimo y el máximo

		val suma = notasTruncadas.sum()
		val media = suma.toDouble() / notasTruncadas.size
		return "La media truncada es: $media"
	}
}