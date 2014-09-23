angular.module('collaboroServices').factory('syntaxService', ['$http', 
	function($http) {
		var service = {
      getTotalAbstractSyntaxImages : function(successFn, errorFn) {
        $http.get(collaboroServletURL + '/renderMetamodel').then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
			getAbstractSyntaxImage : function(numImage, successFn, errorFn) {
        $http.post(collaboroServletURL + '/renderMetamodel', { 'numImage' : numImage }).then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
      getTotalConcreteSyntaxImages : function(successFn, errorFn) {
        $http.get(collaboroServletURL + '/notations').then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
      getConcreteSyntaxImage : function(numImage, successFn, errorFn) {
        $http.post(collaboroServletURL + '/notations', { 'numImage' : numImage }).then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      }

		};
		return service;
	}
]);