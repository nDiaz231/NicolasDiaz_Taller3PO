package dominio;

public class AccionesVisitor implements TareaVisitor{

	@Override
	public void visit(Bug bug) {
		System.out.println("Bug "+bug.getDescripcion());
		System.out.println("Afecta la criticidad del proyecto");
	}

	@Override
	public void visit(Feature feature) {
		System.out.println("Feature "+feature.getDescripcion());
		System.out.println("Impacta en la estimacion de tiempo");
	
	}

	@Override
	public void visit(Documentacion doc) {
		System.out.println("Documentación "+doc.getDescripcion());
		System.out.println("Mejora la calidad del proyecto");
	
			
	}
	
	
}
