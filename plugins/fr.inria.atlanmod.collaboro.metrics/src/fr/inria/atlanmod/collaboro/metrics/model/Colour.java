package fr.inria.atlanmod.collaboro.metrics.model;

import fr.inria.atlanmod.collaboro.notation.Color;

public class Colour {

	private Color stroke;
	private Color fill;
	private Color textColor;
	
	public Colour() {
		this.stroke = Color.BLACK;
		this.fill = Color.WHITE;
		this.textColor = null;
	}
	
	public Colour(Color stroke, Color fill)  {
		this.stroke = stroke;
		this.fill = fill;
		this.textColor = null;
	}
	
	public Colour(Color stroke, Color fill, Color textColor) {
		this.stroke = stroke;
		this.fill = fill;
		this.textColor = textColor;
	}

	public Color getStroke() {
		return stroke;
	}

	public void setStroke(Color stroke) {
		this.stroke = stroke;
	}

	public Color getFill() {
		return fill;
	}

	public void setFill(Color fill) {
		this.fill = fill;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public String toString() {
		return "Colour [stroke=" + stroke + ", fill=" + fill + ", text=" + textColor
				+ "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colour other = (Colour) obj;
		if (fill != other.fill)
			return false;
		if (stroke != other.stroke)
			return false;
		if (textColor != other.textColor)
			return false;
		return true;
	}
	
	
	
	
}
