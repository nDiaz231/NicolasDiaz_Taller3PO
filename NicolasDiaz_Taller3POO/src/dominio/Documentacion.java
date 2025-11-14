package dominio;

public class Documentacion extends Tarea{

	public Documentacion(String id, String proyectoId, String descripcion, String estado, String responsable,
			String complejidad, String fecha) {
		super(id, proyectoId, descripcion, estado, responsable, complejidad, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(TareaVisitor visitor) {
		visitor.visit(this);
	}

}
