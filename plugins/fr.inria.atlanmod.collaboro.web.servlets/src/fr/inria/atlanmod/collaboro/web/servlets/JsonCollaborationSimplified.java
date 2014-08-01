/*******************************************************************************
 * Copyright (c) 2014 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Juan David Villa Calle - (juan-david.villa_calle@inria.fr)
 *******************************************************************************/
package fr.inria.atlanmod.collaboro.web.servlets;

/**
 * Simple object to desirialize a collaboration in json format
 * 
 * @author Juan David Villa Calle (juan-david.villa_calle@inria.fr)
 *
 */
public class JsonCollaborationSimplified {
	private String id;
	private String proposedBy;
	private String rationale;
	private String actions;
	private String type;
	private String parent_id;
	private String referredElements;
	
	
	
	public String getReferredElements() {
		return referredElements;
	}

	public void setReferredElements(String referredElements) {
		this.referredElements = referredElements;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProposedBy() {
		return proposedBy;
	}

	public void setProposedBy(String proposedBy) {
		this.proposedBy = proposedBy;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getType() {
		return type;
	}
	
	public String getParent_id() {
		return parent_id;
	}

	public void setType(String type) {
		this.type = type;
	}
}
