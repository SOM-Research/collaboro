angular.module('collaboroServices').factory('recommenderService', ['$http', '$q',
	function($http, $q) {
		var service = {
      queryRecommender : function() {
        var deferred = $q.defer();
        $http.get(collaboroServletURL + '/recommender')
          .success(function(data) {
            deferred.resolve(data);
          })
          .error(function(reason) {
            deferred.reject(reason);
          });
        return deferred.promise;
      },
      launchRecommender : function() {
        var deferred = $q.defer();
        $http.post(collaboroServletURL + '/recommender')
          .success(function(data) {
            deferred.resolve(data);
          })
          .error(function(reason) {
            deferred.reject(reason);
          });
        return deferred.promise;
      }
    };
		return service;
	}
]);
