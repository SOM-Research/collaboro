angular.module('collaboroControllers').controller('collaborationController', ['$scope', 'collaborationService', 'security',
  function($scope, collaborationService, security) {
    // It is initialized by refreshCollaborations() function (see below)
    $scope.collaborationTreeControl = {};

    // The collaborations tree
    $scope.collaborationTreeData = [];

    // Error messages
    $scope.connError = "";

    // Adds a new collaboration
    $scope.addcollaboration = function() {
      collaborationService.showCollaboration().result.then(
        function(result) {
          var newCollaboration = {
              "type": result.data['type'],
              "rationale": result.data['rationale'],
              "referredElements" : result.data['referredElements'],
              "parent_id" : ($scope.collaborationTreeControl.get_selected_branch == null || result.data['type'] == 'Proposal') ? 
                              "" : 
                              $scope.collaborationTreeControl.get_selected_branch().data['id']
          };

          // We send the new collaboration to the server and the update the tree
          collaborationService.saveCollaboration(newCollaboration).then(
            function(response) {
              $scope.refreshcollaborations();
              $scope.connError = "";
            },
            function(response) {
              $scope.refreshcollaborations();
              $scope.connError = "Error while saving";
            }
          );
        });
    };

    // Edit an existing collaboration
    $scope.editcollaboration = function() {
      collaborationService.editCollaboration($scope.collaborationTreeControl.get_selected_branch()).result.then(
        function(result) {
          var newCollaboration = {
              "id" : result.data["id"],
              "type": result.data['type'],
              "rationale": result.data['rationale'],
              "referredElements" : result.data['referredElements'],
              "parent_id" : ($scope.collaborationTreeControl.get_selected_branch == null || result.data['type'] == 'Proposal') ? 
                              "" : 
                              $scope.collaborationTreeControl.get_selected_branch().data['id']
          };

          // We send the new collaboration to the server and the update the tree
          collaborationService.modifyCollaboration(newCollaboration).then(
            function(response) {
              $scope.refreshcollaborations();
              $scope.connError = "";
            },
            function(response) {
              $scope.refreshcollaborations();
              $scope.connError = "Error while saving";
            }
          );

          $scope.collaborationSelected(result);
        });
    }

    // Deletes an existing collaboration
    $scope.deletecollaboration = function() {
      var selectedElement = $scope.collaborationTreeControl.get_selected_branch();
      if(selectedElement.data['id']) {
        collaborationService.deleteCollaboration($scope.collaborationTreeControl.get_selected_branch()).then(
          function(result) {
            $scope.refreshcollaborations();
          }
        );
      } else {
        $scope.refreshcollaborations();
      }
    }

    // Refresh the tree
    $scope.refreshcollaborations = function() {
      collaborationService.getCollaborations().then(
        function(response) {
          $scope.collaborationTreeData = response;
        }, 
        function(reason) {
          $scope.connError = "Error while refreshing the collaboration treE: " + reason;
        }
      );
    }

    $scope.$on('dslVersionChanged', function() {
      $scope.refreshcollaborations();
    });

    $scope.$on('dslDecisionsMade', function() {
      $scope.refreshcollaborations();
    });

    // Tree initialization
    $scope.refreshcollaborations();

    /*$scope.$watch('collaborationTreeControl',
      function(newVal, oldVal) {
        console.log(newVal);
        console.log(oldVal);
      }
    );*/

    $scope.add_collaboration = function(newbranch) {
      var b;
      if(newbranch.data['type'] != 'Proposal')
        b = $scope.collaborationTreeControl.get_selected_branch();
      else
        b = null; // Proposals are always in the root
      return $scope.collaborationTreeControl.add_branch(b, newbranch);
    };

    $scope.collaborationSelected = function(element) {
      if (element.data) { 
        $scope.versionSelected = element.label;
        $scope.versionSelectedDescription = element.data.rationale;
        $scope.versionSelectedUsersAgree = $scope.convert(element.data.agree, 'agreement votes');
        $scope.versionSelectedUsersDisagree = $scope.convert(element.data.disagree, 'disagreement votes');
        $scope.versionSelectedReferredElements = $scope.convert(element.data.referredElements, 'referred elements');
        $scope.userNameSelected = element.data.username;
      }
    };

    $scope.vote = function(vote) {
      collaborationService.voteCollaboration($scope.collaborationTreeControl.get_selected_branch(), { vote : vote }).then(
        function(response) {
          var agreeConverted = $scope.convert(response.data.data.agree, 'agreement votes');
          var disagreeConverted = $scope.convert(response.data.data.disagree, 'disagreement votes');
          $scope.versionSelectedUsersAgree = agreeConverted;
          $scope.versionSelectedUsersDisagree = disagreeConverted;
          $scope.collaborationTreeControl.get_selected_branch().data.agree = response.data.data.agree;
          $scope.collaborationTreeControl.get_selected_branch().data.disagree = response.data.data.disagree;
          //$scope.refreshcollaborations();
      });
    }

    $scope.convert = function(jsonArray, topic) {
      var result = '';
      if(jsonArray && jsonArray.length > 0) {
        for (var i = 0; i < jsonArray.length; i++) {
          result = result + jsonArray[i] + ',';
        };
        result = result.substr(0, result.length - 1);
      } else {
        if(topic) result = 'No ' + topic + ' yet';
      }
      return result;
    }
  }
]);
