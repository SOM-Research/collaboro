angular.module('collaboroServices').factory('collaboration', ['$location', '$modal', '$http',
	function($location, $modal, $http) {
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

		var service = {
			showCollaboration : function() {
				return openCollaborationDialog();
			},
      editCollaboration : function(collaborationtoedit) {
        return openCollaborationDialog(collaborationtoedit);
      },
			cancelCollaboration: function() {
  			closeCollaborationDialog(false);
  		},
  		saveCollaboration: function(collaboration, successFn, errorFn) {
  			$http.post(collaboroServletURL + '/collaboration', { action : "save", collaboration : collaboration }).then(
  				function(response) {
  					successFn(response);
  				},
  				function(response) {
  					errorFn(response);
  				}
  			);
  		},
  		deleteCollaboration: function(collaboration, successFn) {
  			$http.post(collaboroServletURL + '/collaboration', { action : "delete", collaboration : collaboration }).then(
  				function(response) {
  					successFn(response);
  				}
  			);
  		},
  		voteCollaboration: function(collaboration, data, successFn) {
  			$http.post(collaboroServletURL + '/collaboration', { action : "vote", collaboration : collaboration, data : data }).then(
  				function(response) {
  					successFn(response);
  				}
  			);
  		},
    };
		return service;
	}
]);
