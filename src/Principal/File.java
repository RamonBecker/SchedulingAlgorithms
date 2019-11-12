package Principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class File {

	public static void main(String[] args) {

		String nome = "C:/Users/Cliente/Downloads/in-all-1.txt";

		Escalonador escalonador = new Escalonador();

		String[] textoSeparado = null;

		int qtdProcessos = 0, qtdFilas = 0;

		try {

			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";

			linha = lerArq.readLine();

			textoSeparado = linha.split(" ");
			qtdFilas = Integer.parseInt(textoSeparado[0]);
			qtdProcessos = Integer.parseInt(textoSeparado[1]);

			for (int i = 0; i < qtdFilas; i++) {
				linha = lerArq.readLine();

				if (linha.contains(" ")) {
					textoSeparado = linha.split(" ");
					escalonador.addFila(new Lista(textoSeparado[0], i, Integer.parseInt(textoSeparado[1])));

				} else {
					textoSeparado = linha.split(" ");
					escalonador.addFila(new Lista(textoSeparado[0], i));
				}
			}

			for (int i = 0; i < qtdProcessos; i++) {
				linha = lerArq.readLine();
				textoSeparado = linha.split(" ");

				escalonador.addProcesso(
						new Processo(Integer.parseInt(textoSeparado[0]), Integer.parseInt(textoSeparado[1]),
								Integer.parseInt(textoSeparado[2]), Integer.parseInt(textoSeparado[3])));
			}
			arq.close();
			lerArq.close();

		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo\n");

		}

		System.out.println("\n");

		escalonador.escalonar();

		GraficoGantt graficoGantt = GraficoGantt.getInstance();

		String textoGraficoGantt = "";
		String textoTabelaProcessos = "";
		String textoAvgWT = "";
		String textoAvgTAT = "";

		for (String processo : graficoGantt.getListProcesso()) {
			textoGraficoGantt += processo;
		}

		for (Processo processo : escalonador.getListProcessoFinalizado()) {
			textoTabelaProcessos += processo.toString() + "\n";
		}

		textoAvgTAT = String.valueOf(escalonador.getAvgTAT());
		textoAvgWT = String.valueOf(escalonador.getAvgWT());
		
		
		try (FileWriter arqSaida = new FileWriter("C:/Users/Cliente/Downloads/ArqSaida.txt")) {

			PrintWriter gravarArq = new PrintWriter(arqSaida);
			
			gravarArq.printf(" --------- Gráfico de Gantt ---------\n\n");
			gravarArq.printf(textoGraficoGantt);
			gravarArq.printf("\n\n --------- Tabela de Processos ---------\n\n");
			gravarArq.printf(textoTabelaProcessos);
			gravarArq.printf("\n\n --------- Tempo médio no sistema:" + textoAvgTAT);
			gravarArq.printf("\n\n --------- Tempo médio de espera:" + textoAvgWT);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
