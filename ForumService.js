'use strict';
 
app.factory('ForumService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("ForumService...")
	
	var BASE_URL='http://localhost:8040/ecommerce'
		
    return {
         
            fetchAllForums: function() {
            	console.log("calling fetchAllForums ")
                    return $http.get(BASE_URL+'/forums')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching UserDetailss');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            
            getForum: function(id){
            	console.log("calling get Forum in service")
            	return $http.get(BASE_URL+'/forum/' +id)
            	.then(
            	function(response){
            		$rootScope.selectedForum = response.data
            		return response.data;
            		},
            		function(errResponse)
            		{
            			console.error('error while getting Forum');
            			return $q.reject(errResponse);
            		}
            	);
            },	
            
             
            createForum: function(Forum){
            	console.log("calling create user")
                    return $http.post(BASE_URL+'/createforums/', Forum)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating user');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
        
            
           
         
    };
 
}]);