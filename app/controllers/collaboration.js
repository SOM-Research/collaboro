angular.module('collaboroControllers').controller('collaborationController', ['$scope', 'History', 'collaboration', 'security',
  function($scope, History, collaboration, security) {
    // It is initialized by refreshCollaborations() function (see below)
    var tree;
    $scope.my_tree = tree = {};

    // The collaborations tree
    $scope.treeCollaborations = [];

    // The new collaborations to be sent to the server
    // (We keep different arrays due to different formats)
    $scope.newCollaborations = [];

    $scope.connError = "";

    // Adds a new collaboration
    $scope.addcollaboration = function() {
      collaboration.showCollaboration().result.then(
        function(result) {
          var newCollaboration = {
              "rationale": result.data['description'],
              "actions" : result.data['actions'],
              "type": result.data['type'],
              "proposedBy" : security.currentUser.firstName,
              "parent_id" : (tree.get_selected_branch == null || result.data['type'] == 'Proposal') ? null : tree.get_selected_branch().data['collaboration_id'],
              "referredElements" : result.data['referredElements']
          };
          console.log(newCollaboration);

          // We build the element for the tree
          result.data['username'] = security.currentUser.firstName;
          result.label = result.data['type'] + ' from ' + result.data['username'];

          $scope.newCollaborations.push(newCollaboration);
          $scope.add_collaboration(result);
        }).then(
        function(result) {
          $scope.savecollaborations();
        });
    };

    // Save (sends) the collaboration to the server
    $scope.savecollaborations = function() {
      collaboration.saveCollaborations($scope.newCollaborations,
        function(response) {
          $scope.newCollaborations = [];
          $scope.connError = "";
        },
        function(response) {
          $scope.newCollaborations = [];
          $scope.connError = "Error while saving";
        });
    }

    // Edit an existing collaboration
    $scope.editcollaboration = function() {
      collaboration.editCollaboration(tree.get_selected_branch()).result.then(
        function(result) {
          result.label = result.data['type'] +' from '+ result.data['username'];
        });
    }

    // Deletes an existing collaboration
    $scope.deletecollaboration = function() {
      var selectedElement = tree.get_selected_branch();
      if(selectedElement.data['id']) {
        collaboration.deleteCollaboration(tree.get_selected_branch(),
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
      History.query(
        function(history) {
          $scope.treeCollaborations = history;
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

    $scope.add_collaboration = function(newbranch) {
      var b;
      if(newbranch.data['type'] != 'Proposal')
        b = tree.get_selected_branch();
      else
        b = null; // Proposals are always in the root
      return tree.add_branch(b, newbranch);
    };

    $scope.collaborationSelected = function(branch) {
      var _ref;
      if ((_ref = branch.data) != null ? _ref.description : void 0) {
        $scope.versionSelected = branch.label;
        $scope.versionSelectedDescription = branch.data.description;
        $scope.versionSelectedUsersAgree = $scope.convert(branch.data.agree, 'agreement votes');
        $scope.versionSelectedUsersDisagree = $scope.convert(branch.data.disagree, 'disagreement votes');
        $scope.versionSelectedReferredElements = $scope.convert(branch.data.referredElements, 'referred elements');
        $scope.userNameSelected = branch.data.username;
        return $scope.output += '(' + branch.data.description + ')';
      }
    };

    $scope.vote = function(vote) {
      collaboration.voteCollaboration(tree.get_selected_branch(), { vote : vote },
        function(response) {
          $scope.versionSelectedUsersAgree = $scope.convert(response.data.data.agree, 'agreement votes');
          $scope.versionSelectedUsersDisagree = $scope.convert(response.data.data.disagree, 'disagreement votes');
          tree.get_selected_branch().data.agree = $scope.convert(response.data.data.agree, 'agreement votes');
          tree.get_selected_branch().data.disagree = $scope.convert(response.data.data.disagree, 'disagreement votes');
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
