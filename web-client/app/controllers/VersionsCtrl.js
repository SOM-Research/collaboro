angular.module('collaboroControllers').controller('versionsCtrl', ['$scope','History','collaboration','security',
  function($scope,History, collaboration, security) {
    // The collaborations tree
    $scope.example_treedata =[];

    // The new collaborations to be sent to the server
    // (We keep different arrays due to different formats)
    $scope.new_collaborations =[];

    // Function associated with the cancel button in the collaboration dialog.
    $scope.cancelCollaboration = function() {
      collaboration.cancelCollaboration();
    };

    //Function associated with sending the new collaborations to the server.
    $scope.savecollaborations = function() {
      collaboration.saveCollaborations($scope.new_collaborations);
    }

    //Function associated to the Edit Collaboration button.
    $scope.editcollaboration = function() {
      collaboration.editCollaboration(tree.get_selected_branch()).result.then(
        function(result){
          result.label = result.data['type'] +' from '+ result.data['username'];
        });
    }

    $scope.showcollaboration = function() {
      collaboration.showCollaboration().result.then(
        function(result) {
          // We build the collaboration to be sent to the server
          var newCollaboration = {
              "rationale": result.data['description'],
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

          $scope.new_collaborations.push(newCollaboration);
          $scope.add_collaboration(result);
        });
    };


    var tree;

    History.query(function(history){$scope.example_treedata=history;});

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
        $scope.versionSelectedUsersAgree = branch.data.agree;
        $scope.versionSelectedUsersDisagree = branch.data.disagree;
        $scope.userNameSelected = branch.data.username;
        return $scope.output += '(' + branch.data.description + ')';
      }
    };

    $scope.try_changing_the_tree_data = function() {
       $scope.example_treedata = [ 
        {
          label: 'North America',
          children: [
            {
              label: 'Canada',
              children: ['Toronto', 'Vancouver']
            }, 
            {
              label: 'USA',
              children: ['New York', 'Los Angeles']
            }, 
            {
              label: 'Mexico',
              children: ['Mexico City', 'Guadalajara']
            }
          ]
        } 
      ];
    };

    $scope.my_tree = tree = {};

    return $scope.try_adding_a_branch = function() 
    {
      var b;
      b = tree.get_selected_branch();

      return tree.add_branch(b, {
        label: 'New Collaboration',
        data:
        {
          username: 'Insert username',
          description: 'Insert rationale',
          agree: 'Insert users who agree',
          disagree: 'No users who disagree'
        }
      });
    };

  }
]);