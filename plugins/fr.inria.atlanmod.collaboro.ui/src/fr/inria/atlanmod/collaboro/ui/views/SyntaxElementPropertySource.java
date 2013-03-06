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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import fr.inria.atlanmod.collaboro.history.SyntaxElement;

public class SyntaxElementPropertySource extends PropertySource {
	private SyntaxElement parent;
	
	public SyntaxElementPropertySource(Object object, SyntaxElement parent, IItemPropertySource itemPropertySource) {
		super(object, itemPropertySource);
		this.parent = parent;
	}

	public Object getObject() {
		return this.object;
	}

	public IItemPropertySource getIItemPropertySource() {
		return this.itemPropertySource;
	}

	public IPropertyDescriptor [] getPropertyDescriptors() {
		Collection<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
		List<IItemPropertyDescriptor> itemPropertyDescriptors = itemPropertySource.getPropertyDescriptors(object);
		if (itemPropertyDescriptors != null) {
			for (IItemPropertyDescriptor itemPropertyDescriptor : itemPropertyDescriptors) {
				result.add(createPropertyDescriptor(itemPropertyDescriptor));
			}
		}

		return result.toArray(new IPropertyDescriptor [result.size()]);
	}

	protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor) {
		return new SyntaxElementPropertyDescriptor(object, itemPropertyDescriptor);
	}

	public SyntaxElement getParent() {
		return parent;
	}
}
