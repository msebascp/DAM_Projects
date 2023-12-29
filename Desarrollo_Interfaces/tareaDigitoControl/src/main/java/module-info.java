module org.example.tareadigitocontrol {
	requires javafx.controls;
	requires javafx.fxml;


	opens org.example.tareadigitocontrol to javafx.fxml;
	exports org.example.tareadigitocontrol;
}