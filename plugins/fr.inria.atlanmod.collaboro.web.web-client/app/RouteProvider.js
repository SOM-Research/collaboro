angular.module('collaboroApp').config(['$routeProvider','$httpProvider',function($routeProvider, $httpProvider)
{
	//$routeProvider.when('/',{templateUrl: '/app/partials/main.html', controller : "LoginFormController"}).otherwise({redirectTo : "/"});
	$routeProvider.when('/',{templateUrl: 'app/partials/main.html', controller : "LoginFormController"}).when('/project', {templateUrl: 'app/partials/project.html', controller : "LoginFormController"}).when('/register', {templateUrl: 'app/partials/register.html', controller : "LoginFormController"}).otherwise({redirectTo : "/"});
	
	//$routeProvider.when('/',{templateUrl: 'app/partials/main.html', controller : "LoginFormController"}).when('/project', {resolve:[security,function requireAdminUser(security){var promise = service.requestCurrentUser(); return promise.then(function(currentUser){if(true){return true;}})}]}  ,{templateUrl: 'app/partials/project.html', controller : "LoginFormController"}).when('/register', {templateUrl: 'app/partials/register.html', controller : "LoginFormController"}).otherwise({redirectTo : "/"});
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
    $httpProvider.defaults.useXDomain = true;
}]);