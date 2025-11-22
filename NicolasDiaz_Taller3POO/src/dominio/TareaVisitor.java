//Nicolas Ignacio Diaz Romero (216340612-2) (ICCI)

package dominio;

public interface TareaVisitor {
	void visit(Bug bug);
	void visit(Feature feature);
	void visit(Documentacion doc);
	
}
