angular.module('collaboroControllers').controller('collaborationController', ['$scope', 'collaborationService', 'securityService',
  function($scope, collaborationService, securityService) {
    // It is initialized by refreshCollaborations() function (see below)
    $scope.collaborationTreeControl = {};

    // The collaborations tree
    $scope.collaborationTreeData = [];

    // Error messages
    $scope.connError = "";

    // Control showing the text area to write the comment related to a disagreement vote
    $scope.disagreement = false;

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

          // We send the new collaboration to the server and then update the tree
          collaborationService.saveCollaboration(newCollaboration).then(
            function(response) {
              var parentCollaboration = (response.data['type'] != 'Proposal') ? $scope.collaborationTreeControl.get_selected_branch() : null;
              $scope.collaborationTreeControl.add_branch(parentCollaboration, response);
              $scope.connError = "";
            },
            function(response) {
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
              $scope.collaborationSelected(response);
              $scope.connError = "";
            },
            function(response) {
              $scope.connError = "Error while saving";
            }
          );

        });
    }

    // Deletes an existing collaboration
    $scope.deletecollaboration = function() {
      var selectedElement = $scope.collaborationTreeControl.get_selected_branch();
      if(selectedElement.data['id']) {
        collaborationService.deleteCollaboration($scope.collaborationTreeControl.get_selected_branch()).then(
          function(result) {
            $scope.collaborationTreeControl.remove_branch($scope.collaborationTreeControl.get_selected_branch());
          }
        );
      }
    }

    // Refresh the tree
    $scope.refreshcollaborations = function() {
      collaborationService.getCollaborations().then(
        function(response) {
          $scope.collaborationTreeData = response;
          $scope.collaborationTreeControl.expand_all();
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
        $scope.collaborationSelectedProposedBy = element.data.username;
        $scope.collaborationSelectedRationale = element.data.rationale;
        $scope.collaborationSelectedReferredElements = $scope.convert(element.data.referredElements, 'referred elements');
        $scope.collaborationSelectedAgreeVotes = $scope.convert(element.data.agree, 'agreement votes');
        $scope.collaborationSelectedDisagreeVotes = $scope.convert(element.data.disagree, 'disagreement votes');
      }
    };

    $scope.hasVotedAgree = function() {
      var builtName = securityService.currentUser.firstName + " " + securityService.currentUser.lastName;
      return $scope.collaborationSelectedAgreeVotes != undefined && $scope.collaborationSelectedAgreeVotes.indexOf(builtName) > -1
    };


    $scope.vote = function(vote) {
      if($scope.collaborationTreeControl.get_selected_branch()) {
        if(vote == 'no') $scope.disagreement = true;
        else $scope.disagreement = false;

        collaborationService.voteCollaboration($scope.collaborationTreeControl.get_selected_branch(), { vote : vote }).then(
          function(response) {
            var agreeConverted = $scope.convert(response.data.agree, 'agreement votes');
            var disagreeConverted = $scope.convert(response.data.disagree, 'disagreement votes');
            $scope.collaborationSelectedAgreeVotes = agreeConverted;
            $scope.collaborationSelectedDisagreeVotes = disagreeConverted;
            $scope.collaborationTreeControl.get_selected_branch().data.agree = response.data.agree;
            $scope.collaborationTreeControl.get_selected_branch().data.disagree = response.data.disagree;
        });
      }
    }

    $scope.disagreementComment = function() {
      collaborationService.sendDisagreement($scope.collaborationTreeControl.get_selected_branch(), { comment : $scope.disagreementRationale }).then(
        function(response) {
          var parentCollaboration = $scope.collaborationTreeControl.get_selected_branch();
          $scope.collaborationTreeControl.add_branch(parentCollaboration, response);
          $scope.disagreementRationale = "";
          $scope.disagreement = false;
        }
      );
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
