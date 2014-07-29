angular.module('collaboroControllers').controller('metamodelAndModelsCtrl', ['$scope', '$http',
	function($scope, $http)
	{
		$scope.metamodelImage="assets/img/loading.gif";
		$scope.numImages=1;
		$scope.currentImageIndex=0;

		var requestNumImages = $http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel');

		requestNumImages.then(
				function(response)
				{
					$scope.numImages = response.data.numImages;
					console.log('numImages is' + $scope.numImages);
				}

			);

		$scope.nextMetamodeImage = function()
		{
			var nextImageIndex=0;
			if($scope.currentImageIndex+1<$scope.numImages)
			{
				nextImageIndex = $scope.currentImageIndex+1;
			}
			$scope.metamodelImage="assets/img/loading.gif";
			var request=$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel',{'numImage': nextImageIndex});
			request.then
			    (
					function(response)
					{
						$scope.metamodelImage = "data:image/jpg;base64," + response.data;
						$scope.currentImageIndex = nextImageIndex;
						//console.log(response);
					}
				);

		};

		$scope.nextMetamodeImage();

		$scope.previousMetamodeImage = function()
		{
			var previousImageIndex=$scope.numImages-1;
			if($scope.currentImageIndex-1>=0)
			{
				previousImageIndex = $scope.currentImageIndex-1;
			}
			$scope.metamodelImage="assets/img/loading.gif";
			var request=$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel',{'numImage': previousImageIndex});
			request.then
			    (
					function(response)
					{
						$scope.metamodelImage = "data:image/jpg;base64," + response.data;
						$scope.currentImageIndex = previousImageIndex;
					}
				);

		};

 }]);