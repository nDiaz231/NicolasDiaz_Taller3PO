//Nicolas Ignacio Diaz Romero (216340612-2) (ICCI)

package dominio;

import java.util.ArrayList;

public class PriorizacionPorComplejidad implements EstrategiaDePriorizacion {
	private int getValorTipo(String complejidad) {
		//Le damos valores a nuesta complejidad para comparar
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
		//Bubble Sort
		for(int i = 0; i < tareas.size()-1; i++) {
			for (int j = 0; j < tareas.size()-1-i; j++) {// Compara tarea por tarea y las intercambia
				//Evitamos volver a comparar la misma parte 
				Tarea t1 = tareas.get(j);
				Tarea t2 = tareas.get(j+1);
				
				int valor1 = getValorTipo(t1.getComplejidad());
				int valor2 = getValorTipo(t2.getComplejidad());
				
				if(valor1 < valor2) { // si es menor se intercambian BubbleSort
					tareas.set(j, t2);
					tareas.set(j+1, t1);
				}
				
			}
		}
		
	}

}
