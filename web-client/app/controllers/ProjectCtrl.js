angular.module('collaboroControllers').controller('ProjectCtrl', ['$scope', '$rootScope',
  function($scope, $rootScope) {

    $rootScope.$on("$routeChangeError", function () {
      console.log("failed to change routes");
    });

  }
]);