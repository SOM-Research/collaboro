angular.module('collaboroControllers').controller('imagesCtrl', ['$scope', '$http',
  function($scope, $http){

  $scope.modelImage="assets/imf/loading.gif";
  $scope.numModelImages=1;
  $scope.currentNotationImageIndex=0;

  var requestNumImages = $http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/notations');

    requestNumImages.then(
        function(response)
        {
          $scope.numModelImages = response.data.numImages;
          console.log('numImages is' + $scope.numModelImages);
        }

      );

    $scope.nextModelImage = function()
    {
      var nextImageIndex=0;
      if($scope.currentNotationImageIndex+1<$scope.numModelImages)
      {
        nextImageIndex = $scope.currentNotationImageIndex+1;
      }
      $scope.modelImage="assets/img/loading.gif";
      var request=$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/notations',{'numImage': nextImageIndex});
      request.then
          (
          function(response)
          {
            $scope.modelImage = "data:image/jpg;base64," + response.data;
            $scope.currentNotationImageIndex = nextImageIndex;
            //console.log(response);
          }
        );

    };

    $scope.nextMetamodeImage();

  $scope.previousModeImage = function()
    {
      var previousImageIndex=$scope.numModelImages-1;
      if($scope.currentNotationImageIndex-1>=0)
      {
        previousImageIndex = $scope.currentNotationImageIndex-1;
      }
      $scope.modelImage="assets/img/loading.gif";
      var request=$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/notations',{'numImage': previousImageIndex});
      request.then
          (
          function(response)
          {
            $scope.modelImage = "data:image/jpg;base64," + response.data;
            $scope.currentImageIndex = previousImageIndex;
          }
        );

    };  

  $scope.myInterval = -1;
  
  //var slides= $scope.slides  =[];
  //var slides = $scope.slides = [{image:'https://cloud.githubusercontent.com/assets/1996760/3596028/92b81bde-0cbe-11e4-9f3d-3e72e54655da.png', text:'Notation example 0'},{image:'https://cloud.githubusercontent.com/assets/1996760/3596026/9298408e-0cbe-11e4-8cb7-8b40d6aa6361.png', text:'Notation example 1'},{image:'https://cloud.githubusercontent.com/assets/1996760/3596027/92ab5980-0cbe-11e4-88c8-8931bd265b63.png', text:'Notation example 2'}];
  var slides = $scope.slides = [{image:'assets/img/notation1.png', text:'Notation example 0'},{image:'assets/img/notation2.png', text:'Notation example 1'},{image:'assets/img/notation3.png', text:'Notation example 2'}];
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

 $scope.nextImage=function(){if($scope.notationImageIndex+1<$scope.slides.length){$scope.notationImageIndex++;}else{$scope.notationImageIndex=0;}};
 $scope.previousImage=function(){if($scope.notationImageIndex-1>=0){$scope.notationImageIndex--;}else{$scope.notationImageIndex=$scope.slides.length-1}};


 //$scope.sportImages = ['http://placekitten.com/600/300','http://placekitten.com/601/300','http://placekitten.com/602/300'];
 //$scope.sportImages = ['http://placekitten.com/604/300','http://placekitten.com/601/300','http://placekitten.com/602/300'];

}]);