/*******************************************************************************
 * Copyright (c) 2008, 2013
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (javier.canovas@inria.fr) 
 *******************************************************************************/
package fr.inria.atlanmod.collaboro.ui.views;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.Vote;
import fr.inria.atlanmod.collaboro.ui.CollaboroPlugin;

/**
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
public class VersionViewLabelProvider extends LabelProvider {

	protected String imagePath(Object element) {
		if (element instanceof Proposal) {
			Proposal proposal = (Proposal) element;
			if(proposal.isAccepted()) {
				return "icons/proposalAccepted.png";
			} 
			return "icons/proposal.png";
		} else if (element instanceof Solution) {
			return "icons/solution.png";
		} else if (element instanceof Vote) {
			Vote vote = (Vote) element;
			if(vote.isAgreement()) {
				return "icons/positiveVote.png";
			} else {
				return "icons/negativeVote.png";
			}
		} else if (element instanceof Comment) {
			Comment comment = (Comment) element;
			if(comment.isIncluded()) {
				return "icons/commentAccepted.png";
			} 
			return "icons/comment.png";
		}  else if(element instanceof ExampleElement) {
			return "icons/example.png";
		}
		return null;

	}

	@Override
	public Image getImage(Object element) {
		return CollaboroPlugin.getImage(imagePath(element)).createImage();
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Proposal) {
			Proposal proposal = (Proposal) element;
			return "Proposal " + proposal.getId() + " from " + ((proposal.getProposedBy() != null) ? proposal.getProposedBy().getId() : "?");
		} else if (element instanceof Solution) {
			Solution solution = (Solution) element;
			return "Solution " + solution.getId() + " from " + ((solution.getProposedBy() != null) ? solution.getProposedBy().getId() : "?");
		} else if (element instanceof Comment) {
			Comment comment = (Comment) element;
			return "Comment " + comment.getId() + " from " + ((comment.getProposedBy() != null) ? comment.getProposedBy().getId() : "?");
		} else if (element instanceof ExampleElement) {
			ExampleElement example = (ExampleElement) element;
			return example.getLabel();
		}
		return super.getText(element);
	}

}
