angular.module('collaboroServices').factory('History', ['$resource', 
	function($resource)
	{
		return $resource('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/version');
	}]);
