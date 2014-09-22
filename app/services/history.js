angular.module('collaboroServices').factory('History', ['$resource', 
	function($resource) {
		return $resource(collaboroServletURL + '/collaboration');
	}]);
