
angular.module('collaboroControllers', []).controller('LoginFormController', ['$scope', 'securityService', '$cookieStore', '$cookies', 'md5', '$http',
  function($scope, securityService, $cookieStore, $cookies, md5, $http) {
    $scope.user = {};
    $scope.languages = []
    // Any error message from failing to login
    $scope.authError = null;

    $scope.isAuthenticated = securityService.isAuthenticated;
    $scope.logout = securityService.logout;

    $scope.$watch(
      function() {
        return securityService.currentUser;
      }, 
      function(currentUser) {
        $scope.currentUser = currentUser;
      }
    );
   
    $scope.showLogin = function() {
      $scope.openLoginDialog = securityService.showLogin();
    };

    $scope.login = function() {
      $scope.authError = null;

      // Try to login
      securityService.login($scope.user.email, md5.createHash($scope.user.password), $scope.user.dsl).then(
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
      securityService.cancelLogin();
    };

    // We get the languages to collaborate
    $scope.updateLanguages = function() {
      $http.get(collaboroServletURL + '/languages').then(
        function(response) {
          $scope.languages = response.data.languages;
        });
    }
    $scope.updateLanguages();


  }
]);