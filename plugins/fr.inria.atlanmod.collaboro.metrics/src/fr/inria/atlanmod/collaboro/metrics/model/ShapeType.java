package fr.inria.atlanmod.collaboro.metrics.model;

public enum ShapeType {

	LINE(0,"LINE","LINE"),
	RECTANGLE(1,"RECTANGLE","RECTANGLE"),
	LABEL(2,"LABEL","LABEL"),
	IMAGE(3,"IMAGE","IMAGE"),
	UNKNOWN(3,"UNKNOWN","UNKNOWN");

	
	private int value;
	private String name;
	private String literal;
	
	private static final ShapeType[] VALUES_ARRAY =
			new ShapeType[] {
				LINE,
				RECTANGLE,
				LABEL,
				IMAGE,
				UNKNOWN,
			};
	
	private ShapeType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}
	
	public static ShapeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ShapeType result = VALUES_ARRAY[i];
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
