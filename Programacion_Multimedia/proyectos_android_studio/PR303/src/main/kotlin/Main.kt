import kotlin.math.sqrt

fun main() {
	print("Ingrese la longitud del lado A del rectángulo: ")
	val ladoA = readln().toDouble()

	print("Ingrese la longitud del lado B del rectángulo: ")
	val ladoB = readln().toDouble()
	var opcion: Int
	do {
		menu()
		opcion = readln().toInt()
		when (opcion) {
			1 -> calcularPerimetro(ladoA, ladoB)
			2 -> calcularSuperficie(ladoA, ladoB)
			3 -> calcularDiagonal(ladoA, ladoB)
			4 -> calcularAreaTriangulo(ladoA, ladoB)
			5 -> println("Saliendo...")
			else -> println("Opción no válida")
		}
	} while (opcion != 5)
}

fun menu() {
	println("¿Qué desea calcular?")
	println("1. Perímetro")
	println("2. Superficie")
	println("3. Diagonal")
	println("4. Área del triángulo formado por la diagonal")
	println("5. Salir")
	print("Ingrese una opción: ")
}

fun calcularPerimetro(ladoA: Double, ladoB: Double) {
	val perimetro = 2 * (ladoA + ladoB)
	println("El perímetro del rectángulo es: $perimetro")
}

fun calcularSuperficie(ladoA: Double, ladoB: Double) {
	val superficie = ladoA * ladoB
	println("La superficie del rectángulo es: $superficie")
}

fun calcularDiagonal(ladoA: Double, ladoB: Double) {
	val diagonal = sqrt((ladoA * ladoA) + (ladoB * ladoB))
	println("La diagonal del rectángulo es: $diagonal")
}

fun calcularAreaTriangulo(ladoA: Double, ladoB: Double) {
	val areaTriangulo = 0.5 * ladoA * ladoB
	println("El área del triángulo formado por la diagonal es: $areaTriangulo")
}
