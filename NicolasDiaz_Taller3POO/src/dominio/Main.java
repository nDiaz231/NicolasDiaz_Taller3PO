package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	private static Scanner s ;

	
	
	public static void main(String[] args) throws IOException {
		s = new Scanner(System.in);
		lecturaProyecto();
		lecturaTarea();
		lecturaUsuario();
		boolean activo = true;
		while(activo) {
			mostarMenuLogin();
		}
		s.close();
	}

	private static void mostarMenuLogin() throws IOException {
		System.out.println("Bienvenido");
		System.out.print("Ingrese su nombre de usuario: > ");
		String nombre = s.nextLine();
		System.out.print("Ingrese su constraseña: > ");
		String constraseña = s.nextLine();
		Usuario usuario=SistemaEspecifico.getInstance().verificacion(nombre, constraseña);
		if (usuario == null) {
			System.out.println("Error nombre de usuario o constraseña incorrecta");
		}else {
			System.out.println("Bienvenido "+usuario.getNombre());
			if(usuario.getRol().equalsIgnoreCase("Administrador")) {
				mostrarMenuAdmin();
			}else {
				mostrarMenuUsuario(usuario);
			}
			System.out.println("Cerrando sesión "+usuario.getNombre());
		}
	}

	private static void mostrarMenuUsuario(Usuario usuario) {
		boolean activo = true;
		int opcion = 0;
		while(activo) {
			System.out.println();
			System.out.println("---Menu usuario---");
			System.out.println("1) Mostrar lista de proyectos");
			System.out.println("2) Ver tareas asignadas");
			System.out.println("3) Actualizar estado de una tarea");
			System.out.println("4) Aplicar acciones");
			System.out.println("5) Cerrar sesión");
			System.out.print("Eliga la opcion : >");
			opcion = s.nextInt();
			s.nextLine();
			switch (opcion) {
			case 1:
				mostrarListaProyectos();
				break;
			case 2:
				verTareasAsignadas(usuario);
				break;
			case 3:
				actualizarEstado();
			}
			
		}
	}

	private static void actualizarEstado() {
		System.out.println();
		System.out.println("---Actualizar estado---");
		System.out.print("Ingrese la ID proyecto a cambiar: >");
		String id = s.nextLine();
		
		Proyecto proyectoActualizado = SistemaEspecifico.getInstance().buscarProyecto(id);
		if (proyectoActualizado == null) {
		System.out.println("No existe");
		return;
		}
		for(Tarea t : proyectoActualizado.getTarea()) {
			System.out.println("ID Tarea: "+t.getId());
		}
	
		
		
		System.out.print("Ingrese la ID de la tarea a actualizar: >");
		String idTarea = s.nextLine();
		Tarea tareaEncontrada = null;
		for(Tarea t : proyectoActualizado.getTarea()) {
			if ( t.getId().equalsIgnoreCase(idTarea)) {
				tareaEncontrada = t;
				break;
			}else {
				System.out.println("No existe");
				return;
			}
		}
		
		System.out.println("Tarea: "+tareaEncontrada.getId());
		System.out.println("Estado: "+tareaEncontrada.getEstado());
		
		
		System.out.println("A que estado cambiar");
		System.out.println("1) Pendiente ");
		System.out.println("2) En progreso");
		System.out.println("3) Completada");
		System.out.print("Ingrese opcion: > ");
		int opcion = s.nextInt();
		s.nextLine();
		
		switch (opcion) {
		case 1:
			tareaEncontrada.setEstado("Pendiente");
			System.out.println("Estado actualizado");
			break;
		case 2:
			tareaEncontrada.setEstado("En progreso");
			System.out.println("Estado actualizado");
			break;
		case 3:
			tareaEncontrada.setEstado("Completado");
			System.out.println("Estado actualizado");
			break;
		default:
			System.out.println("Opcion no valida");
		}
		
		
		
		
		
		
		
	}

	private static void verTareasAsignadas(Usuario usuario) {
		System.out.println("");
		System.out.println("Tareas asignadas a "+usuario.getNombre());
		for(Proyecto p : SistemaEspecifico.getInstance().getProyectos()) {
			System.out.println("-----");
			System.out.println("Tarea ");
			for(Tarea t: p.getTarea()) {
				
				if(t.getResponsable().equalsIgnoreCase(usuario.getNombre())) {
					System.out.println("ID: "+t.getId());
					System.out.println("Descripcion: "+t.getDescripcion());
					System.out.println("Estado: "+t.getEstado());
					System.out.println("Tipo: "+t.getTipo());
				}
			
			}
		}
	}

	private static void mostrarListaProyectos() {
		System.out.println();
		System.out.println("---Proyectos---");
		for(Proyecto p :SistemaEspecifico.getInstance().getProyectos()) {
			System.out.println("--------");
			System.out.println("Nombre: "+p.getNombre());
			System.out.println("ID: "+p.getId());
			System.out.println("Responsable: "+p.getResponsable());
		}
	}

	private static void mostrarMenuAdmin() throws IOException {
		boolean activo = true;
		int opcion = 0;
		while (activo) {
			System.out.println();
			System.out.println("---Menu admin---");
			System.out.println("1) Ver lista completa de proyectos y tareas");
			System.out.println("2) Agregar o eliminar un proyecto");
			System.out.println("3) Agregar o eliminar tarea de un proyecto");
			System.out.println("4) Asignar prioridades");
			System.out.println("5) Generar reporte de proyectos ");
			System.out.println("6) Volver al inicio de sesión");
			System.out.print("Eliga la opcion : >");
			opcion = s.nextInt();
			s.nextLine();
			switch (opcion) {
			case 1:
				verListaProyectosTares();
				break;
			case 2:
				agregarEliminarProyecto();
				break;
			case 3:
				agregarEliminarTareas();
				break;
			case 4:
				asignarPrioridades();
				break;
			case 5:
				generarReporte();
				break;
			case 6:
				activo = false;
				System.out.println("Cerrando sesión");
				break;
			default:
				System.out.println("Ingrese valor valido ");
			}
			
		}
	}

	

	private static void generarReporte() throws IOException {
		System.out.println();
		System.out.println("---Generando Reporte---");
		ArrayList<Proyecto> lista = SistemaEspecifico.getInstance().getProyectos();
		
		try (FileWriter writer = new FileWriter("reportes.txt",true)) {
			writer.write("\n");
			writer.write("---Reporte---");
			writer.write("\n");
			for (Proyecto p: lista) {
				writer.write("\n");
				writer.write("Proyecto: "+p.getNombre()+"\n");
				writer.write("ID: "+p.getId()+"\n");
				writer.write("Responsable: "+p.getResponsable()+"\n");
				writer.write("--------"+"\n");
				writer.write("Tareas "+"\n");
				if(p.getTarea().isEmpty()) {
					writer.write("No tiene tareas asociadas"+"\n");
				}else {
					for(Tarea t: p.getTarea()) {
						writer.write("\n");
						writer.write("ID: "+t.getId()+"\n");
						writer.write("Estado: "+t.getEstado()+"\n");
						
					}
				}
				

			}
		}
		System.out.println();
		System.out.println("---Reporte generado---");
		
				
	}

	private static void asignarPrioridades() {
		System.out.println();
		System.out.println("---AsignarPrioridades---");
		System.out.println("IDs Proyectos");
		for(Proyecto p : SistemaEspecifico.getInstance().getProyectos()) {
			System.out.println("Proyecto "+p.getNombre() + ", ID: "+p.getId());
		}
		System.out.print("Ingrese ID del proyecto a ordenar: >");
		String id = s.nextLine();
		
		Proyecto proyectoBuscado = SistemaEspecifico.getInstance().buscarProyecto(id);
		
		if (proyectoBuscado == null) {
			System.out.println("Proyecto no existe");
			return;
			
		}
		if (proyectoBuscado.getTarea().isEmpty()) {
			System.out.println("El proyecto no tiene tareas asociadas");
			return;
		}
		
		System.out.println("Asignación de prioridades");
		System.out.println("1) Por fecha de creacion");
		System.out.println("2) Por impacto");
		System.out.println("3) Por complejidad");
		System.out.print("Ingrese su opción: >");
		int opcion = s.nextInt();
		s.nextLine();
		
		EstrategiaDePriorizacion estrategia = null;
		switch (opcion) {
		case 1:
			estrategia = new PriorizacionPorFecha();
			break;
		case 2:
			estrategia = new PriorizacionPorImpacto();
			break;
		case 3:
			estrategia = new PriorizacionPorComplejidad();
			break;

		default:
			System.out.println("Ingrese valor valido ");
			}
		
		estrategia.ordenar(proyectoBuscado.getTarea());
		
		
		System.out.println("---Prioridades---");
		for(Tarea t: proyectoBuscado.getTarea()) {
			System.out.println("ID: "+t.getId()+ ", Tipo: "+t.getTipo()+ ", Complejidad: "+t.getComplejidad()+ ", Fecha: "+t.getFecha());
		}
	}

	private static void agregarEliminarTareas() {
	System.out.println();
	int opcion = 0;
	System.out.println("---Agregar o Elimnar Tarea---");
	System.out.println("1) Agregar Tarea");
	System.out.println("2) Elimnar Tarea");
	System.out.println("3) Salir");
	System.out.print("Ingrese opcion: >");
	opcion = s.nextInt();
	s.nextLine();
	
	switch (opcion) {
	case 1:
		System.out.println();
		System.out.println("---Creación de tareas");
		System.out.print("Ingrese ID de proyecto al cual agregar la tarea: > ");
		String id = s.nextLine();
		Proyecto proyectoBuscado= SistemaEspecifico.getInstance().buscarProyecto(id);
		if( proyectoBuscado == null) {
			System.out.println("ID inexistente ");
			return;
		}
		System.out.print("Ingrese ID de la tarea: >");
		String idTarea=s.nextLine();
		if (proyectoBuscado != null) {
			ArrayList<Tarea> tareas = proyectoBuscado.getTarea();
			for (Tarea t : tareas) {
				if (t.getId().equalsIgnoreCase(idTarea)) {
					System.out.println("ID Existente");
					return;
				}
			}
		}
		System.out.print("Ingrese tipo de la tarea: >");
		String tipo=s.nextLine();
		System.out.print("Ingrese descripción de la tarea: >");
		String descripcion = s.nextLine();
		System.out.print("Ingrese estado de la tarea: >");
		String estado = s.nextLine();
		System.out.print("Ingrese responsable de la tarea: >");
		String responsable = s.nextLine();
		System.out.print("Ingrese complejidad de la tarea: >");
		String complejidad = s.nextLine();
		String fecha = new Date().toString();
		
		
		Tarea tareaNueva = TareaFactory.crearTarea(idTarea, id, tipo, descripcion, estado, responsable, complejidad, fecha);
		
		proyectoBuscado.agregarTarea(tareaNueva);
		System.out.println("Tarea Agregada");
		break;
		
	case 2:
		System.out.println();
		System.out.println("---Eliminar Tarea---");
		System.out.print("Ingrese id del proyecto : >");
		String idProyecto = s.nextLine();
		Proyecto proyectoTarea= SistemaEspecifico.getInstance().buscarProyecto(idProyecto);
		if (proyectoTarea != null) {
			ArrayList<Tarea> tareas = proyectoTarea.getTarea();
			System.out.println("Tareas asociadas a "+ proyectoTarea.getNombre());
			for (Tarea t : tareas) {
				System.out.println("Tarea ID: "+t.getId() + ", Tipo" + t.getTipo() + ", Descripcion "+ t.getDescripcion());
			}
			System.out.println();
			System.out.print("Ingrese ID de la tarea a eliminar: > ");
			String idBorrar = s.nextLine();
			for (int i = 0; i <tareas.size(); i++) {
				if ( tareas.get(i).getId().equalsIgnoreCase(idBorrar)) {
					tareas.remove(i);
					System.out.println("Tarea eliminada");
					break;
				}
			}
			
		}else {
			System.out.println("Proyecto inexistente ");
		}
	case 3:
		System.out.println("Volviendo...");
		break;
	default:
		System.out.println("Ingrese valor valido");
	}
	}

	private static void agregarEliminarProyecto() {
		System.out.println();
		int opcion = 0;
		System.out.println("---Agregar o Eliminar Proyecto---");
		System.out.println("1) Agregar proyecto");
		System.out.println("2) Eliminar proyecto");
		System.out.println("3) Salir");
		System.out.print("Ingrese opcion: >");
		opcion = s.nextInt();
		s.nextLine();
		switch (opcion) {
		case 1:
			System.out.println();
			System.out.println("---Creación de proyecto---");
			System.out.print("Ingrese ID: >");
			String id = s.nextLine();
			if (SistemaEspecifico.getInstance().buscarProyecto(id) != null) {
				System.out.println("ID existente");
				return;
			}
			System.out.print("Ingrese nombre del proyecto: >");
			String nombre = s.nextLine();
			System.out.print("Ingrese responsable: >");
			String responsable = s.nextLine();			
			Proyecto proyectoNuevo= new Proyecto(id,nombre,responsable);
			SistemaEspecifico.getInstance().agregarProyecto(proyectoNuevo);
			
			System.out.println("Proyecto "+ nombre+" Creado");
			break;
		case 2:
			System.out.println();
			System.out.println("---Eliminación de proyecto---");
			System.out.print("Ingrese ID: >");
			String idBuscada= s.nextLine();
			Proyecto proyectoEliminado=SistemaEspecifico.getInstance().buscarProyecto(idBuscada);
			
			if (proyectoEliminado != null) {
				SistemaEspecifico.getInstance().getProyectos().remove(proyectoEliminado);
				System.out.println("Proyecto "+ idBuscada +" Eliminado");
			}
			else {
				System.out.println("No existe el id");
			}
			break;
		case 3:
			System.out.println("Volviendo...");
			break;
		default:
			System.out.println("Error ingrese valor valido");
		}
	}

	private static void verListaProyectosTares() {
		System.out.println();
		System.out.println("Lista Proyectos y tareas");
		ArrayList<Proyecto> lista = SistemaEspecifico.getInstance().getProyectos();
		
		for (Proyecto p : lista) {
			System.out.println("---------------------");
			System.out.println("Proyecto: "+p.getNombre()+", ID: "+p.getId());
			System.out.println("Responsable: "+ p.getResponsable());
			
			ArrayList<Tarea> tareas = p.getTarea();
			if(tareas.isEmpty()) {
				System.out.println("  Sin tareas Asociadas");
			}
			else {
				System.out.println("Tareas asociadas ");
				for(Tarea t: tareas) {
					System.out.println(" (ID: "+t.getId()+", Tipo: "+t.getTipo()+", Descripción: "+t.getDescripcion()+")");
					}
			}
			
		}
		
	}

	private static void lecturaUsuario() throws FileNotFoundException {
		File arch = new File("usuarios.txt");
		Scanner s = new Scanner(arch);
		while (s.hasNextLine()) {
		String linea = s.nextLine();
		String datos [] = linea.split("\\|");
		String nombre = datos[0];
		String contraseña= datos[1];
		String rol= datos[2];
		
		Usuario usuario = new Usuario(nombre,contraseña,rol);
		SistemaEspecifico.getInstance().agregarUsuario(usuario);
		}
		s.close();

	}

	private static void lecturaTarea() throws FileNotFoundException {
		File arch = new File("tareas.txt");
		Scanner s = new Scanner(arch);
		while (s.hasNextLine()) {
		String linea = s.nextLine();
		String datos [] = linea.split("\\|");
		String idProyecto = datos[0];
		String idTarea= datos[1];
		String tipo = datos [2];
		String descripcion = datos [3];
		String estado = datos [4];
		String responsable = datos [5];
		String complejidad = datos [6];
		String fecha = datos [7];
		
		Tarea tarea = TareaFactory.crearTarea(idTarea, idProyecto, tipo, descripcion, estado, responsable, complejidad, fecha);
		Proyecto p =  SistemaEspecifico.getInstance().buscarProyecto(idProyecto);
		if (p != null) {
			p.agregarTarea(tarea);
		}
	
				
			
		
		}
		
		s.close();
	}

	private static void lecturaProyecto() throws FileNotFoundException {
		File arch = new File("proyectos.txt");
		Scanner s = new Scanner(arch);
		
		while (s.hasNextLine()) {
		String linea = s.nextLine();
		String datos [] = linea.split("\\|");
		String idProyecto = datos[0];
		String nombre= datos[1];
		String responsable = datos [2];
		Proyecto proyecto = new Proyecto(idProyecto,nombre,responsable);
		SistemaEspecifico.getInstance().agregarProyecto(proyecto);
		}
		s.close();
	}

}
