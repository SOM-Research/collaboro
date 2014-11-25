package fr.inria.atlanmod.collaboro.metrics.symbol;

import org.eclipse.emf.ecore.EObject;

public class Concept {
	
	private String name;
	private EObject abstractSyntaxElement;
	
	public Concept(String name, EObject abstractSyntaxElement) {
		this.name = name;
		this.abstractSyntaxElement = abstractSyntaxElement;
	}

	public String getName() {
		return name;
	}

	public EObject getAbstractSyntaxElement() {
		return abstractSyntaxElement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Concept other = (Concept) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

	

}
