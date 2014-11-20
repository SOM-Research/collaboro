package fr.inria.atlanmod.collaboro.metrics.impl;

import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;

public class MetricResultImpl implements MetricResult{
	
	private String reason;
	private float ratio;
	private MetricResultStatus status;
	private List<ReferredElement> referredElements;

	@Override
	public String getReason() {
		return reason;
	}

	@Override
	public float getRatio() {
		return ratio;
	}

	@Override
	public MetricResultStatus getStatus() {
		return status;
	}

	@Override
	public List<ReferredElement> getReferredElements() {
		return referredElements;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	public void setStatus(MetricResultStatus status) {
		this.status = status;
	}
	
	public void setReferredElements(List<ReferredElement> referredElements) {
		this.referredElements = referredElements;
	}

}
