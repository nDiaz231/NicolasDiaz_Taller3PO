package dominio;

public abstract class Tarea {
	protected String id;
	protected String proyectoId;
	protected String tipo;
	protected String descripcion;
	protected String estado;
	protected String responsable;
	protected String complejidad;
	protected String fecha;
	
	public Tarea(String id, String proyectoId,String tipo, String descripcion, String estado, String responsable,
			String complejidad, String fecha) {
		this.id = id;
		this.proyectoId = proyectoId;
		this.tipo=tipo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.responsable = responsable;
		this.complejidad = complejidad;
		this.fecha = fecha;
	}
	public abstract void accept(TareaVisitor visitor);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProyectoId() {
		return proyectoId;
	}
	public void setProyectoId(String proyectoId) {
		this.proyectoId = proyectoId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getComplejidad() {
		return complejidad;
	}
	public void setComplejidad(String complejidad) {
		this.complejidad = complejidad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
