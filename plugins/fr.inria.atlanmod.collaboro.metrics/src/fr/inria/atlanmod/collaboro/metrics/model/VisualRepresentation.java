package fr.inria.atlanmod.collaboro.metrics.model;

import fr.inria.atlanmod.collaboro.notation.GraphicalElement;

public class VisualRepresentation {
	
	private ShapeType shape;
	private Position position;
	private Colour colour;
	private Size size;
	private GraphicalElement graphicalElement;
	
	public VisualRepresentation(ShapeType shape, Position position,
			Colour colour, Size size, GraphicalElement graphicalElement) {
		this.shape = shape;
		this.position = position;
		this.colour = colour;
		this.size = size;
		this.graphicalElement = graphicalElement;
	}

	public VisualRepresentation() {
		
	}

	public ShapeType getShape() {
		return shape;
	}

	public void setShape(ShapeType shape) {
		this.shape = shape;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public GraphicalElement getGraphicalElement() {
		return graphicalElement;
	}

	public void setGraphicalElement(GraphicalElement graphicalElement) {
		this.graphicalElement = graphicalElement;
	}

	public String toString() {
		return "VisualRepresentation [shape=" + shape + ", position="
				+ position + ", colour=" + colour + ", size=" + size
				+ ", graphicalElement=" + graphicalElement + "]";
	}
	
}
