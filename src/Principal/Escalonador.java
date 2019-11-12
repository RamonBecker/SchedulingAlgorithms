package Principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Escalonador implements Comparator<Processo> {
	private List<Lista> filas;
	private List<Processo> filaFinalizado;
	private List<Processo> filaEspera;
	private int tempoEscalonador;
	private GraficoGantt graficoGantt = GraficoGantt.getInstance();
	private float avgTAT = 0;
	private float avgWT = 0;

	public Escalonador() {
		filas = new ArrayList<Lista>();
		filaFinalizado = new ArrayList<Processo>();
		filaEspera = new ArrayList<Processo>();
		this.tempoEscalonador = 0;
	}

	public void addFila(Lista lista) {

		filas.add(lista);
	}

	public void addProcesso(Processo processo) {
		filaEspera.add(processo);
		Collections.sort(filaEspera);
	}

	public List<Processo> getListProcessoEspera() {
		return filaEspera;
	}

	public void escalonar() {

		int sizeListEspera = filaEspera.size();

		int contListEmpty = 0;

		while (true) {

			insereLista();

			for (Lista lista : filas) {

				if (lista.getAlgoritmo().equals("RR")) {
					algoritmoRR(lista);
				}

				if (lista.getAlgoritmo().equals("FCFS")) {
					algoritmoFCFS(lista);
				}

				if (lista.getAlgoritmo().equals("SJF")) {
					algoritmoSJF(lista);
				}

				if (lista.getListProcesso().isEmpty()) {
					contListEmpty++;
				}
			}

			if (filaFinalizado.size() == sizeListEspera) {
				break;
			}

			if (contListEmpty == filas.size()) {
				int tempo = tempoEscalonador;
				tempoEscalonador++;
				graficoGantt.addIDE(tempo, tempoEscalonador);
				contListEmpty = 0;
			}
		}

		calcAVG();
	}

	private void algoritmoRR(Lista lista) {

		while (!lista.getListProcesso().isEmpty()) {

			if (checkPRLista(lista)) {
				return;
			}

			Processo auxProcessoAtual = null;
			Processo auxP1 = null;
			Processo auxP2 = null;

			Processo processo1 = lista.getListProcesso().get(0);

			for (Processo processo2 : lista.getListProcesso()) {
				if (processo1.getAt() == processo2.getAt()) {
					if (!(processo1.getId() == processo2.getId())) {
						if (processo1.getId() < processo2.getId()) {
							auxP1 = processo1;
						} else {
							auxP2 = processo2;
						}

					}

				}
				auxP1 = processo1;
			}

			if (auxP2 == null) {
				auxProcessoAtual = auxP1;
			} else if (auxP2.getEt() != 0) {
				auxProcessoAtual = auxP1;
			}

			else if (auxP1.getAt() == auxP2.getAt()) {
				if (auxP1.getId() < auxP2.getId()) {
					auxProcessoAtual = auxP1;
				} else {
					auxProcessoAtual = auxP2;
				}

			}

			auxProcessoAtual.setQat(tempoEscalonador);

			for (int i = 0; i < lista.getQuantum(); i++) {
				auxProcessoAtual.setEt();
				tempoEscalonador++;
				auxProcessoAtual.setCt(tempoEscalonador);
				insereLista();
				if (auxProcessoAtual.completeBT()) {
					break;
				}

			}

			graficoGantt.add(auxProcessoAtual.getId(), auxProcessoAtual.getQat(), tempoEscalonador);

			if (auxProcessoAtual.completeBT()) {
				auxProcessoAtual.setTat(auxProcessoAtual.getCt() - auxProcessoAtual.getAt());
				auxProcessoAtual.setWt();
				filaFinalizado.add(auxProcessoAtual);
				lista.getListProcesso().remove(auxProcessoAtual);
			} else {
				lista.getListProcesso().add(auxProcessoAtual);
				lista.getListProcesso().remove(auxProcessoAtual);
			}

		}

	}

	private void algoritmoSJF(Lista lista) {

		while (!lista.getListProcesso().isEmpty()) {

			if (checkPRLista(lista)) {
				return;
			}

			Collections.sort(lista.getListProcesso(), new BTComparator());

			Processo auxProcessoAtual = null;
			Processo auxP1 = null;
			Processo auxP2 = null;

			for (Processo processo1 : lista.getListProcesso()) {
				for (Processo processo2 : lista.getListProcesso()) {
					if (processo1.getBt() == processo2.getBt()) {
						if (processo1.getAt() == processo2.getAt()) {
							if (processo1.getId() < processo2.getId()) {
								auxP1 = processo1;
							} else {
								auxP2 = processo2;
							}
						}
					}

					auxP1 = processo1;
				}

				if (auxP2 == null) {
					auxProcessoAtual = auxP1;
				}

				if (auxP1.getAt() == auxP2.getAt()) {
					if (auxP1.getAt() < auxP2.getAt()) {
						auxProcessoAtual = auxP1;
					} else {
						auxProcessoAtual = auxP2;
					}
				}

				else if (auxP1.getId() < auxP2.getId()) {
					auxProcessoAtual = auxP1;
				} else {
					auxProcessoAtual = auxP2;
				}

				auxProcessoAtual.setQat(tempoEscalonador);

				for (int i = 0; i < auxProcessoAtual.getBt(); i++) {
					tempoEscalonador++;
					auxProcessoAtual.setCt(tempoEscalonador);
					auxProcessoAtual.setEt();
					insereLista();
				}

				if (auxProcessoAtual.completeBT()) {
					break;
				}
			}

			graficoGantt.add(auxProcessoAtual.getId(), auxProcessoAtual.getQat(), tempoEscalonador);

			if (auxProcessoAtual.completeBT()) {
				auxProcessoAtual.setTat(auxProcessoAtual.getCt() - auxProcessoAtual.getAt());
				auxProcessoAtual.setWt();
				filaFinalizado.add(auxProcessoAtual);
				lista.getListProcesso().remove(auxProcessoAtual);
			}
		}
	}

	private void algoritmoFCFS(Lista lista) {
		while (!lista.getListProcesso().isEmpty()) {

			if (checkPRLista(lista)) {
				return;
			}

			Processo auxProcessoAtual = null;
			Processo auxP1 = null;
			Processo auxP2 = null;

			for (Processo processo1 : lista.getListProcesso()) {
				for (Processo processo2 : lista.getListProcesso()) {
					if (processo1.getAt() == processo2.getAt()) {
						if (processo1.getId() < processo2.getId()) {
							auxP1 = processo1;
						} else {
							auxP2 = processo2;
						}
					}
					auxP1 = processo1;
				}
				if (auxP2 == null) {
					auxProcessoAtual = auxP1;
				}

				if (auxP1.getAt() == auxP2.getAt()) {
					if (auxP1.getAt() < auxP2.getAt()) {
						auxProcessoAtual = auxP1;
					} else {
						auxProcessoAtual = auxP2;
					}
				}

				else if (auxP1.getId() < auxP2.getId()) {
					auxProcessoAtual = auxP1;
				} else {
					auxProcessoAtual = auxP2;
				}

				auxProcessoAtual.setQat(tempoEscalonador);

				for (int i = 0; i < auxProcessoAtual.getBt(); i++) {
					auxProcessoAtual.setEt();
					tempoEscalonador++;
					auxProcessoAtual.setCt(tempoEscalonador);
					insereLista();
				}

				if (auxProcessoAtual.completeBT()) {
					break;

				}
			}

			graficoGantt.add(auxProcessoAtual.getId(), auxProcessoAtual.getQat(), tempoEscalonador);

			if (auxProcessoAtual.completeBT()) {
				auxProcessoAtual.setTat(auxProcessoAtual.getCt() - auxProcessoAtual.getAt());
				auxProcessoAtual.setWt();
				filaFinalizado.add(auxProcessoAtual);
				lista.getListProcesso().remove(auxProcessoAtual);

			}

		}
	}

	public boolean checkPRLista(Lista lista2) {
		for (Lista lista1 : filas) {
			if (lista1.getPr() < lista2.getPr()) {
				if (lista1.getListProcesso().size() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	public void insereLista() {

		Iterator<Processo> iteratorListEspera = null;
		iteratorListEspera = filaEspera.iterator();

		while (iteratorListEspera.hasNext()) {
			Processo processo = iteratorListEspera.next();
			for (Lista lista : filas) {
				if (lista.getPr() == processo.getPr()) {
					if (processo.getAt() <= tempoEscalonador) {
						lista.addProcesso(processo);
						iteratorListEspera.remove();
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Escalonador [list=" + filas + "\n\n";
	}

	public List<Lista> getList() {
		return filas;
	}

	public List<Processo> getListProcessoFinalizado() {
		this.filaFinalizado.sort(new IDComparator());
		return filaFinalizado;
	}

	public float getAvgTAT() {
		return avgTAT;
	}

	public float getAvgWT() {
		return avgWT;
	}

	@Override
	public int compare(Processo o1, Processo o2) {

		return o1.getId().compareTo(o2.getId());
	}

	private void calcAVG() {

		for (Processo processo : filaFinalizado) {
			avgTAT += processo.getTat();
			avgWT += processo.getWt();
		}

		avgTAT = avgTAT / filaFinalizado.size();
		avgWT = avgWT / filaFinalizado.size();
	}
}
