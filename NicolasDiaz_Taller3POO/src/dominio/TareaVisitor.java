package dominio;

public interface TareaVisitor {
	void visit(Bug bug);
	void visit(Feature feature);
	void visit(Documentacion doc);
	
}
