
var app=angular.module('myApp',['ngRoute']);
app.config(function($routeProvider){
      $routeProvider
          .when('/home',{
                templateUrl: 'myhome.html',
                controller  : 'HomeController'
          })
          .when('/About',{
                templateUrl: 'About.html',
                controller  : 'AboutController'
          })
          .when('/login',{
                templateUrl: 'login.html',
                controller  : 'UserController'
          })
});


app.controller('AboutController',function($scope){

	$scope.message="Welcome to About Us Page"
    /*      
    Here you can handle controller for specific route as well.
    */
});

app.controller('HomeController',function($scope){

	$scope.message="Welcome to Home Page"
   
});
app.controller('UserController',function($scope){

	$scope.message="login"
   
});