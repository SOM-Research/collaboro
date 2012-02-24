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

package fr.inria.atlanmod.collaboro.ui;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.Proposal;

public abstract class DecisionEngine {
	abstract public boolean resolveProposal(History history, Proposal proposal);
	abstract public boolean resolveSolution(History history, Proposal proposal);
	
	public static DecisionEngine INSTANCE = new TotalAgreementDecisionEngine();
	
}
