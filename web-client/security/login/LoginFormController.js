
angular.module('collaboroControllers').controller('LoginFormController', ['$scope', 'security',
 function($scope,security)
 {

    $scope.user={};

    // Any error message from failing to login
    $scope.authError=null;

  // The reason that we are being asked to login - for instance because we tried to access something to which we are not authorized
  // We could do something diffent for each reason here but to keep it simple...
  $scope.authReason = null;


  $scope.isAuthenticated = security.isAuthenticated;
  $scope.logout = security.logout;

  $scope.$watch(function() {
        return security.currentUser;
      }, function(currentUser) {
        $scope.currentUser = currentUser;
      });
   
  $scope.showLogin =function()
  {

       $scope.openLoginDialog=security.showLogin();

      //security
  };

  $scope.login=function()
  {
    security.login($scope.user.email, $scope.user.password);


  };

  $scope.clearForm = function()
  {
     $scope.user = {};
  };

  $scope.cancelLogin = function() 
  {
    security.cancelLogin();
  };



 }
]);