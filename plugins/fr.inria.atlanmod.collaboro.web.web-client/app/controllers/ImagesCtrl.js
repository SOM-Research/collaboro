angular.module('collaboroControllers').controller('imagesCtrl', ['$scope',function($scope){
  $scope.myInterval = -1;
  var slides= $scope.slides  =[];
  //var slides = $scope.slides = [{image:'http://www.eclipse.org/atl/usecases/MMIndApproachtoDiffRep/img/extendedUmlMetamodel.png', text:'Notation example 0'}];
  $scope.addSlide = function() {
   var newWidth = 600 + slides.length;
   slides.push({
    image: 'http://placekitten.com/' + newWidth + '/300',
    text: ['More','Extra','Lots of','Surplus'][slides.length % 4] + ' ' +
    ['Cats', 'Kittys', 'Felines', 'Cutes'][slides.length % 4]
  });
 };
 for (var i=0; i<4; i++)
 {
  $scope.addSlide();
}
}]);