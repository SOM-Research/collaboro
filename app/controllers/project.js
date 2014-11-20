angular.module('collaboroControllers').controller('ProjectController', ['$scope', '$rootScope', 'securityService', 'recommenderService', '$http',
  function($scope, $rootScope, securityService, recommenderService, $http) {
  	$scope.dslVersion = "";

  	$scope.checkVersion = function() {
  		$http.get(collaboroServletURL + '/versionManagement').then(
  			function(response) {
  				$scope.dslVersion = response.data.version;
  			},
  			function(error) {
  				$scope.dslVersion = "Error";
  			});
  	};


  	$scope.nextVersion = function() {
  		$http.post(collaboroServletURL + '/versionManagement', { action : "next" } ).then(
  			function(response) {
  				$scope.dslVersion = response.data.version;
  				$scope.$broadcast('dslVersionChanged');
  			},
  			function(error) {
  				$scope.dslVersion = "Error";
  			});
  	};

  	$scope.createVersion = function() {
  		$http.post(collaboroServletURL + '/versionManagement', { action : "create" } ).then(
  			function(response) {
  				$scope.dslVersion = response.data.version;
  				$scope.$broadcast('dslVersionChanged');
  			},
  			function(error) {
  				$scope.dslVersion = "Error";
  			});

  	};

  	$scope.previousVersion = function() {
  		$http.post(collaboroServletURL + '/versionManagement', { action : "previous" } ).then(
  			function(response) {
  				$scope.dslVersion = response.data.version;
  				$scope.$broadcast('dslVersionChanged');
  			},
  			function(error) {
  				$scope.dslVersion = "Error";
  			});
  		
  	};

  	$scope.launchDecision = function() {
      $http.post(collaboroServletURL + '/decision').then(
        function(response) {
          $scope.$broadcast('dslDecisionsMade');
        });

  	};

    $scope.launchRecommender = function() {
      recommenderService.launchRecommender.then(
        $scope.$broadcast('dslRecommendationsMade');
      );
    }

  	// First call to update the version
  	$scope.checkVersion();

    $scope.isAuthenticated = securityService.isAuthenticated;
  }
]);