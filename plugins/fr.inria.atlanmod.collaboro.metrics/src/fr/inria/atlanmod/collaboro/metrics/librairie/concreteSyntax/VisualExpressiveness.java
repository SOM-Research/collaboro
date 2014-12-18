package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.model.Colour;
import fr.inria.atlanmod.collaboro.metrics.model.Position;
import fr.inria.atlanmod.collaboro.metrics.model.ShapeType;
import fr.inria.atlanmod.collaboro.metrics.model.Size;
import fr.inria.atlanmod.collaboro.metrics.model.Symbol;
import fr.inria.atlanmod.collaboro.metrics.model.VisualRepresentation;

public class VisualExpressiveness extends ConcreteSyntaxGraphicalMetricImpl {
	
	private static String description = "";
	private static String dimension = "Ontological";

	public VisualExpressiveness(String name, Integer acceptanceRatio,
			MetricPriority priority, boolean isActive) {
		super(name, dimension, description, acceptanceRatio, priority, isActive);
	}

	public List<MetricResult> execute() {
		// if they are different values for a visual variable -> visualExpressiveness + 1
		
		System.out.println("Execute Visual Expressiveness");
		List<MetricResult> results = new ArrayList<MetricResult>();
		
		List<Symbol> concreteSymbols = this.modelMapping.getConcreteSymbols();
		
		List<ShapeType> shapeTypeUsed = new ArrayList<ShapeType>();
		List<Size> sizeUsed = new ArrayList<Size>();
		List<Position> positionUsed = new ArrayList<Position>();
		List<Colour> colourUsed = new ArrayList<Colour>();
		
		for(Symbol concreteSymbol : concreteSymbols) {
			List<VisualRepresentation> visualRepresentations = concreteSymbol.getVisualRepresentations();
			if(visualRepresentations.size() > 1) {
				
			} else if(visualRepresentations.size() < 1) {
				
			} else {
				VisualRepresentation visualRepresentation = visualRepresentations.get(0);
				ShapeType shapeType = visualRepresentation.getShape();
				Size size = visualRepresentation.getSize();
				Position position = visualRepresentation.getPosition();
				Colour colour = visualRepresentation.getColour();
				
				if(!shapeTypeUsed.contains(shapeType)) {
					shapeTypeUsed.add(shapeType);
				}
				if(!sizeUsed.contains(size)) {
					sizeUsed.add(size);
				}
				if(!positionUsed.contains(position)) {
					positionUsed.add(position);
				}
				if(!colourUsed.contains(colour)) {
					colourUsed.add(colour);
				}
				
			}
		}
		
		int visualVariation = 0;
		int count = 0;
		String visualVariationOn = "";
		if(shapeTypeUsed.size() > 1) {
			visualVariation++;
			visualVariationOn += "Shape,";
		} else if (shapeTypeUsed.size() == 1 ){
			// shape is used but only one type
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setStatus(MetricResultStatus.BAD);
			
			metricResult.setReason("The concrete syntax should use more Shapes (only shape used : " + shapeTypeUsed.get(0).getName() + ")");
			metricResult.setRatio(1);
			metricResult.setReferredElements(new ArrayList<ReferredElement>());
			results.add(metricResult);
			count++;
		} else {
			
		}
		
		if(sizeUsed.size() > 1) {
			visualVariation++;
			visualVariationOn += "Size,";
		} else if (sizeUsed.size() == 1 ){

		} else {
			
		}
		
		if(positionUsed.size() > 1) {
			visualVariation++;
			visualVariationOn += "Position,";
		} else if (positionUsed.size() == 1 ){

		} else {
			
		}
		
		if(colourUsed.size() > 1) {
			visualVariation++;
			visualVariationOn += "Colour,";
		} else if (colourUsed.size() == 1 ){
			
		} else {
			
		}
		
		if(!visualVariationOn.equals("")) {
			visualVariationOn = visualVariationOn.substring(0, visualVariationOn.length() - 1);
		}
		
		if(visualVariation < this.acceptanceRatio) {
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setStatus(MetricResultStatus.BAD);
			String visualVariatonMessage = String.valueOf(visualVariation);
			if(visualVariation != 0) {
				visualVariatonMessage += " : " + visualVariationOn;
			}
			
			metricResult.setReason("The concrete syntax doesn't use enough visual variables (" + visualVariatonMessage + ")");
			metricResult.setRatio(visualVariation);
			metricResult.setReferredElements(new ArrayList<ReferredElement>());
			results.add(metricResult);
		}
		
		System.out.println("Visual Expressiveness : " + visualVariation + " on : " + visualVariationOn);
		
		
		
		
		return results;
	}

}
