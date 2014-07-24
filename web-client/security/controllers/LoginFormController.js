
angular.module('security.login.form', []).controller('LoginFormController', ['$scope', 'security', '$cookieStore', '$cookies', 'md5',
  function($scope, security, $cookieStore, $cookies, md5) {
    $scope.user = {};

    // Any error message from failing to login
    $scope.authError = null;

    // The reason that we are being asked to login - for instance because we tried to access something to which we are not authorized
    // We could do something diffent for each reason here but to keep it simple...
    $scope.authReason = null;

    $scope.isAuthenticated = security.isAuthenticated;
    $scope.logout = security.logout;

    if(security.getLoginReason()) {
      $scope.authReason = (security.isAuthenticated()) ? 'Not authorized' : 'Not authenticated';
    }

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

      //Try to login
      security.login($scope.user.email, md5.createHash($scope.user.password), $scope.user.dsl).then(
        function(loggedIn){
          console.log('Cookies:');
          console.log($cookies);
          console.log('Cookie Store:');
          console.log($cookieStore);

          //I believe this cookie can not be obtained like this.
          //$cookies.get('JSESSIONID');
          //console.log($cookies.get('JSESSIONID'));
          if(!loggedIn) {
            $scope.authError = 'Invalid Credentials';
          }
        },
        function(x){
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