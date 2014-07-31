angular.module('collaboroControllers').controller('imagesCtrl', ['$scope', '$http',
  function($scope, $http) {
    $scope.modelImage = "assets/img/loading.gif";
    $scope.numModelImages = 1;
    $scope.currentNotationImageIndex = -1;

    $http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/notations').then(
      function(response) {
        $scope.numModelImages = response.data.numImages;
        console.log('num models is' + $scope.numModelImages);
      }
    );

    $scope.nextModelImage = function() {
      if($scope.currentNotationImageIndex + 1 < $scope.numModelImages) {
        var nextImageIndex = $scope.currentNotationImageIndex+1;
        $scope.modelImage = "assets/img/loading.gif";
        $http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/notations', { 'numImage': nextImageIndex }).then(
          function(response) {
            $scope.modelImage = "data:image/jpg;base64," + response.data;
            $scope.currentNotationImageIndex = nextImageIndex;
          }
        );
      }
    };
    $scope.nextModelImage();

    $scope.previousModelImage = function() {
      if($scope.currentNotationImageIndex - 1 >= 0) {
        var previousImageIndex = $scope.currentNotationImageIndex - 1;
        $scope.modelImage = "assets/img/loading.gif";
        $http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/notations', { 'numImage': previousImageIndex }).then(
            function(response) {
              $scope.modelImage = "data:image/jpg;base64," + response.data;
              $scope.currentNotationImageIndex = previousImageIndex;
            }
          );
      }
    };  
}]);