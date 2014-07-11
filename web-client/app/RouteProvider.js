angular.module('collaboroApp').config(['$routeProvider','$httpProvider', 'securityAuthorizationProvider',function($routeProvider, $httpProvider, securityAuthorizationProvider)//Le puse security que antes no lo tenia.
{
	//$routeProvider.when('/',{templateUrl: '/app/partials/main.html', controller : "LoginFormController"}).otherwise({redirectTo : "/"}).
	$routeProvider.when('/',{templateUrl: 'app/partials/main.html', controller : "LoginFormController"})
	//$routeProvider.when('/project',
	//	{resolve:[security,function requireLogedInUser(security)
	//		{
	//			var promise = service.requestCurrentUser();
	//		 	return promise.then(
	//		 		function(currentUser)
	//		 		{
	//		 			if(currentUser)
	//		 			{
	//		 				return $q.reject();
	//		 			}
	//		 			return currentUser;
	//		 		});
	//		}]
	//	}
	//);
	.when('/register', {templateUrl: 'app/partials/register.html', controller : "LoginFormController"})
	.when('/project',{templateUrl: 'app/partials/project.html', controller : "LoginFormController", resolve : securityAuthorizationProvider.requireAuthenticatedUser})
	//commented 05/07/14
	//.when('/project', {templateUrl: 'app/partials/project.html', controller : "LoginFormController", resolve : { theuser : [security, function requireLogedInUser(security)
	//		{
	//			var promise = security.requestCurrentUser();
	//		 	return promise.then(
	//		 		function(currentUser)
	//		 		{
	//		 			if(currentUser)
	//		 			{
	//		 				return $q.reject();
	//		 			}
	//		 			return currentUser;
	//		 		});
	//		}]}})
	.otherwise({redirectTo : "/"});

	
	//$routeProvider.when('/',{templateUrl: 'app/partials/main.html', controller : "LoginFormController"}).when('/project', {resolve:[security,function requireAdminUser(security){var promise = service.requestCurrentUser(); return promise.then(function(currentUser){if(true){return true;}})}]}  ,{templateUrl: 'app/partials/project.html', controller : "LoginFormController"}).when('/register', {templateUrl: 'app/partials/register.html', controller : "LoginFormController"}).otherwise({redirectTo : "/"});
	//Following line added on 04/07/2014
	$httpProvider.defaults.withCredentials = true;
	delete $httpProvider.defaults.headers.common["X-Requested-With"];
    $httpProvider.defaults.useXDomain = true;
}]);