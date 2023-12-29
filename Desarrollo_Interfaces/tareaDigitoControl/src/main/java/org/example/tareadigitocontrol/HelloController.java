package org.example.tareadigitocontrol;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

	@FXML
	private TextField ochoDigitosField;

	@FXML
	private TextField digitosControlField;

	@FXML
	private TextField diezDigitosField;

	@FXML
	protected void onVerificarButtonOnClick() {
		boolean correctFormat = validarCuentaBancaria();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		if (correctFormat) {
			alert.setTitle("Información");
			alert.setHeaderText(null);
			alert.setContentText("Cuenta bancaria correcta");
		} else {
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Cuenta bancaria incorrecta");
			alert.setAlertType(Alert.AlertType.ERROR);
		}

		alert.showAndWait();
	}

	@FXML
	protected void onCancelarButtonOnClick() {
	}

	@FXML
	public boolean validarCuentaBancaria() {
		if (soloDigitos(ochoDigitosField.getText() + digitosControlField.getText() + diezDigitosField.getText())) {
			int[] primeros8digitos = convertirCadenaANumeros(ochoDigitosField.getText());
			int[] digitosControl = convertirCadenaANumeros(digitosControlField.getText());
			int[] ultimos10digitos = convertirCadenaANumeros(diezDigitosField.getText());
			int[] factores8 = {4, 8, 5, 10, 9, 7, 3, 6};
			int[] factores10 = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
			if (primeros8digitos.length != 8 || digitosControl.length != 2 || ultimos10digitos.length != 10) {
				return false;
			}
			int suma = 0;
			for (int i = 0; i < 8; i++) {
				suma += primeros8digitos[i] * factores8[i];
			}
			int resto = suma % 11;
			int digitoControl = 11 - resto;
			if (digitoControl != digitosControl[0]) {
				return false;
			}
			suma = 0;
			for (int i = 0; i < 10; i++) {
				suma += ultimos10digitos[i] * factores10[i];
			}
			resto = suma % 11;
			digitoControl = 11 - resto;
			if (digitoControl != digitosControl[1]) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	public static int[] convertirCadenaANumeros(String cadena) {
		int[] arrayNumeros = new int[cadena.length()];

		// Iterar sobre cada carácter de la cadena y convertirlo a un número
		for (int i = 0; i < cadena.length(); i++) {
			char caracter = cadena.charAt(i);
			arrayNumeros[i] = Character.getNumericValue(caracter);
		}

		return arrayNumeros;
	}

	public static boolean soloDigitos(String str) {
		// Verifica cada caracter en la cadena para asegurarse de que sea un dígito
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false; // Si encuentra un no dígito, la cadena no es un número
			}
		}
		return true; // Si todos los caracteres son dígitos, la cadena es un número
	}
}