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

package fr.inria.atlanmod.collaboro.backend;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.Vote;

/**
 * Decides proposals and solutions. To be accepted, there must be more positive votes than negative ones. Only
 * the emmited voted are counted (even if no every user has voted).
 */
public class SimpleAgreementDecisionEngine extends DecisionEngine {
	public boolean resolveProposal(History history, Proposal proposal) {
		boolean accepted = true;
		for(Vote vote : proposal.getVotes()) {
			if(!vote.isAgreement()) {
				accepted = false;
				break;
			}
		}
		
		if((proposal.getVotes().size() == 0) || !accepted) {
			proposal.setAccepted(false);
			return false;
		} else {
			proposal.setAccepted(true);
			return true;
		}
	}

	public boolean resolveSolution(History history, Proposal proposal) {
		for(Solution solution : proposal.getSols()) {
			boolean accepted = true;
			for(Vote vote : solution.getVotes()) {
				if(!vote.isAgreement()) {
					accepted = false;
					break;
				}
			}
			
			if(!((proposal.getVotes().size() == 0) || !accepted)) {
				proposal.setSelected(solution);
				break;
			} 
		}
		
		if(proposal.getSelected() == null) {
			return false;
		} else {
			return true;
		}
	}

}
