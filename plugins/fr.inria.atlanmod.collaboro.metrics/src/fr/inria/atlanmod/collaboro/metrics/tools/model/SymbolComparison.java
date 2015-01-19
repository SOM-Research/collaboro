package fr.inria.atlanmod.collaboro.metrics.tools.model;

import fr.inria.atlanmod.collaboro.metrics.model.Symbol;

public class SymbolComparison {
	
	private Symbol referenceSymbol;
	private Symbol compareSymbol;
	
	private boolean isShapeSame;
	private boolean isSizeSame;
	private boolean isPositionSame;
	private boolean isColourSame;
	
	public SymbolComparison(Symbol referenceSymbol, Symbol compareSymbol) {
		this.referenceSymbol = referenceSymbol;
		this.compareSymbol = compareSymbol;
	}

	public SymbolComparison(Symbol referenceSymbol, Symbol compareSymbol,
			boolean isShapeSame, boolean isSizeSame, boolean isPositionSame,
			boolean isColourSame) {
		this.referenceSymbol = referenceSymbol;
		this.compareSymbol = compareSymbol;
		this.isShapeSame = isShapeSame;
		this.isSizeSame = isSizeSame;
		this.isPositionSame = isPositionSame;
		this.isColourSame = isColourSame;
	}

	public Symbol getReferenceSymbol() {
		return referenceSymbol;
	}

	public void setReferenceSymbol(Symbol referenceSymbol) {
		this.referenceSymbol = referenceSymbol;
	}

	public Symbol getCompareSymbol() {
		return compareSymbol;
	}

	public void setCompareSymbol(Symbol compareSymbol) {
		this.compareSymbol = compareSymbol;
	}

	public boolean isShapeSame() {
		return isShapeSame;
	}

	public void setShapeSame(boolean isShapeSame) {
		this.isShapeSame = isShapeSame;
	}

	public boolean isSizeSame() {
		return isSizeSame;
	}

	public void setSizeSame(boolean isSizeSame) {
		this.isSizeSame = isSizeSame;
	}

	public boolean isPositionSame() {
		return isPositionSame;
	}

	public void setPositionSame(boolean isPositionSame) {
		this.isPositionSame = isPositionSame;
	}

	public boolean isColourSame() {
		return isColourSame;
	}

	public void setColourSame(boolean isColourSame) {
		this.isColourSame = isColourSame;
	}

}
