angular.module('collaboroControllers').controller('AbstractSyntaxController', ['$scope', '$http', 'syntaxService', 
	function($scope, $http, syntax) {
		$scope.abstractSyntaxImage = "assets/img/loading.gif";
		$scope.totalAbstractSyntaxImages = 1;
		$scope.currentAbstractSyntaxIndex = -1;

		syntax.getTotalAbstractSyntaxImages(
			function(response) {
				$scope.totalAbstractSyntaxImages = response.data.numImages;
			}
		);

		$scope.nextAbstractSyntaxImage = function() {
			var nextImageIndex = 0;
			if($scope.currentAbstractSyntaxIndex + 1 < $scope.totalAbstractSyntaxImages) {
				nextImageIndex = $scope.currentAbstractSyntaxIndex + 1;
				$scope.abstractSyntaxImage="assets/img/loading.gif";
				syntax.getAbstractSyntaxImage(nextImageIndex,
					function(response) {
						$scope.abstractSyntaxImage = "data:image/jpg;base64," + response.data;
						$scope.currentAbstractSyntaxIndex = nextImageIndex;
					}
				);
			}

		};

		$scope.nextAbstractSyntaxImage();

		$scope.previousAbstractSyntaxImage = function() {
			var previousImageIndex = $scope.totalAbstractSyntaxImages - 1;
			if($scope.currentAbstractSyntaxIndex - 1 >= 0) {
				previousImageIndex = $scope.currentAbstractSyntaxIndex - 1;
				$scope.abstractSyntaxImage="assets/img/loading.gif";
				syntax.getAbstractSyntaxImage(previousImageIndex,
					function(response) {
						$scope.abstractSyntaxImage = "data:image/jpg;base64," + response.data;
						$scope.currentAbstractSyntaxIndex = previousImageIndex;
					}
				);
			}
		};
}]);