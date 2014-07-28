angular.module('collaboroControllers').controller('ProjectCtrl', ['$scope', '$rootScope', 'security',
  function($scope, $rootScope, security) {
    $scope.isAuthenticated = security.isAuthenticated;
  }
]);