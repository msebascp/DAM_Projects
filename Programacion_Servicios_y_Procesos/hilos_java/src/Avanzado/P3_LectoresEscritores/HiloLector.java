package Avanzado.P3_LectoresEscritores;

public class HiloLector extends Thread {
	private EditorTexto editorTexto;

	public HiloLector(EditorTexto editorTexto) {
		this.editorTexto = editorTexto;
	}

	@Override
	public void run() {
		editorTexto.leer();
	}
}
