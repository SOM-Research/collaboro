package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.model.Symbol;

public abstract class ConcreteSyntaxElementExtractor {
	
	public abstract List<Symbol> discoverConcreteSymbols();

}
