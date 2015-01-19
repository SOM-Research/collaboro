package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.model.ShapeType;
import fr.inria.atlanmod.collaboro.metrics.model.Symbol;
import fr.inria.atlanmod.collaboro.metrics.model.VisualRepresentation;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;

public class DualCoding extends ConcreteSyntaxGraphicalMetricImpl {
	
	private static String dimension = "Ontological";
	private static String description = "Textual encoding added to visual symbols convey information better";	
	
	public DualCoding(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public DualCoding(ModelMapping modelMapping) {
		super("Dual Coding","Ontological", "Textual encoding added to visual symbols convey information better",0);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		System.out.println("Excecute DualCoding");
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<ReferredElement> referredElements = new ArrayList<ReferredElement>();
		
		List<Symbol> symbols = this.modelMapping.getConcreteSymbols();
		for(Symbol symbol : symbols) {
			if(!checkSymbolContainingLabel(symbol)) {
				ReferredElement referredElement = new ConcreteSyntaxReferredElementImpl(symbol.getFullName(), ReferredElementReason.WRONG, symbol.getNotationElement());
				referredElements.add(referredElement);
			}
		}
		
		if(referredElements.size() > this.acceptanceRatio) {
			MetricResultImpl metricResult =  new MetricResultImpl();
			metricResult.setStatus(MetricResultStatus.MIDDLE);
			float ratio = (referredElements.size() * 100) / this.modelMapping.getConcreteSymbols().size();
			metricResult.setRatio(ratio);
			metricResult.setReason("Symbols should have textual encoded data to reinforce their meaning (" + referredElements.size() + ")");
			metricResult.setReferredElements(referredElements);
			results.add(metricResult);
		} else {
			MetricResultImpl metricResult =  new MetricResultImpl();
			metricResult.setStatus(MetricResultStatus.GOOD);
			float ratio = (referredElements.size() * 100) / this.modelMapping.getConcreteSymbols().size();
			metricResult.setRatio(ratio);
			metricResult.setReason("Symbols have textual encoded data to reinforce their meaning");
			metricResult.setReferredElements(referredElements);
			results.add(metricResult);
		}
		
		
		return results;
	}
	
	private boolean checkSymbolContainingLabel(Symbol symbol) {
		List<VisualRepresentation> visualRepresentations = symbol.getVisualRepresentations();
		for(VisualRepresentation visualRepresentation : visualRepresentations) {
			if(visualRepresentation.getShape().equals(ShapeType.LABEL)) {
				return true;
			}
		}
		return false;
	}

}
