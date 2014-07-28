angular.module('collaboroControllers').controller('metamodelAndModelsCtrl', ['$scope', '$http',
	function($scope, $http)
	{
		$scope.metamodelImage="assets/img/loading.gif";
		var request=$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel',{metamodelname:'ModiscoWorkflow.ecore'});

		request.then(
					function(response)
					{
						$scope.metamodelImage= "data:image/jpg;base64," + response.data;
						//console.log(response);
					}
				);

   //$http.get('assets/models/models.json').success(function(data) {
    //$scope.models = data;
  //});
 }]);