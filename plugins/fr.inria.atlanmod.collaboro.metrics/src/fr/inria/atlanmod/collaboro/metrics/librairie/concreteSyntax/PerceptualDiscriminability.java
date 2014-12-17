package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.model.Colour;
import fr.inria.atlanmod.collaboro.metrics.model.Position;
import fr.inria.atlanmod.collaboro.metrics.model.ShapeType;
import fr.inria.atlanmod.collaboro.metrics.model.Size;
import fr.inria.atlanmod.collaboro.metrics.model.Symbol;
import fr.inria.atlanmod.collaboro.metrics.model.VisualRepresentation;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.model.SymbolComparison;

public class PerceptualDiscriminability extends ConcreteSyntaxGraphicalMetricImpl {
	
	private static String description = "Different symbols should be clearly distinguishable from each other";
	private static String dimension = "Ontological";

	public PerceptualDiscriminability(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive) {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public PerceptualDiscriminability(ModelMapping modelMapping) {
		super("Perceptual Discriminability",dimension,description,0);
		this.modelMapping = modelMapping;
	}

	public List<MetricResult> execute() {
		System.out.println("Execute PerceptualDiscriminability");
		List<MetricResult> results = new ArrayList<MetricResult>();
		
		// Symbols should have at least one unique value for one visual variable
		List<ReferredElement> elementWithNoUniqueValueList = new ArrayList<ReferredElement>();
		
		
		List<SymbolComparison> symbolComparisonList = new ArrayList<SymbolComparison>();
		
		List<Symbol> concreteSymbols = this.modelMapping.getConcreteSymbols();
		
		for(int i = 0 ; i < concreteSymbols.size() ; i++) {
			
			if(i == concreteSymbols.size() - 1) {
				
			}
			Symbol currentSymbol = concreteSymbols.get(i);
			
			ShapeType currentShape = null;
			Size currentSize = null;
			Position currentPosition = null;
			Colour currentColour = null;
			
			boolean shapeUnique = true;
			boolean sizeUnique = true;
			boolean positionUnique = true;
			boolean colourUnique = true;
			
			List<VisualRepresentation> currentSymbolVisualRepresentations = currentSymbol.getVisualRepresentations();
			if(currentSymbolVisualRepresentations.size() > 1) {
				for(VisualRepresentation visualRepresentation : currentSymbolVisualRepresentations) {
					ShapeType shape = visualRepresentation.getShape();
					Size size = visualRepresentation.getSize();
					Position position = visualRepresentation.getPosition();
					Colour colour = visualRepresentation.getColour();
					//TODO aggreagate
				}
			} else if(currentSymbolVisualRepresentations.size() < 1) {
				// error
			} else {
				VisualRepresentation visualRepresentation = currentSymbolVisualRepresentations.get(0);
				currentShape = visualRepresentation.getShape();
				currentSize = visualRepresentation.getSize();
				currentPosition = visualRepresentation.getPosition();
				currentColour = visualRepresentation.getColour();
			}
			
			if(currentShape != null) {
				for(int j = 0 ; j < concreteSymbols.size() ; j++) {
					Symbol compareSymbol = concreteSymbols.get(j);
					
					ShapeType compareShape = null;
					Size compareSize = null;
					Position comparePosition = null;
					Colour compareColour = null;
					
					List<VisualRepresentation> compareSymbolVisualRepresentations = compareSymbol.getVisualRepresentations();
					if(compareSymbolVisualRepresentations.size() > 1) {
						for(VisualRepresentation visualRepresentation : compareSymbolVisualRepresentations) {
							ShapeType shape = visualRepresentation.getShape();
							Size size = visualRepresentation.getSize();
							Position position = visualRepresentation.getPosition();
							Colour colour = visualRepresentation.getColour();
							//TODO aggregate
							
						}
					} else if(compareSymbolVisualRepresentations.size() < 1) {
						// error
					} else {
						VisualRepresentation visualRepresentation = compareSymbolVisualRepresentations.get(0);
						compareShape = visualRepresentation.getShape();
						compareSize = visualRepresentation.getSize();
						comparePosition = visualRepresentation.getPosition();
						compareColour = visualRepresentation.getColour();
					}
					
					boolean isShapeSame = currentShape.equals(compareShape);
					boolean isSizeSame = currentSize.equals(compareSize);
					boolean isPositionSame = currentPosition.equals(comparePosition);
					boolean isColourSame = currentColour.equals(compareColour);
					
					if(j != i) {
						if(j > i) {
							SymbolComparison symbolComparison = new SymbolComparison(currentSymbol, compareSymbol);
							symbolComparison.setShapeSame(isShapeSame);
							symbolComparison.setSizeSame(isSizeSame);
							symbolComparison.setPositionSame(isPositionSame);
							symbolComparison.setColourSame(isColourSame);
							symbolComparisonList.add(symbolComparison);
						}
						shapeUnique &=  !isShapeSame;
						colourUnique &= !isColourSame;
						positionUnique &= !isPositionSame;
						sizeUnique &= !isSizeSame;
					}
					
					
				}
				System.out.println(" ---------- ");
				System.out.println("symbol ref : " + currentSymbol.getFullName());
				int numberOfUniqueVisualVariable = 0;
				if(shapeUnique) {
					System.out.println("shape is unique");
					numberOfUniqueVisualVariable++;
				}
				if(sizeUnique) {
					System.out.println("Size is unique");
					numberOfUniqueVisualVariable++;
				}
				if(positionUnique) {
					System.out.println("Position is unique");
					numberOfUniqueVisualVariable++;
				}
				if(colourUnique) {
					System.out.println("Colour is unique");
					numberOfUniqueVisualVariable++;
				}
				
				if(numberOfUniqueVisualVariable == 0) {
					ReferredElement noUniqueValueElement = new ConcreteSyntaxReferredElementImpl(currentSymbol.getFullName(), ReferredElementReason.WRONG, currentSymbol.getNotationElement());
					elementWithNoUniqueValueList.add(noUniqueValueElement);
				}
				
				System.out.println("number of unique visual variable : " + numberOfUniqueVisualVariable);
			}
		}
		
		for(SymbolComparison symbolComparison : symbolComparisonList) {
			boolean isShapeSame = symbolComparison.isShapeSame();
			boolean isSizeSame = symbolComparison.isSizeSame();
			boolean isPositionSame = symbolComparison.isPositionSame();
			boolean isColourSame = symbolComparison.isColourSame();
			
			int visualDistance = 4;
			if(isShapeSame) {
				visualDistance--;
			}
			if(isSizeSame) {
				visualDistance--;
			}
			if(isPositionSame) {
				visualDistance--;
			}
			if(isColourSame) {
				visualDistance--;
			}
			
			System.out.println("Visual distance between : " + visualDistance);
			System.out.println("\t ref : " + symbolComparison.getReferenceSymbol().getFullName());
			System.out.println("\t comp : " + symbolComparison.getCompareSymbol().getFullName());
			
			if(visualDistance == 0) {
				Symbol referenceSymbol = symbolComparison.getReferenceSymbol();
				Symbol compareSymbol = symbolComparison.getCompareSymbol();
				
				MetricResultImpl metricResult = new MetricResultImpl();
				metricResult.setReason("The two symbols can not be differenciated");
				metricResult.setStatus(MetricResultStatus.BAD);
				
				List<ReferredElement> referredElementList = new ArrayList<ReferredElement>();
				ReferredElement refReferredElement = new ConcreteSyntaxReferredElementImpl(referenceSymbol.getFullName(), ReferredElementReason.WRONG, referenceSymbol.getNotationElement());
				ReferredElement compReferredElement = new ConcreteSyntaxReferredElementImpl(compareSymbol.getFullName(), ReferredElementReason.WRONG, compareSymbol.getNotationElement());
				referredElementList.add(refReferredElement);
				referredElementList.add(compReferredElement);
				
				metricResult.setReferredElements(referredElementList);
				results.add(metricResult);
			}
		}
		
		if(elementWithNoUniqueValueList.size() > 0) {
			MetricResultImpl metricResult =  new MetricResultImpl();
			metricResult.setStatus(MetricResultStatus.MIDDLE);
			float ratio = (elementWithNoUniqueValueList.size() * 100) / this.modelMapping.getConcreteSymbols().size();
			metricResult.setRatio(ratio);
			metricResult.setReason("Symbols should have at least one unique value for one of the visual variables");
			metricResult.setReferredElements(elementWithNoUniqueValueList);
			results.add(metricResult);
		}
		
		return results;
	}

}
