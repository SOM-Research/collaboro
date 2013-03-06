/*******************************************************************************
 * Copyright (c) 2008, 2012
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (javier.canovas@inria.fr) 
 *******************************************************************************/

package fr.inria.atlanmod.collaboro.ui.views;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Composite;

public class SyntaxElementPropertyDescriptor extends PropertyDescriptor {

	public static class MyEDataTypeCellEditor extends TextCellEditor {
		protected EDataType eDataType;
		protected EDataTypeValueHandler valueHandler;  

		public MyEDataTypeCellEditor(EDataType eDataType, Composite parent) {
			super(parent);
			this.eDataType = eDataType;
			valueHandler = new EDataTypeValueHandler(eDataType);
			setValidator(valueHandler);
		}

		@Override
		public Object doGetValue() {
			return valueHandler.toValue((String)super.doGetValue());
		}

		@Override
		public void doSetValue(Object value) {
			value = valueHandler.toString(getRealValue(value));
			super.doSetValue(value);
		}

		protected boolean validateAsValue = true;

		@Override
		protected void editOccured(ModifyEvent e) {
			validateAsValue = false;
			super.editOccured(e);
			validateAsValue = true;
		}

		@Override
		protected boolean isCorrect(Object value) {
			if (validateAsValue) {
				value = valueHandler.toString(getRealValue(value));
			}
			return super.isCorrect(getRealValue(value));
		}
		
		private String getRealValue(Object value) {
			if (value instanceof PropertyValueWrapper) {
				PropertyValueWrapper wrapper = (PropertyValueWrapper) value;
				return wrapper.getText(wrapper);
			}
			return "";
		}
	}

	public SyntaxElementPropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor) {
		super(object, itemPropertyDescriptor);
	}

	protected CellEditor createEDataTypeCellEditor(final EDataType eDataType, Composite composite)
	{
		if (itemPropertyDescriptor.isMultiLine(object))
			return super.createEDataTypeCellEditor(eDataType, composite);
		return new MyEDataTypeCellEditor(eDataType, composite);
	}
}
