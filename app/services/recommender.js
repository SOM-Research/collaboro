angular.module('collaboroServices').factory('recommenderService', ['$http', '$q',
  function($http, $q) {

    function performRecommendation(action) {
      var data = { action: action };

      var deferred = $q.defer();
      $http.post(collaboroServletURL + '/recommender', data)
        .success(function(data) {
          deferred.resolve(data);
        })
        .error(function(reason) {
          deferred.reject(reason);
        });
      return deferred.promise;
    }
    
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
        return performRecommendation('launch');
      },
      obtainRecommendations : function() {
        return performRecommendation('list');
      },
    };
    return service;
  }
]);
