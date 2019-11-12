package Principal;

import java.util.ArrayList;
import java.util.List;

public class Lista {
	private List<Processo> listProcesso;
	private String algoritmo;
	private int pr;
	private int quantum;

	public Lista(String algoritmo, int pr) {
		this.algoritmo = algoritmo;
		this.pr = pr;
		listProcesso = new ArrayList<Processo>();
	}

	public Lista(String algoritmo, int pr, int quantum) {
		this.algoritmo = algoritmo;
		this.quantum = quantum;
		this.pr = pr;
		listProcesso = new ArrayList<Processo>();
	}

	public List<Processo> getListProcesso() {
		return listProcesso;
	}

	public void setListProcesso(List<Processo> listProcesso) {
		this.listProcesso = listProcesso;
	}

	public String getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	public void addProcesso(Processo processo) {
		listProcesso.add(processo);
	}

	public int getPr() {
		return pr;
	}

	public void setPr(int pr) {
		this.pr = pr;
	}

	@Override
	public String toString() {
		return "Lista [listProcesso=" + listProcesso + ", algoritmo=" + algoritmo + ", pr=" + pr + ", quantum="
				+ quantum + "]";
	}
	

	
}
