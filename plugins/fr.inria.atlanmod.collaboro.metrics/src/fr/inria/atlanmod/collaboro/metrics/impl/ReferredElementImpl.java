package fr.inria.atlanmod.collaboro.metrics.impl;

import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;

public class ReferredElementImpl implements ReferredElement{

	private ReferredElementReason reason;
	private String name;
	
	public ReferredElementImpl(String name, ReferredElementReason reason) {
		this.reason = reason;
	}
	
	public ReferredElementReason getReason() {
		return this.reason;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
