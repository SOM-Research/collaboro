
angular.module('security.login.form', []).controller('LoginFormController', ['$scope', 'security', '$cookieStore', '$cookies', 'md5',
  function($scope, security, $cookieStore, $cookies, md5) {
    $scope.user = {};

    // Any error message from failing to login
    $scope.authError = null;

    $scope.isAuthenticated = security.isAuthenticated;
    $scope.logout = security.logout;

    $scope.$watch(
      function() {
        return security.currentUser;
      }, 
      function(currentUser) {
        $scope.currentUser = currentUser;
      }
    );
   
    $scope.showLogin = function() {
      $scope.openLoginDialog = security.showLogin();
    };

    $scope.login = function() {
      $scope.authError = null;

      // Try to login
      security.login($scope.user.email, md5.createHash($scope.user.password), $scope.user.dsl).then(
        function(loggedIn) {
          if(!loggedIn) {
            $scope.authError = 'Invalid Credentials';
          }
        },
        function(x) {
          $scope.authError = 'Server error';
        }
      );
    };

    $scope.clearForm = function() {
      $scope.user = {};
    };

    $scope.cancelLogin = function() {
      security.cancelLogin();
    };


  }
]);