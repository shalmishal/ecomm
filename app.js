var app=angular.module('myApp',['ngRoute']);

app.config(function($routeProvider) {
	  $routeProvider

	  .when('#/', {
	    templateUrl : 'index.html',
	    controller  : 'HomeController'
	  })
	  
	  
	  .when('/About',{
		  templateUrl : 'About.html',
		  controller  : 'AboutCtrl'
	  })
	  .when('/Login',{
		  templateUrl : 'Login.html',
		  controller  : 'UserController'
	  })
	  .when('/Register', {
		    templateUrl : 'Register.html',
		    controller  : 'UserController'
		  })
		  
  .when('/view_friend', {
    templateUrl : 'c_friend/view_friend.html',
    controller  : 'FriendController'
  })
   .when('/search_friend', {
    templateUrl : 'c_friend/search_friend.html',
    controller  : 'FriendController'
  })
 
.when('/create_blog', {
    templateUrl : 'c_blog/create_blog.html',
    controller  : 'BlogController'
  })
  .when('/view_blog', {
    templateUrl : 'c_blog/view_blog.html',
    controller  : 'BlogController'
  })
  .when('/list_blog', {
    templateUrl : 'c_blog/list_blog.html',
    controller  : 'BlogController'
  })
   .when('/create_forum', {
    templateUrl : 'c_forum/create_forum.html',
    controller  : 'ForumController'
  })
  .when('/list_forum', {
    templateUrl : 'c_forum/list_forum.html',
    controller  : 'ForumController'
  })

  .when('/chat', {
    templateUrl : 'c_chat/chat.html',
    controller  : 'ChatController'
  })

  .otherwise({redirectTo:'/'});
	  
	  
	  
	});
/*

app.controller('HomeCtrl',function($scope)
		{
	$scope.message="Home page";
		});
*/
/*app.run(function ($rootScope, $location, $cookieStore, $http){

	$rootScope.$on('$locationChangeStart', function(event, next, current){
	console.log("$locationChangeStart")
	//redirect to login page if not logged in and typing to access a restricted page

	var restrictedPage=$.inArray($location.path(), ['/Login','/Register','/list_blog']) ===-1;
	console.log("restrictedPage:" +restrictedPage)
	var loggedIn=$rootScope.currentUser.userid;
	console.log("loggedIn:"+loggedIn)
	if(restrictedPage & !loggedIn){
	console.log("Navigating to login page:")
	alert("You are not logged and so you can't do this operation")
	$location.path('/Login');
	}
	});

	//keep user logged in after page refresh
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
	if($rootScope.currentUser){
	$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser;
	
	}
	});*/