package fr.inria.atlanmod.collaboro.ui;

import java.io.File;

import fr.inria.atlanmod.collaboro.history.Solution;

public class ExampleDrivenMediator {

	public void createChanges(Solution solution, File example) {
		// Testing code
		Controller.INSTANCE.createAdd(solution);
		Controller.INSTANCE.createAdd(solution);
	}
	
}
