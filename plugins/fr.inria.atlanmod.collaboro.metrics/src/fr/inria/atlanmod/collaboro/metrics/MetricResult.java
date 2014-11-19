package fr.inria.atlanmod.collaboro.metrics;

import java.util.List;

public interface MetricResult {
	
	public String getReason();
	
	public float getRatio();
	
	public MetricResultStatus getStatus();
	
	public List<ReferredElement> getReferredElements();

}
