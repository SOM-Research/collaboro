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
      		saveCollaborations: function(collaborations, successFn) {
      			$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/version', { action : "save", collaborations : collaborations }).then(
      				function(response) {
      					successFn(response);
      				}
      			);
      		},
      		deleteCollaboration: function(collaboration, successFn) {
      			$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/version', { action : "delete", collaboration : collaboration }).then(
      				function(response) {
      					successFn(response);
      				}
      			);
      		},
      		voteCollaboration: function(collaboration, data, successFn) {
      			$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/version', { action : "vote", collaboration : collaboration, data : data }).then(
      				function(response) {
      					successFn(response);
      				}
      			);
      		},
		};
		return service;
	}
]);