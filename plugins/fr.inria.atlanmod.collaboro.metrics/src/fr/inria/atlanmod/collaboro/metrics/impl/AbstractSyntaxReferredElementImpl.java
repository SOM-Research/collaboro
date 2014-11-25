package fr.inria.atlanmod.collaboro.metrics.impl;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;

public class AbstractSyntaxReferredElementImpl extends ReferredElementImpl implements AbstractSyntaxReferredElement {

	private EObject abstractSyntaxElement;
	
	public AbstractSyntaxReferredElementImpl(String name,
			ReferredElementReason reason, EObject abstractSyntaxElement) {
		super(name, reason);
		this.abstractSyntaxElement = abstractSyntaxElement;	
	}

	public EObject getAbstractSyntaxElement() {
		return this.abstractSyntaxElement;
	}
}
