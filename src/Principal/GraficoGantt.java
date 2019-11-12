package Principal;

import java.util.ArrayList;
import java.util.List;

public class GraficoGantt {
	private List<String> listProcesso;
	private static GraficoGantt graficoGantt;

	private GraficoGantt() {
		listProcesso = new ArrayList<String>();
	}

	public static GraficoGantt getInstance() {
		if (graficoGantt == null) {
			graficoGantt = new GraficoGantt();
		}
		return graficoGantt;
	}

	public void add(int id, int qat, int tat) {

		String processo = "P" + String.valueOf(id) + "(" + String.valueOf(qat) + "-" + String.valueOf(tat) + ") "
				+ " | ";
		listProcesso.add(processo);
	}

	public List<String> getListProcesso() {
		return listProcesso;
	}
	
	
	public void addIDE(int qta, int tat) {
		String ide = "ide "+"("+String.valueOf(qta)+"-"+String.valueOf(tat)+") |";
		listProcesso.add(ide);
	}
}
