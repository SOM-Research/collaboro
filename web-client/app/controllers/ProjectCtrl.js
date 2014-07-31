angular.module('collaboroControllers').controller('ProjectCtrl', ['$scope', '$rootScope', 'security', '$http',
  function($scope, $rootScope, security, $http) {
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

  	// First call to update the version
  	$scope.checkVersion();

    $scope.isAuthenticated = security.isAuthenticated;
  }
]);