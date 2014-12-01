angular.module('collaboroServices').factory('recommenderService', ['$http', '$q', '$modal',
  function($http, $q, $modal) {

    function openConfigDialog() {
      configDialog = $modal.open(
        {
          templateUrl : 'app/partials/modal/recommender.html',
          controller : function($scope, $modalInstance) {
            $scope.metrics = [];

            // Getting the metrics
            $scope.obtainMetrics = function() {
              performRecommendation('list').then(
                function(data) {
                  $scope.metrics = data.metrics;
                }
              );
            }

            // Updating
            $scope.obtainMetrics();

            $scope.status = function(metric) {
              if(metric.active)
                return "btn btn-success btn-xs";
              else 
                return "btn btn-danger btn-xs";
            }

            $scope.statusText = function(metric) {
              if(metric.active)
                return "Active";
              else 
                return "Deactivated";
            }

            $scope.toggleMetric = function(metric) {
              metric.active = !metric.active;
            }

            // When clicking on ADD
            $scope.ok = function() {
              $modalInstance.close();
            };

            // When clicking on CANCEL
            $scope.cancel = function() {
              $modalInstance.dismiss('closed');
            }
          }
        }
      );
      return configDialog;
    }

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
      statusRecommender : function() {
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
      configRecommender : function() {
        return openConfigDialog();
      },
    };
    return service;
  }
]);
