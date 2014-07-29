angular.module('collaboroControllers').controller('versionsCtrl', ['$scope', 'History', 'collaboration', 'security',
  function($scope, History, collaboration, security) {
    // Tree tree 
    // It is initialized by refreshCollaborations() function (see below)
    var tree;
    $scope.my_tree = tree = {};

    // The collaborations tree
    $scope.treeCollaborations = [];

    // The new collaborations to be sent to the server
    // (We keep different arrays due to different formats)
    $scope.newCollaborations =[];

    // Adds a new collaboration
    $scope.addcollaboration = function() {
      collaboration.showCollaboration().result.then(
        function(result) {
          // We build the collaboration to be sent to the server
          var newCollaboration = {
              "rationale": result.data['description'],
              "actions" : result.data['actions'],
              "type": result.data['type'],
              "proposedBy" : security.currentUser.firstName
          };

          var c;
          if(newCollaboration.type != 'Proposal') {
            c = tree.get_selected_branch();
            result.data['parent_id'] = c.data['collaboration_id'];
            newCollaboration.parent_id = c.data['collaboration_id'];
          }

          // We build the element for the tree
          result.data['username'] = security.currentUser.firstName;
          result.label = result.data['type'] + ' from ' + result.data['username'];

          $scope.newCollaborations.push(newCollaboration);
          $scope.add_collaboration(result);
        });
    };

    // Save (sends) the collaboration to the server
    $scope.savecollaborations = function() {
      collaboration.saveCollaborations($scope.newCollaborations);
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

    // Tree initialization
    $scope.refreshcollaborations();

    $scope.add_collaboration = function(newbranch) {
      var b;
      b = tree.get_selected_branch();
      return tree.add_branch(b, newbranch);
    };
    
    $scope.my_tree_handler = function(branch) {
      var _ref;
      if ((_ref = branch.data) != null ? _ref.description : void 0) {
        $scope.versionSelected = branch.label;
        $scope.versionSelectedDescription = branch.data.description;
        $scope.showAction = (branch.data.type == 'Solution') ? true : false;
        $scope.versionSelectedActions = branch.data.actions;
        $scope.versionSelectedUsersAgree = branch.data.agree;
        $scope.versionSelectedUsersDisagree = branch.data.disagree;
        $scope.userNameSelected = branch.data.username;
        return $scope.output += '(' + branch.data.description + ')';
      }
    };

    $scope.vote = function(vote) {
      collaboration.voteCollaboration(tree.get_selected_branch(), { vote : vote }, 
        function(response) {
          $scope.versionSelectedUsersAgree = response.data.data.agree;
          $scope.versionSelectedUsersDisagree = response.data.data.disagree;
          $scope.refreshcollaborations();
      });
    }
  }
]);