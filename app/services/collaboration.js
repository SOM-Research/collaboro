angular.module('collaboroServices').factory('collaboration', ['$location', '$modal', '$http',
	function($location, $modal, $http) {
		var collaborationDialog = null;

		function openCollaborationDialog(collaborationtoedit) {
			collaborationDialog = $modal.open(
				{
					templateUrl : 'app/partials/collaboration/form.tpl.html',
					controller : function($scope, $modalInstance) {
            //$scope.range=[{value:'One', text:'First!'},{value:'Two', text:'Second!'},{value:'Three', text:'Third!'}];
            //$scope.list_of_string = [];

            // Setting the title of the window
            $scope.dialogtitle = 'New Collaboration';
            if(collaborationtoedit) {
              $scope.newcollaboration = collaborationtoedit;
              $scope.newcollaboration.data.referredElements = collaborationtoedit.data.referredElements;
              $scope.dialogtitle = 'Edit Collaboration';
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
			cancelCollaboration: function() {
  			closeCollaborationDialog(false);
  			//redirect();
  		},
  		saveCollaboration: function(newcollaboration) {
  			closeCollaborationDialog(newcollaboration);
  		},
  		editCollaboration: function(collaborationtoedit) {
  			return openCollaborationDialog(collaborationtoedit);
  		},
  		saveCollaborations: function(collaborations, successFn, errorFn) {
  			$http.post(collaboroServletURL + '/version', { action : "save", collaborations : collaborations }).then(
  				function(response) {
  					successFn(response);
  				},
  				function(response) {
  					errorFn(response);
  				}
  			);
  		},
  		deleteCollaboration: function(collaboration, successFn) {
  			$http.post(collaboroServletURL + '/version', { action : "delete", collaboration : collaboration }).then(
  				function(response) {
  					successFn(response);
  				}
  			);
  		},
  		voteCollaboration: function(collaboration, data, successFn) {
  			$http.post(collaboroServletURL + '/version', { action : "vote", collaboration : collaboration, data : data }).then(
  				function(response) {
  					successFn(response);
  				}
  			);
  		},
    };
		return service;
	}
]);
