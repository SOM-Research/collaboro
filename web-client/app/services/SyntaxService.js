angular.module('collaboroServices').factory('syntax', ['$http', 
	function($http) {
		var service = {
      getTotalAbstractSyntaxImages : function(successFn, errorFn) {
        $http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel').then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
			getAbstractSyntaxImage : function(numImage, successFn, errorFn) {
        $http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/renderMetamodel', { 'numImage' : numImage }).then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
      getTotalConcreteSyntaxImages : function(successFn, errorFn) {
        $http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/notations').then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      },
      getConcreteSyntaxImage : function(numImage, successFn, errorFn) {
        $http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/notations', { 'numImage' : numImage }).then(
          function(response) { successFn(response); }, 
          function(response) { errorFn(response); }
        );
      }

		};
		return service;
	}
]);