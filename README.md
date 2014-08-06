Collaboro
=========

What is this project about?
---------------------------

Collaboro is an approach to make language development processes more participative, meaning that both developers and users of the language can collaborate together to create and evolve it.

Collaboro has been developed as an Eclipse plugin, whose features currently implemented are:

* Version view to navigate through the Proposals of a version. For each Proposal, the solutions and comments are shown.
* Collaboration view to show the data related to a Collaboration selected in the version view. This view also shows the changes to apply if the selected element is a Solution.
* The user can login to the Collaboro system and create proposals, solutions and comments by right-clicking in the version view. The user can also vote for/against the collaborations.
* Decision engine based on a total agreement (i.e., all the community users must vote for the collaboration). The decision engine can be launch by using the bar menu.
* Notation engine and Notation view to render SVG snapshots of the DSL concrete syntax.

To test the plugin, you can import the Production System project, where you find the ecore file along with the history file which stores the collaboration. Once you open the ecore file, the Version, Collaboration and Notation views will be updated. 

We are also developing a web-enabled version of the tool that you can find in the web-support branch.

Papers
------

You can read the research paper titled Community-driven Language Development published at Models in Software Engineering Workshop at ICSE [here] (http://goo.gl/AAHyg)

You can also read the research paper titled Enabling the Collaborative Definition of DSMLs published at CAiSE conference [here] (http://goo.gl/i9vTS)

The Collaboro model of the Workflow case study can be downloaded from [here](https://github.com/jlcanovas/collaboro/blob/master/examples/fr.inria.atlanmod.collaboro.examples.workflow/model/ModiscoWorkflow.history)

The Notation model of the Workflow case study can be downloaded from [here](https://github.com/jlcanovas/collaboro/blob/master/examples/fr.inria.atlanmod.collaboro.examples.workflow/model/ModiscoWorkflow.notation)

Installation and Usage
----------------------
Requirements:

* JRE 1.6 or above
* Eclipse 3.6 or above
* EMF 3.7 or above

Steps:
* Download the update site file fr.inria.atlanmod.collaboro.zip from the Download section.
* Open the Eclipse IDE and click on Help-->Install New Software...
* Add a new software site by clicking on Add.
* Select the update site file downloaded previously and accept the form.
* Select the software site in the Work with field
* Install the two features that will appear in the list (Collaboro infrastructure and Collaboro UI). If they don’t appear, uncheck the option Group items by category.
* Restart Eclipse

Who is behind this project?
---------------------------
* [Javier Canovas](http://github.com/jlcanovas/ "Javier Canovas")
* [Jordi Cabot](http://github.com/jcabot/ "Jordi Cabot")
* [Juan David Villa](http://github.com/juandavidvillacalle/ "Juan David Villa")

Also participated:
* [Guillaume Doux](http://github.com/scheremele/ "Guillaume Doux")

Javier and Jordi work in [Atlanmod](http://www.emn.fr/z-info/atlanmod), a research team of Inria.
