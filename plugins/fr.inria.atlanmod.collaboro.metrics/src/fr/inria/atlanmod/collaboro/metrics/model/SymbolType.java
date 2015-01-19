package fr.inria.atlanmod.collaboro.metrics.model;


public enum SymbolType {
	
	UNKNOWN(0,"UNKNOWN","UNKNOWN"),
	CLASS(1,"CLASS","CLASS"),
	ATTRIBUTE(2,"ATTRIBUTE","ATTRIBUTE"),
	REFERENCE(3,"REFERENCE","REFERENCE"),
	STRUCTURAL(4,"STRUCTURAL","STRUCTURAL");

	
	private int value;
	private String name;
	private String literal;
	
	private static final SymbolType[] VALUES_ARRAY =
			new SymbolType[] {
				UNKNOWN,
				CLASS,
				ATTRIBUTE,
				REFERENCE,
				STRUCTURAL,
			};
	
	private SymbolType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}
	
	public static SymbolType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SymbolType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}
	
	public int getValue() {
	  return value;
	}

	public String getName() {
	  return name;
	}

	public String getLiteral() {
	  return literal;
	}
	
	public String toString() {
		return literal;
	}

}
