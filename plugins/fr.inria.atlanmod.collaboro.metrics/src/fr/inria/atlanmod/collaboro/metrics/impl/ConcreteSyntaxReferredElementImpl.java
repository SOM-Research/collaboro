package fr.inria.atlanmod.collaboro.metrics.impl;

import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class ConcreteSyntaxReferredElementImpl extends ReferredElementImpl implements ConcreteSyntaxReferredElement {
	
	private NotationElement concreteSyntaxElement;

	public ConcreteSyntaxReferredElementImpl(String name, ReferredElementReason reason, NotationElement concreteSyntaxElement) {
		super(name,reason);
		this.concreteSyntaxElement = concreteSyntaxElement;
	}

	public NotationElement getConcreteSyntaxElement() {
		return this.concreteSyntaxElement;
	}

}
