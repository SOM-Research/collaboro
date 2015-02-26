angular.module('collaboroServices').factory('recommenderService', ['$http', '$q', '$modal',
  function($http, $q, $modal) {

    function openConfigDialog() {
      configDialog = $modal.open(
        {
          templateUrl : 'app/partials/modal/recommender.html',
          controller : function($scope, $modalInstance) {
            $scope.metrics = [];
            $scope.changed = false;

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

            $scope.footerText = function(metric) {
              if(metric.totalBad > 0)
                return "<strong>" + metric.totalBad + "</strong> issue/s detected in the current version";
              else if (metric.totalBad == 0 && metric.totalGood > 0) 
                return "The current version fulfills the recommendations for this metric";
              else
                return "No issue/s detected in the current version";
            }

            $scope.footerStyle = function(metric) {
              if(metric.totalBad > 0)
                return "#EBCCD1";
              else if (metric.totalBad == 0 && metric.totalGood > 0) 
                return "#dff0d8";
              else
                return "#f5f5f5";
            }

            $scope.toggleMetric = function(metric) {
              $scope.changed = true;

              metric.active = !metric.active;
              var action = 'deactivate';

              if(metric.active) {
                action = 'activate';
              } 

              performRecommendation(action, { metricName : metric.name });
            }

            // When clicking on ADD
            $scope.ok = function() {
              $modalInstance.close({changed : $scope.changed });
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

    function performRecommendation(action, payload) {

      if(payload) 
        data = { action: action, data: payload };
      else 
        data = { action: action };

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
      activateMetric : function(metricName) {
        return performRecommendation('activate', { metricName : metricName })
      },
      deactivateMetric : function(metricName) {
        return performRecommendation('deactivate', { metricName : metricName })
      }
    };
    return service;
  }
]);
