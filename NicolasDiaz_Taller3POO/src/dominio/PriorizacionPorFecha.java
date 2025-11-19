package dominio;

import java.util.ArrayList;

public class PriorizacionPorFecha implements EstrategiaDePriorizacion {

	@Override
	public void ordenar(ArrayList<Tarea> tareas) {
		int n = tareas.size();
		
		for(int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-1-i; j++) {
				Tarea t1 = tareas.get(j);
				Tarea t2 = tareas.get(j+1);
				
				
				if(t1.getFecha().compareTo(t2.getFecha()) > 0) {
					tareas.set(j,t2);
					tareas.set(j+1,t1);
				}
			}
		}
	}

}
