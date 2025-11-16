package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Proyecto> proyectos = new ArrayList<>();
	private static ArrayList<Usuario> usuarios = new ArrayList<>();
	
	
	public static void main(String[] args) throws FileNotFoundException {
		lecturaProyecto();
		lecturaTarea();
		lecturaUsuario();
		
		
		mostarMenuLogin();
	}

	private static void mostarMenuLogin() {
		Scanner s = new Scanner(System.in);
		System.out.println("Bienvenido");
		System.out.print("Ingrese su nombre de usuario: > ");
		String nombre = s.nextLine();
		System.out.print("Ingrese su constraseña: > ");
		String constraseña = s.nextLine();
		Usuario usuario=verificacion(nombre,constraseña);
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
		// TODO Auto-generated method stub
		
	}

	private static Usuario verificacion(String nombre, String constraseña) {
		for (Usuario u : usuarios) {
			if(u.getNombre().equals(nombre) && u.getContraseña().equals(constraseña)) {
				return u;
			}
		}
		return null;
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
		usuarios.add(usuario);
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
		
		for (Proyecto p: proyectos) {
			if(p.getId().equalsIgnoreCase(idProyecto)) {
				p.agregarTarea(tarea);
				break;
				
			}
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
		proyectos.add(proyecto);
		}
		s.close();
	}

}
