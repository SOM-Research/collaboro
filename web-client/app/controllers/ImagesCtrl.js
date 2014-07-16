angular.module('collaboroControllers').controller('imagesCtrl', ['$scope',function($scope){
  $scope.myInterval = -1;
  //var slides= $scope.slides  =[];
  var slides = $scope.slides = [{image:'https://cloud.githubusercontent.com/assets/1996760/3596028/92b81bde-0cbe-11e4-9f3d-3e72e54655da.png', text:'Notation example 0'},{image:'https://cloud.githubusercontent.com/assets/1996760/3596026/9298408e-0cbe-11e4-8cb7-8b40d6aa6361.png', text:'Notation example 1'},{image:'https://cloud.githubusercontent.com/assets/1996760/3596027/92ab5980-0cbe-11e4-88c8-8931bd265b63.png', text:'Notation example 2'}];
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