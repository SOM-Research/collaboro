angular.module('collaboroServices').factory('collaborationService', ['$location', '$modal', '$http', '$q',
	function($location, $modal, $http, $q) {
		var collaborationDialog = null;

		function openCollaborationDialog(collaborationtoedit) {
			collaborationDialog = $modal.open(
				{
					templateUrl : 'app/partials/modal/collaboration.html',
					controller : function($scope, $modalInstance) {

            // Setting the title of the window
            if(collaborationtoedit) {
              $scope.collaboration = collaborationtoedit;
              //$scope.collaboration.data.referredElements = collaborationtoedit.data.referredElements;
              $scope.dialogtitle = 'Edit Collaboration';
            } else {
              $scope.dialogtitle = 'New Collaboration';
            };

            // Setting the referred elements
            var requestReferredElements = $http.get(collaboroServletURL+'/availableElementsToRefer');
              requestReferredElements.then(
                function(response){
                  angular.extend($scope.select2Options.tags, response.data);
                }
            );

            $scope.select2Options =
              {
                'allowClear' : true,
                'multiple' : true,
                'simple_tags': true,
                'tags': [],
                initSelection : function (element, callback) {
                  callback($(element).data('$ngModelController').$modelValue);
                }
              };

            // When clicking on ADD
						$scope.add = function(result) {
    					$modalInstance.close(result);
  					};

            // When clicking on CANCEL
  					$scope.cancel = function() {
    					$modalInstance.dismiss('closed');
  					}
				  }
				}
			);
		  return collaborationDialog;
    }

		function closeCollaborationDialog(success) {
			if(collaborationDialog) {
				collaborationDialog.close(success);
				collaborationDialog = null;
			}
		}

    function performCollaboration(action, collaboration, payload) {
      var data;
      if(payload) 
        data = { action: action, collaboration: collaboration, data: payload };
      else 
        data = { action: action, collaboration: collaboration};

      var deferred = $q.defer();
      $http.post(collaboroServletURL + '/collaboration', data)
        .success(function(data) {
          deferred.resolve(data);
        })
        .error(function(reason) {
          deferred.reject(reason);
        });
      return deferred.promise;
    }

		var service = {
      getCollaborations : function() {
        var deferred = $q.defer();
        $http.get(collaboroServletURL + '/collaboration')
          .success(function(data) {
            deferred.resolve(data);
          })
          .error(function(reason) {
            deferred.reject(reason);
          });
        return deferred.promise;
      },
			showCollaboration : function() {
				return openCollaborationDialog();
			},
      editCollaboration : function(collaborationtoedit) {
        return openCollaborationDialog(collaborationtoedit);
      },
			cancelCollaboration: function() {
  			closeCollaborationDialog(false);
  		},
  		saveCollaboration: function(collaboration) {
        return performCollaboration('save', collaboration);
  		},
      modifyCollaboration: function(collaboration) {
        return performCollaboration('edit', collaboration);
      },
  		deleteCollaboration: function(collaboration) {
        return performCollaboration('delete', collaboration);
  		},
  		voteCollaboration: function(collaboration, data) {
        return performCollaboration('vote', collaboration, data);
  		},
      sendDisagreement : function(collaboration, comment) {
        return performCollaboration('disagreementComment', collaboration, comment);
      }
    };
		return service;
	}
]);
