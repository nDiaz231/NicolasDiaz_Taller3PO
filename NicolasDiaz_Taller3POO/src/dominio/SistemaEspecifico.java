package dominio;

import java.util.ArrayList;

public class SistemaEspecifico {
	
	private static SistemaEspecifico instance;
	private  ArrayList<Proyecto> proyectos;
	private  ArrayList<Usuario> usuarios;
	private SistemaEspecifico() {
		this.proyectos= new ArrayList<>();
		this.usuarios = new ArrayList<>();
		
	}
	public static SistemaEspecifico getInstance(){
		if(instance == null) {
			instance = new SistemaEspecifico();
		}
		return instance;
	}
	
	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}
	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
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
