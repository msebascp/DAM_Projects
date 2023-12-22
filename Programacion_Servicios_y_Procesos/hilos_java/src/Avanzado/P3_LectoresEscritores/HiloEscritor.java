package Avanzado.P3_LectoresEscritores;

public class HiloEscritor extends Thread {
	private EditorTexto editorTexto;

	public HiloEscritor(EditorTexto editorTexto) {
		this.editorTexto = editorTexto;
	}

	@Override
	public void run() {
		editorTexto.escribir();
	}
}
