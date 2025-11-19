package dominio;

import java.util.ArrayList;

public class PriorizacionPorComplejidad implements EstrategiaDePriorizacion {
	private int getValorTipo(String complejidad) {
		if(complejidad.equalsIgnoreCase("alta")) {
			return 3;
		}
		if(complejidad.equalsIgnoreCase("media")) {
			return 2;
		}
		if(complejidad.equalsIgnoreCase("baja")) {
			return 1;
		}
		return 0;
	}
	@Override
	public void ordenar(ArrayList<Tarea> tareas) {
		int n = tareas.size();
		for(int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-1-i; j++) {
				Tarea t1 = tareas.get(j);
				Tarea t2 = tareas.get(j+1);
				
				int valor1 = getValorTipo(t1.getComplejidad());
				int valor2 = getValorTipo(t2.getComplejidad());
				
				if(valor1 < valor2) {
					tareas.set(j, t2);
					tareas.set(j+1, t1);
				}
				
			}
		}
		
	}

}
