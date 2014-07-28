angular.module('collaboroControllers').controller('collaborationsCtrl', ['$scope', 
  function($scope) {
    $scope.oneAtATime = false;

    $scope.groups = [
      {
        title: 'Proposed by',
        content: $scope.userNameSelected
      },
      {
        title: 'Rationale',
        content: $scope.versionSelectedDescription
      },
      {
        title: "Votes Agree",
        content: $scope.versionSelectedUsersAgree
      },
      {
        title: "Votes Disagree",
        content: $scope.versionSelectedUsersDisagree
      }
    ];

    $scope.addItem = function() {
      var newItemNo = $scope.items.length + 1;
      $scope.items.push('Item ' + newItemNo);
    };

  //This is the callback function for the tree selection
   $scope.$on('VersionSelectedEvent', function() {
      $scope.groups = [
        {
          title: 'Proposed by',
          content: $scope.userNameSelected
        },
        {
          title: 'Rationale',
          content: $scope.versionSelectedDescription
        },
        {
          title: "Votes Agree",
          content: $scope.versionSelectedUsersAgree
        },
        {
          title: "Votes Disagree",
          content: $scope.versionSelectedUsersDisagree
        }
      ];
  });

}]);