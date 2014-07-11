
//angular.module('collaboroControllers').controller('LoginFormController', ['$scope', 'security',
angular.module('security.login.form',[]).controller('LoginFormController', ['$scope', 'security','$cookieStore', '$cookies',
 function($scope,security,$cookieStore, $cookies)
 {

    $scope.user={};

    // Any error message from failing to login
    $scope.authError=null;

  // The reason that we are being asked to login - for instance because we tried to access something to which we are not authorized
  // We could do something diffent for each reason here but to keep it simple...
  $scope.authReason = null;

  if(security.getLoginReason())
  {
    $scope.authReason=(security.isAuthenticated())?
    'Not authorized':'Not authenticated';
  }


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
    $scope.authError = null;

    //Try to login
    security.login($scope.user.email, $scope.user.password).then(function(loggedIn){
      console.log('Cookies:');
      console.log($cookies);
      console.log('Cookie Store:');
      console.log($cookieStore);
      //I believe this cookie can not be obtained like this.
      //$cookies.get('JSESSIONID');
      //console.log($cookies.get('JSESSIONID'));
      if(!loggedIn)
      {
        $scope.authError = 'Invalid Credentials';
      }
    },function(x){
      $scope.authError = 'Server error';
    });


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