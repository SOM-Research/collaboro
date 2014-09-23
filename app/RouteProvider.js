angular.module('collaboroApp').config(['$routeProvider','$httpProvider',
    function($routeProvider, $httpProvider) {
      $routeProvider.when('/',
        {
          templateUrl :  'app/partials/main.html',
          controller : "LoginController"
        }
      ).when('/project',
        {
          templateUrl: 'app/partials/project.html',
          controller : "ProjectController",
          resolve : {
            accept : function(securityService) {
              return securityService.requestCurrentUser();
            }
          }
        }
      ).otherwise(
        {
          redirectTo : "/"
        }
      );
      //Following line added on 04/07/2014
      $httpProvider.defaults.withCredentials = true;
      delete $httpProvider.defaults.headers.common["X-Requested-With"];
      $httpProvider.defaults.useXDomain = true;
    }
  ]).run(["$rootScope", "$location", "securityService",
    function($rootScope, $location, securityService) {
      // register listener to watch route changes
      /*$rootScope.$on("$routeChangeStart",
        function(event, next, current) {
          if (!securityService.isAuthenticated()) {
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