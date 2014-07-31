angular.module('collaboroControllers').controller('metamodelAndModelsCtrl', ['$scope', '$http',
	function($scope, $http) {
		$scope.metamodelImage = "assets/img/loading.gif";
		$scope.numImages = 1;
		$scope.currentImageIndex = -1;

		$scope.updateNumImages = function() {
			$http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel').then(
				function(response) {
					$scope.numImages = response.data.numImages;
				}
			);
		};

		$scope.updateNumImages();

		$scope.nextMetamodeImage = function() {
			var nextImageIndex = 0;
			if($scope.currentImageIndex + 1 < $scope.numImages) {
				nextImageIndex = $scope.currentImageIndex + 1;
				$scope.metamodelImage="assets/img/loading.gif";
				$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel', { 'numImage' : nextImageIndex }).then(
					function(response) {
						$scope.metamodelImage = "data:image/jpg;base64," + response.data;
						$scope.currentImageIndex = nextImageIndex;
					}
				);
			}

		};

		$scope.nextMetamodeImage();

		$scope.previousMetamodeImage = function() {
			var previousImageIndex = $scope.numImages - 1;
			if($scope.currentImageIndex - 1 >= 0) {
				previousImageIndex = $scope.currentImageIndex - 1;
				$scope.metamodelImage="assets/img/loading.gif";
				$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel', { 'numImage' : previousImageIndex }).then(
					function(response) {
						$scope.metamodelImage = "data:image/jpg;base64," + response.data;
						$scope.currentImageIndex = previousImageIndex;
					}
				);
			}
		};
}]);