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

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Vote;

public class TotalAgreementDecisionEngine extends DecisionEngine {
	
	public boolean resolveProposal(History history, Proposal proposal) {
		List<String> userNames = new ArrayList<String>();
		for(User user : history.getUsers()) {
			userNames.add(user.getId());
		}
		userNames.remove(proposal.getProposedBy().getId());
		
		boolean accepted = true;
		
		for(Vote vote : proposal.getVotes()) {
			userNames.remove(vote.getUser().getId());
			if(!vote.isAgreement()) {
				accepted = false;
				break;
			}
		}
		
		if((proposal.getVotes().size() == 0) || !accepted || userNames.size() > 0) {
			proposal.setAccepted(false);
			return false;
		} else {
			proposal.setAccepted(true);
			return true;
		}
	}

	public boolean resolveSolution(History history, Proposal proposal) {
		
		for(Solution solution : proposal.getSols()) {
			List<String> userNames = new ArrayList<String>();
			for(User user : history.getUsers()) {
				userNames.add(user.getId());
			}
			userNames.remove(solution.getProposedBy().getId());
			
			boolean accepted = true;
			
			for(Vote vote : solution.getVotes()) {
				userNames.remove(vote.getUser().getId());
				if(!vote.isAgreement()) {
					accepted = false;
					break;
				}
			}
			
			if(!((proposal.getVotes().size() == 0) || !accepted || userNames.size() > 0)) {
				proposal.setSelected(solution);
//				System.out.println("Solution " + solution.getId() + " found");
				break;
			} 
		}
		
		if(proposal.getSelected() == null) {
//			System.out.println("No solution found");
			return false;
		} else {
			return true;
		}
	}

}
