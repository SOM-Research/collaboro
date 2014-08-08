angular.module('collaboroServices').factory('collaboration', ['$location', '$modal', '$http', 
	function($location, $modal, $http) {
		var collaborationDialog = null;

		function openCollaborationDialog(collaborationtoedit) {				
			collaborationDialog = $modal.open(
				{
					templateUrl:'app/partials/collaboration/form.tpl.html',
					controller: 
						function($scope, $modalInstance) {
							$scope.add = function(result) {
            					$modalInstance.close(result);
        					};
							$scope.dialogtitle = 'New Collaboration';
        					 
        					if(collaborationtoedit) {
        						$scope.newcollaboration = collaborationtoedit;
        						$scope.dialogtitle = 'Edit Collaboration';
        					}

        					$scope.cancel = function() {
            					$modalInstance.dismiss('closed');
        					}

                  $scope.list_of_string = [];
                  var requestReferredElements = $http.get(collaboroServletURL+'/availableElementsToRefer');
                  requestReferredElements.then
                  (
                    function(response)
                    {
                      angular.extend($scope.select2Options.tags,response.data);
                    }
              );

              $scope.range=[{value:'One', text:'First!'},{value:'Two', text:'Second!'},{value:'Three', text:'Third!'}];
              $scope.select2Options =
              {
                'allowClear':true,
                'multiple': true,
                'simple_tags': true,
                'tags': [],
                initSelection : function (element, callback)
                {
                  callback($(element).data('$ngModelController').$modelValue);
                }
              };
              //if(collaborationtoedit)
              //{
                //$scope.select2Options.initSelection();
              //}
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