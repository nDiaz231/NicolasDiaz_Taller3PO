package dominio;

public class TareaFactory {
	public static Tarea crearTarea(String id, String proyectoId,String tipo, String descripcion, String estado, String responsable,
			String complejidad, String fecha) {
		switch (tipo.toLowerCase()) {
		case "bug":
			return new Bug(id, proyectoId, tipo, descripcion, estado, responsable, complejidad, fecha);
		case "feature":
			return new Feature(id, proyectoId, tipo, descripcion, estado, responsable, complejidad, fecha);
		case "documentacion":
			return new Documentacion(id, proyectoId, tipo, descripcion, estado, responsable, complejidad, fecha);
		default:
			System.out.println("Tipo tarea desconocido "+tipo);
		}
		return null;
	}
}
