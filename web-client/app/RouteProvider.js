angular.module('collaboroApp').config(['$routeProvider','$httpProvider',
    function($routeProvider, $httpProvider) {
      $routeProvider.when('/',
        {
          templateUrl :  'app/partials/main.html',
          controller : "LoginFormController"
        }
      /*).when('/project',
        {
          templateUrl: 'app/partials/project.html',
          controller : "ProjectCtrl",
          resolve : securityAuthorizationProvider.requireAuthenticatedUser
        }*/
      ).when('/project',
        {
          templateUrl: 'app/partials/project.html',
          controller : "ProjectCtrl",
          resolve : {
            accept : function(security) {
              return security.requestCurrentUser();
            }
          }
        }
      ).otherwise(
        {
          redirectTo : "/"
        }
      );
      //$routeProvider.when('/',{templateUrl: 'app/partials/main.html', controller : "LoginFormController"}).when('/project', {resolve:[security,function requireAdminUser(security){var promise = service.requestCurrentUser(); return promise.then(function(currentUser){if(true){return true;}})}]}  ,{templateUrl: 'app/partials/project.html', controller : "LoginFormController"}).when('/register', {templateUrl: 'app/partials/register.html', controller : "LoginFormController"}).otherwise({redirectTo : "/"});
      //Following line added on 04/07/2014
      $httpProvider.defaults.withCredentials = true;
      delete $httpProvider.defaults.headers.common["X-Requested-With"];
      $httpProvider.defaults.useXDomain = true;
    }
  ]).run(["$rootScope", "$location", "security",
    function($rootScope, $location, security) {
      // register listener to watch route changes
      /*$rootScope.$on("$routeChangeStart",
        function(event, next, current) {
          if (!security.isAuthenticated()) {
            // no logged user, we should go the landing page
            $location.path("/");
          }
        });*/
      $rootScope.$on("$routeChangeError",
        function(event, next, current) {
          console.log("route change error");
          $location.path( "/" );
        });
    }]);