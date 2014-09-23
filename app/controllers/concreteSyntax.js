angular.module('collaboroControllers').controller('ConcreteSyntaxCtrl', ['$scope', '$http', 'syntaxService',
  function($scope, $http, syntax) {
    $scope.concreteSyntaxImage = "assets/img/loading.gif";
    $scope.totalConcreteSyntaxImages = 1;
    $scope.currentConcreteSyntaxIndex = -1;

    syntax.getTotalConcreteSyntaxImages(
      function(response) {
        $scope.totalConcreteSyntaxImages = response.data.numImages;
      }
    );

    $scope.nextConcreteSyntaxImage = function() {
      var nextImageIndex = 0;
      if($scope.currentConcreteSyntaxIndex + 1 < $scope.totalConcreteSyntaxImages) {
        nextImageIndex = $scope.currentConcreteSyntaxIndex + 1;
        $scope.concreteSyntaxImage="assets/img/loading.gif";
        syntax.getConcreteSyntaxImage(nextImageIndex,
          function(response) {
            $scope.concreteSyntaxImage = "data:image/jpg;base64," + response.data;
            $scope.currentConcreteSyntaxIndex = nextImageIndex;
          }
        );
      }

    };

    $scope.nextConcreteSyntaxImage();

    $scope.previousConcreteSyntaxImage = function() {
      var previousImageIndex = $scope.totalConcreteSyntaxImages - 1;
      if($scope.currentConcreteSyntaxIndex - 1 >= 0) {
        previousImageIndex = $scope.currentConcreteSyntaxIndex - 1;
        $scope.concreteSyntaxImage="assets/img/loading.gif";
        syntax.getConcreteSyntaxImage(previousImageIndex,
          function(response) {
            $scope.concreteSyntaxImage = "data:image/jpg;base64," + response.data;
            $scope.currentConcreteSyntaxIndex = previousImageIndex;
          }
        );
      }
    };
}]);