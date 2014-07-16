angular.module('collaboroControllers').controller('imagesCtrl', ['$scope',function($scope){
  $scope.myInterval = 5000;
  //var slides= $scope.slides  =[];
  var slides = $scope.slides = [{image:'http://www.eclipse.org/atl/usecases/MMIndApproachtoDiffRep/img/extendedUmlMetamodel.png', text:'Notation example 0'}];
  $scope.addSlide = function() {
   //var newWidth = 600 + slides.length;
   var newWidth = 600;
   slides.push({
    image: 'http://placekitten.com/' + newWidth + '/300',
    text: ['More','Extra','Lots of','Surplus'][slides.length % 4] + ' ' +
    ['Cats', 'Kittys', 'Felines', 'Cutes'][slides.length % 4]
  });
 };
 for (var i=0; i<4; i++)
 {
  //$scope.addSlide();
 }

 //$scope.sportImages = ['http://placekitten.com/600/300','http://placekitten.com/601/300','http://placekitten.com/602/300'];
 //$scope.sportImages = ['http://placekitten.com/604/300','http://placekitten.com/601/300','http://placekitten.com/602/300'];

}]);