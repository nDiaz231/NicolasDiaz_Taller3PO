package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Scanner s ;

	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		lecturaProyecto();
		lecturaTarea();
		lecturaUsuario();
		mostarMenuLogin();
	}

	private static void mostarMenuLogin() {
		 s = new Scanner(System.in);
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
				mostrarMenuUsuario();
			}
		}
		s.close();
	}

	private static void mostrarMenuUsuario() {
		// TODO Auto-generated method stub
		
	}

	private static void mostrarMenuAdmin() {
		boolean activo = true;
		int opcion = 0;
		s = new Scanner(System.in);
		while (activo) {
			System.out.println();
			System.out.println("--Menu admin--");
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
			}
			
		}
	}

	

	private static void agregarEliminarTareas() {
	System.out.println();
	s= new Scanner(System.in);
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
		System.out.println("");
	}
	}

	private static void agregarEliminarProyecto() {
		System.out.println();
		s = new Scanner(System.in);
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
					System.out.println(" (Tipo: "+t.getTipo()+", Descripción: "+t.getDescripcion()+")");
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
		
		Tarea tarea = TareaFactory.crearTarea(idProyecto, idTarea, tipo, descripcion, estado, responsable, complejidad, fecha);
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
