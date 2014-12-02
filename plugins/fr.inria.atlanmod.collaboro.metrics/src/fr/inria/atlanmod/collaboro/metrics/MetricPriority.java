package fr.inria.atlanmod.collaboro.metrics;


public enum MetricPriority {

	LOW(0,"LOW","LOW"),
	NORMAL(1,"NORMAL","NORMAL"),
	HIGH(2,"HIGH","HIGH");
	
	private int value;
	private String name;
	private String literal;
	
	public static final int LOW_VALUE = 0;
	
	private static final MetricPriority[] VALUES_ARRAY =
			new MetricPriority[] {
				LOW,
				NORMAL,
				HIGH,
			};
	
	private MetricPriority(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}
	
	public static MetricPriority getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MetricPriority result = VALUES_ARRAY[i];
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
	
	
	
}
