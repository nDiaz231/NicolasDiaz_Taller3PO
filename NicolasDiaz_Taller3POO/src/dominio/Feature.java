//Nicolas Ignacio Diaz Romero (216340612-2) (ICCI)

package dominio;

public class Feature extends Tarea{



	public Feature(String id, String proyectoId, String tipo, String descripcion, String estado, String responsable,
			String complejidad, String fecha) {
		super(id, proyectoId, tipo, descripcion, estado, responsable, complejidad, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(TareaVisitor visitor) {
		visitor.visit(this);
	}

}
