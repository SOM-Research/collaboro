package fr.inria.atlanmod.collaboro.metrics.model;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class Symbol {
	
	private String nameFull;
	private String nameForMapping;
	private NotationElement notationElement;
	private SymbolType type;
	private List<VisualRepresentation> visualRepresentations;
	
	public Symbol() {
		this.type = SymbolType.UNKNOWN;
		this.visualRepresentations = new ArrayList<VisualRepresentation>();
	}
	
	public Symbol(String nameFull, String nameForMapping,SymbolType symbolType ,NotationElement notationElement) {
		this.nameFull = nameFull;
		this.nameForMapping = nameForMapping;
		this.notationElement = notationElement;
		this.type = symbolType;
		this.visualRepresentations = new ArrayList<VisualRepresentation>();
	}
	
	public Symbol(String name, String nameForMapping, NotationElement notationElement,
			SymbolType type, List<VisualRepresentation> visualRepresentations) {
		this.nameFull = name;
		this.nameForMapping = nameForMapping;
		this.notationElement = notationElement;
		this.type = type;
		this.visualRepresentations = visualRepresentations;
	}

	public String getFullName() {
		return nameFull;
	}

	public void setFullName(String nameFull) {
		this.nameFull = nameFull;
	}

	public String getMappingName() {
		return nameForMapping;
	}

	public void setMappingName(String nameForMapping) {
		this.nameForMapping = nameForMapping;
	}
	
	public NotationElement getNotationElement() {
		return notationElement;
	}

	public void setNotationElement(NotationElement notationElement) {
		this.notationElement = notationElement;
	}

	public SymbolType getType() {
		return type;
	}

	public void setType(SymbolType type) {
		this.type = type;
	}

	public List<VisualRepresentation> getVisualRepresentations() {
		return visualRepresentations;
	}

	public void setVisualRepresentations(
			List<VisualRepresentation> visualRepresentations) {
		this.visualRepresentations = visualRepresentations;
	}
	
	public void addVisualRepresentation(VisualRepresentation visualRepresentation) {
		this.visualRepresentations.add(visualRepresentation);
	}
	
	public String toString() {
		return "Symbol [nameFull=" + nameFull + ", nameForMapping="
				+ nameForMapping + ", notationElement=" + notationElement
				+ ", type=" + type + ", visualRepresentations="
				+ visualRepresentations + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nameForMapping == null) ? 0 : nameForMapping.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symbol other = (Symbol) obj;
		if (nameForMapping == null) {
			if (other.nameForMapping != null)
				return false;
		} else if (!nameForMapping.equals(other.nameForMapping))
			return false;
		return true;
	}
	
	public boolean equals(String fullName) {
		if(fullName.equals(this.nameFull)) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	

}
