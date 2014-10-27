angular.module('collaboroServices').factory('syntaxService', ['$http', 
	function($http) {
		var service = {
      getTotalAbstractSyntaxImages : function(successFn, errorFn) {
        $http.get(collaboroServletURL + '/abstractSyntax').then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
			getAbstractSyntaxImage : function(numImage, successFn, errorFn) {
        $http.post(collaboroServletURL + '/abstractSyntax', { 'numImage' : numImage }).then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
      getTotalConcreteSyntaxImages : function(successFn, errorFn) {
        $http.get(collaboroServletURL + '/concreteSyntax').then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
      getConcreteSyntaxImage : function(numImage, successFn, errorFn) {
        $http.post(collaboroServletURL + '/concreteSyntax', { 'numImage' : numImage }).then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      }

		};
		return service;
	}
]);