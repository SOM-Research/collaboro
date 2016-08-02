Collaboro
=========

**This is the dev branch of collaboro, specially aimed at developing and testing new features**

What is this project about?
---------------------------

Collaboro is an approach to make language development processes more participative, meaning that both developers and users of the language can collaborate together to create and evolve it.

Collaboro has been developed both as an Eclipse plugin and as a web-application.

The Eclipse plugin offers the following features:

* Version view to navigate through the Proposals of a version. For each Proposal, the solutions and comments are shown.
* Collaboration view to show the data related to a Collaboration selected in the version view. This view also shows the changes to apply if the selected element is a Solution.
* The user can login to the Collaboro system and create proposals, solutions and comments by right-clicking in the version view. The user can also vote for/against the collaborations.
* Decision engine based on a total agreement (i.e., all the community users must vote for the collaboration). The decision engine can be launch by using the bar menu.
* Notation engine and Notation view to render SVG snapshots of the DSL concrete syntax.

The web-application is located at [http://atlanmod.github.io/collaboro](http://atlanmod.github.io/collaboro). If you want to initialize a new workspace for your language, just contact us.

Publications
------------

You can read the research paper titled Community-driven Language Development published at Models in Software Engineering Workshop at ICSE [here](http://goo.gl/AAHyg)

You can also read the research paper titled Enabling the Collaborative Definition of DSMLs published at CAiSE conference [here](http://goo.gl/i9vTS)

The Collaboro model of the Workflow case study can be downloaded from [here](https://github.com/jlcanovas/collaboro/blob/master/examples/fr.inria.atlanmod.collaboro.examples.workflow/model/ModiscoWorkflow.history)

The Notation model of the Workflow case study can be downloaded from [here](https://github.com/jlcanovas/collaboro/blob/master/examples/fr.inria.atlanmod.collaboro.examples.workflow/model/ModiscoWorkflow.notation)

Installation and Usage in Eclipse
---------------------------------
Requirements:

* JRE 1.6 or above
* Eclipse 3.6 or above
* EMF 3.7 or above

Steps:

* Download the update site file from [here](https://github.com/SOM-Research/collaboro/blob/751e33e323913feb6eed0126e52cd0cfe98d54c9/plugins/fr.inria.atlanmod.collaboro/fr.inria.atlanmod.collaboro.zip)
* Open the Eclipse IDE and click on "Help-->Install New Software"
* Add a new software site by clicking on "Add".
* Click on "Archiv" and select the update site file downloaded previously and accept the form.
* Select the software site in the "Work with" field.
* Install the two features that will appear in the list ("Collaboro infrastructure" and "Collaboro UI"). If they don’t appear, uncheck the option Group items by category.
* Restart Eclipse.

You can activate the Collaboro views/editors on "Window/Show View/Other..." and then select the views for Collaboro group.

You can import some of the example projects located at the "example" folder to play with the tool.

A graphical language example
--
You can find an example which illustrates the collaborative development of a graphical language [here](https://github.com/SOM-Research/collaboro/tree/dev/examples/fr.inria.atlanmod.collaboro.examples.baggageGraphical). To use the example, follow these steps:

* Download and import the project in your Eclipse
* Activate the Collaboro views (go to "Window/Show View/Other..." and then select the views for Collaboro group).
* Open the project and double-click on the file `baggage.ecore` located in the folder `model`. This will load the collaboration tasks and will update the Collaboro views.
* You can navigate through the collaboration elements
* To visualize a model example, open the file `example1.xmi`located in the folder `model`. Opening this file will show the model editor but also will trigger the on-the-fly rendered, which will show graphical concrete syntax in the Notation view. 

You can play modifying the collaboration (by using the Collaboro views), the notation definition (file `baggage.notation`) and the examples. 

If you find any problem or have any suggestion, do not hesitate to contact us.

Who is behind this project?
---------------------------
* [Javier Canovas](http://github.com/jlcanovas/ "Javier Canovas")
* [Jordi Cabot](http://github.com/jcabot/ "Jordi Cabot")

Javier and Jordi are currently members of [SOM](http://som.uoc.es), a research team of IN3-UOC. 

Also participated
* [Robin Boncorps](http://github.com/rboncorps/ "Robin Boncorps")
* [Juan David Villa Calle](https://github.com/juandavidvillacalle "Juan David Villa Calle")
* [Guillaume Doux](https://github.com/scheremele "Guillaume Doux")

