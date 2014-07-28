angular.module('collaboroControllers').controller('ProjectCtrl', ['$scope', '$rootScope', 'security', '$http',
  function($scope, $rootScope, security, $http) {
  	$scope.dslVersion = "";

  	$scope.checkVersion = function() {
  		$http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/versionManagement').then(
  			function(response) {
  				$scope.dslVersion = response.data.version;
  			},
  			function(error) {
  				$scope.dslVersion = "Error";
  			});
  	};


  	$scope.nextVersion = function() {
  		$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/versionManagement', { action : "next" } ).then(
  			function(response) {
  				$scope.dslVersion = response.data.version;
  				$scope.$broadcast('dslVersionChanged');
  			},
  			function(error) {
  				$scope.dslVersion = "Error";
  			});
  	};

  	$scope.createVersion = function() {
  		$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/versionManagement', { action : "create" } ).then(
  			function(response) {
  				$scope.dslVersion = response.data.version;
  				$scope.$broadcast('dslVersionChanged');
  			},
  			function(error) {
  				$scope.dslVersion = "Error";
  			});

  	};

  	$scope.previousVersion = function() {
  		$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/versionManagement', { action : "previous" } ).then(
  			function(response) {
  				$scope.dslVersion = response.data.version;
  				$scope.$broadcast('dslVersionChanged');
  			},
  			function(error) {
  				$scope.dslVersion = "Error";
  			});
  		
  	};

  	$scope.launchDecision = function() {


  	};

  	// First call to update the version
  	$scope.checkVersion();

    $scope.isAuthenticated = security.isAuthenticated;
  }
]);