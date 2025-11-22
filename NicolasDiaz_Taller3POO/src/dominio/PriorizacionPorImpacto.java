package dominio;

import java.util.ArrayList;

public class PriorizacionPorImpacto implements EstrategiaDePriorizacion {

	private int getValorTipo(String tipo) {
		if(tipo.equalsIgnoreCase("bug")) {
			return 3;
		}
		if(tipo.equalsIgnoreCase("feature")) {
			return 2;
		}
		if(tipo.equalsIgnoreCase("documentacion")) {
			return 1;
		}
		return 0;
	}
	@Override
	public void ordenar(ArrayList<Tarea> tareas) {
		//BubbleSort
		for(int i = 0; i < tareas.size()-1; i++) {
			for (int j = 0; j < tareas.size()-1-i; j++) {
				Tarea t1 = tareas.get(j);
				Tarea t2 = tareas.get(j+1);
				
				int valor1 = getValorTipo(t1.getTipo());
				int valor2 = getValorTipo(t2.getTipo());
				
				if(valor1 < valor2) {
					tareas.set(j, t2);
					tareas.set(j+1, t1);
				}
				
			}
		}
		}
}
