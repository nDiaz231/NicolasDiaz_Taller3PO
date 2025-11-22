//Nicolas Ignacio Diaz Romero (216340612-2) (ICCI)

package dominio;

import java.util.ArrayList;

public class PriorizacionPorFecha implements EstrategiaDePriorizacion {

	@Override
	public void ordenar(ArrayList<Tarea> tareas) {
		
		//Bubble sort
		for(int i = 0; i < tareas.size()-1; i++) {
			for (int j = 0; j < tareas.size()-1-i; j++) {
				Tarea t1 = tareas.get(j);
				Tarea t2 = tareas.get(j+1);
				
				
				if(t1.getFecha().compareTo(t2.getFecha()) > 0) { 
					//Comparamos fechas con el compareTo comparo digito por digito
					tareas.set(j,t2);
					tareas.set(j+1,t1);
				}
			}
		}
	}

}
