package dominio;

import java.util.ArrayList;

public class SistemaEspecifico {
	
	private static SistemaEspecifico instance;
	private static ArrayList<Proyecto> proyectos;
	private static ArrayList<Usuario> usuarios;
	public SistemaEspecifico() {
		this.proyectos= new ArrayList<>();
		this.usuarios = new ArrayList<>();
		
	}
	public static SistemaEspecifico getInstance(){
		if(instance == null) {
			instance = new SistemaEspecifico();
		}
		return instance;
	}
	public static ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}
	public static void setProyectos(ArrayList<Proyecto> proyectos) {
		SistemaEspecifico.proyectos = proyectos;
	}
	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public static void setUsuarios(ArrayList<Usuario> usuarios) {
		SistemaEspecifico.usuarios = usuarios;
	}
	public static void setInstance(SistemaEspecifico instance) {
		SistemaEspecifico.instance = instance;
	}
	public void agregarProyecto(Proyecto p) {
		this.proyectos.add(p);
	}
	public void agregarUsuario(Usuario u) {
		this.usuarios.add(u);
	}
	
	public Usuario verificacion(String nombre, String constraseña) {
		for (Usuario u : usuarios) {
			if(u.getNombre().equals(nombre) && u.getContraseña().equals(constraseña)) {
				return u;
			}
		}
		return null;
	}
	public Proyecto buscarProyecto(String id) {
		for(Proyecto p: proyectos) {
			if(p.getId().equalsIgnoreCase(id))
				return p;
		}
		return null;
	}
	
	
}
